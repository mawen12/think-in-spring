package com.mawen.think.in.spring.event.publisher;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 事件发布器
 *
 * {@link ApplicationEventPublisher}
 *
 * @see ApplicationEventPublisher
 */
public class ApplicationEventPublisherDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册到 Spring 上下文中
        context.register(ApplicationEventPublisherDemo.class);
        context.addApplicationListener(event -> System.out.println("接受事件：" + event));

        // 初始化 Spring 上下文
        context.refresh();
        // 启动 Spring 上下文
        context.start();
        // 关闭 Spring 上下文
        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        publisher.publishEvent(new ApplicationEvent("Hello World"){});

        publisher.publishEvent("Hello World");
    }
}
