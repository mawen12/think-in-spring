package com.mawen.think.in.spring.event.applicationlistener.async;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步的事件处理案例
 *
 * @see SimpleApplicationEventMulticaster
 */
public class AsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册类
        context.register(AsyncEventHandlerDemo.class);

        // 1. 注册监听器
        context.addApplicationListener((MySpringEvent event) -> System.out.printf("[线程 : %s] %s\n", Thread.currentThread().getName(), event));

        // 2. 启动 Spring 上下文
        context.refresh(); // 初始化 ApplicationEventMulticaster

        // 依赖查找 SimpleApplicationEventMulticaster
        ApplicationEventMulticaster applicationEventMulticaster =
                context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            ExecutorService taskExecutors = Executors.newSingleThreadExecutor();
            simpleApplicationEventMulticaster.setTaskExecutor(taskExecutors);

            // 在 Spring 上下文关闭的时候，停止线程池
            context.addApplicationListener((ContextClosedEvent event) -> {
                if (!taskExecutors.isShutdown()) {
                    taskExecutors.shutdown();
                }
            });
        }

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

    // 尽管并未使用 @Async 注解，但是因为 SimpleApplicationEventMulticaster 中设置了taskExecutor，因此它也变成了异步
    @EventListener
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("onApplicationEvent[线程 : %s] %s\n", Thread.currentThread().getName(), event);
    }

}
