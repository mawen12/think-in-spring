package com.mawen.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 发布自定义 Spring 事件
 */
public class CustomizedApplicationEvent {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MySpringEventListener.class);
        // 注册监听自定义事件的监听器
        context.addApplicationListener(new MyListener()); // 只监听 MySpringEvent 事件
        context.addApplicationListener(event -> System.out.println("通用监听器 " + event)); // 监听所有的 ApplicationEvent 事件，包含 MySpringEvent 事件
        // 初始化 Spring 上下文
        context.refresh();
        // 发布自定义事件
        context.publishEvent(new MySpringEvent("Hello World"));
        // 关闭 Spring 上下文
        context.close();
    }

    /**
     * 自定义事件
     */
    static class MySpringEvent extends ApplicationEvent {

        public MySpringEvent(String source) {
            super(source);
        }
    }

    /**
     * 方式一：实现ApplicationListener
     */
    static class MyListener implements ApplicationListener<MySpringEvent> {

        @Override
        public void onApplicationEvent(MySpringEvent event) {
            System.out.println("监听自定义的 Spring 事件：" + event);
        }
    }

    /**
     * 方式二：使用 @EventListener
     */
     static class MySpringEventListener {

        @EventListener(MySpringEvent.class)
        public void onMySpringEvent(MySpringEvent event) {
            System.out.println("@EventListener 来监听自定义的 Spring 事件" + event);
        }

    }

}

