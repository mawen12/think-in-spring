package com.mawen.think.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 注入 {@link ApplicationEventPublisher} 示例
 * # num 表示事件的执行顺序
 *
 */
public class InjectingApplicationEventPublisher implements ApplicationEventPublisherAware,
        ApplicationContextAware, BeanPostProcessor {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void init() {
        // # 3
        publisher.publishEvent(new MyCustomizedEvent("Hello World from @Autowired ApplicationEventPublisher"));
        // # 4
        context.publishEvent(new MyCustomizedEvent("Hello World from @Autowired AppliationContext"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        // # 1
        applicationEventPublisher.publishEvent(new MyCustomizedEvent("The Event from ApplicationEventPublisher"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // # 2
        applicationContext.publishEvent(new MyCustomizedEvent("The Event from ApplicationContext"));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册Bean
        context.register(InjectingApplicationEventPublisher.class);

        // 添加 监听器
        context.addApplicationListener(new MyCustomizedListener());

        // 启动 Spring 上下文
        context.refresh();

        // 关闭 Spring 上下文
        context.close();
    }

    /**
     * 自定义事件
     */
    static class MyCustomizedEvent extends ApplicationEvent {

        public MyCustomizedEvent(Object source) {
            super(source);
        }

    }

    /**
     * 自定义监听器
     */
    static class MyCustomizedListener implements ApplicationListener<MyCustomizedEvent> {

        @Override
        public void onApplicationEvent(MyCustomizedEvent event) {
            System.out.println("监听事件 " + event);
        }
    }

}
