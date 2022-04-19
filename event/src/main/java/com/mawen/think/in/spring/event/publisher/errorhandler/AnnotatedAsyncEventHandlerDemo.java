package com.mawen.think.in.spring.event.publisher.errorhandler;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于注解的异步的事件处理案例
 *
 * @see SimpleApplicationEventMulticaster
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册类
        context.register(AnnotatedAsyncEventHandlerDemo.class);

        // 1. 注册监听器 此处是同步执行
        context.addApplicationListener((MySpringEvent event) -> System.out.printf("[线程 : %s] %s\n", Thread.currentThread().getName(), event));

        // 2. 启动 Spring 上下文
        context.refresh(); // 初始化 ApplicationEventMulticaster

        // 3. 发布事件
        context.publishEvent(new MySpringEvent("Hello World"));

        // 4. 关闭 Spring 上下文
        context.close();
    }

    static class MySpringEvent extends ApplicationEvent {

        public MySpringEvent(Object source) {
            super(source);
        }
    }

    // 同步 -> 异步
    @Async
    @EventListener
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("onApplicationEvent[线程 : %s] %s\n", Thread.currentThread().getName(), event);
    }

    @Bean
    public ExecutorService taskExecutor() {
        return Executors.newSingleThreadExecutor(new CustomizableThreadFactory("myspringevent-pool-thread-"));
    }
}
