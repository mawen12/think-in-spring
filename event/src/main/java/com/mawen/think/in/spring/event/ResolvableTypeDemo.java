package com.mawen.think.in.spring.event;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ResolvableType;

import java.util.ArrayList;

/**
 * 泛型处理案例
 *
 * @see ResolvableType
 */
public class ResolvableTypeDemo {

    public static void main(String[] args) {
        // 工厂创建
        // StringList <- ArrayList <- AbstractList <- List <- Collection
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        System.out.println(resolvableType.getSuperType()); // ArrayList
        System.out.println(resolvableType.getSuperType().getSuperType()); // AbstractList

        System.out.println(resolvableType.asCollection().resolve()); // 获取 Raw Type
        System.out.println(resolvableType.asCollection().resolveGeneric(0)); // 获取泛型参数类型

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addApplicationListener((MySpringEvent event) -> System.out.println(event));

        context.refresh();

        context.publishEvent(new MySubSpringEvent("Hello World"));

        context.close();
    }

    static class StringList extends ArrayList<String> {

    }

    static class MySpringEvent extends ApplicationEvent {
        public MySpringEvent(Object source) {
            super(source);
        }
    }

    static class MySubSpringEvent extends MySpringEvent {
        public MySubSpringEvent(Object source) {
            super(source);
        }
    }




}
