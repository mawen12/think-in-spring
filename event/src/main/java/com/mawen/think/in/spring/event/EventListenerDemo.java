package com.mawen.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * {@link EventListener}
 *
 * @see EventListener
 */
@EnableAsync
public class EventListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EventListenerDemo.class);

        // 方法一：基于 Spring 接口注册监听器
        context.addApplicationListener(event -> println("ApplicationContext 接收到 Spring 事件" + event));

        // 初始化 Spring 上下文
        context.refresh();

        // 启动 Spring 上下文
        context.start();

        // 关闭 Spring 上下文
        context.close();
    }

    @Order(2)
    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        println("EventListener 接收到 Spring 事件" + event);
    }

    @Order(1)
    @EventListener
    public void onApplicationRefreshedEvent(ContextRefreshedEvent event) {
        println("EventListener.onApplicationRefreshedEvent 接收到 Spring ContextRefreshedEvent事件" + event);
    }

    @Async
    @EventListener
    public void onApplicationRefreshedEventAsync(ContextRefreshedEvent event) {
        println("EventListener.onApplicationRefreshedEventAsync 接收到 Spring ContextRefreshedEvent事件" + event);
    }

    @EventListener
    public void onApplicationStartedEvent(ContextStartedEvent event) {
        println("EventListener.onApplicationStartedEvent 接收到 Spring ContextStartedEvent事件" + event);
    }

    @EventListener
    public void onApplicationClosedEvent(ContextClosedEvent event) {
        println("EventListener.onApplicationClosedEvent 接收到 Spring ContextClosedEvent事件" + event);
    }

    private static void println(Object printable) {
        System.out.printf("[线程：%s] - %s\n", Thread.currentThread().getName(), printable);
    }






}
