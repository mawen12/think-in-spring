package com.mawen.think.in.spring.event.applicationevent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Payload 事件案例
 *
 * {@link PayloadApplicationEvent}
 *
 * @see PayloadApplicationEvent
 */
public class PayloadApplicationEventDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PayloadApplicationEventDemo.class);
        // 注册 Spring 上下文事件监听器
        context.addApplicationListener(event -> {
            if (event instanceof PayloadApplicationEvent) {
                System.out.printf("监听Spring事件：%s, 内容：%s\n", event, ((PayloadApplicationEvent<?>) event).getPayload());
            } else {
                System.out.printf("监听Spring事件：%s, 内容：%s\n", event, event.getSource());
            }
        });
        // 初始化 Spring 上下文
        context.refresh();
        // 关闭 Spring 上下文
        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        publisher.publishEvent("Hello Wrold");
    }
}
