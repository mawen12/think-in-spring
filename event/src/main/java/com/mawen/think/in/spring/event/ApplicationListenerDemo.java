package com.mawen.think.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Spring 监听器
 *
 * {@link ApplicationListener}
 *
 * @see ApplicationListener
 */
public class ApplicationListenerDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 向 Spring 应用上下文注册事件，基于Lambda表达式快速注册监听器
        context.addApplicationListener(event -> System.out.println("接受 Spring 事件：" + event));
        // 展示事件对象、时间戳
        // 接受 Spring 事件：org.springframework.context.event.ContextRefreshedEvent[source=org.springframework.context.support.GenericApplicationContext@6576fe71, started on Sat Apr 16 09:42:36 CST 2022]

        // 如果不执行 refresh，后续的操作也不会出发事件
        // 初始化 Spring 上下文
        context.refresh();

        // 启动 Spring 上下文
        context.start();

        // 关闭 Spring 上下文
        context.close();
    }

}
