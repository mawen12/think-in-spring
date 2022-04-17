package com.mawen.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 层次性的 Spring 事件传播示例
 */
public class HierachicalSpringEventPropagateDemo {

    public static void main(String[] args) {
        // 1. parent Spring 上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");

        // 2. current Spring 上下文
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");

        // current -> parent
        currentContext.setParent(parentContext);

        // 3. 注册监听器
        parentContext.addApplicationListener(new MyListener());
        currentContext.addApplicationListener(new MyListener());

        // 4. 初始化 parent Spring 上下文，current Spring 上下文
        parentContext.refresh();
        currentContext.refresh(); // current -> parent

        // 5. 开始 parent Spring 上下文，current Spring 上下文
        currentContext.start();

        // 6. 停止 parent Spring 上下文，current Spring 上下文
        currentContext.stop();

        // 7. 关闭 parent Spring 上下文，current Spring 上下文
        currentContext.close();
        parentContext.close();
    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent> {

        /**
         * 解决事件在层次行上下文传播重复调用的情况
         */
        private static Set<ApplicationContextEvent> applicationEvents = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if (applicationEvents.add(event)) {
                System.out.printf("监听到 Spring 应用上下文[ ID : %s] 的%s事件.\n",
                        event.getApplicationContext().getId(),
                        event.getClass().getSimpleName());
            }
        }
    }
}
