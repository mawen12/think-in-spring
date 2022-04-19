package com.mawen.think.in.spring.event.applicationlistener;

import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 注册 Spring ApplicationListener
 *
 * {@link org.springframework.context.ApplicationListener}
 *
 * @see ApplicationListener
 */
public class ApplicationListenerRegisterDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 方式一：ApplicationListener 来实现 Spring 事件监听
        // a方法：Spring Bean
        context.register(MyApplicationListener.class);

        // b方法：调用API注册
        context.addApplicationListener((ContextClosedEvent event) -> System.out.println("ApplicationListener 接受事件：" +event));

        // 初始化 Spring 上下文
        context.refresh();

        // 关闭 Spring 上下文
        context.close();
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("MyApplicationListener 接受事件：" + event);
        }
    }
}
