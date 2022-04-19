package com.mawen.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.ErrorHandler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * Spring 异常处理接口
 *
 * @see ErrorHandler
 * @see SimpleApplicationEventMulticaster
 */
@EnableAsync
public class ErrorHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ErrorHandlerDemo.class);

        // 1. 注册监听器
        context.addApplicationListener((MyErrorEvent event) -> System.out.println(event));
        context.addApplicationListener(new MyErrorListener());

        // 初始化 Spring 上下文
        context.refresh();

        // 设置 ErrorHandler
        ApplicationEventMulticaster applicationEventMulticaster = context.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
//        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
//            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;
//            simpleApplicationEventMulticaster.setErrorHandler(e -> System.err.println("捕获到的事件异常：" + e.getMessage()));
//        }

        // 发布事件
        context.publishEvent(new MyErrorEvent("Hello World"));

        // 关闭 Spring 上下文
        context.close();

    }

    static class MyErrorEvent extends ApplicationEvent {

        public MyErrorEvent(String source) {
            super(source);
        }
    }

    static class MyErrorListener implements ApplicationListener<MyErrorEvent> {

        @Override
        public void onApplicationEvent(MyErrorEvent event) {
            throw new RuntimeException("故意抛出异常");
        }
    }

    @Bean
    public ErrorHandler errorHandler() {
        return e -> System.out.println("ErrorHandler 捕获到的异常" + e.getMessage());
    }

    @Async
    @EventListener
    public void onMyErrorEvent(MyErrorEvent event) {
        System.out.println(event);
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadExecutor();
    }

}
