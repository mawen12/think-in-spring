package com.mawen.think.in.spring.condition;

import com.mawen.think.in.spring.condition.model.NonConfigurationClass;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Conditional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
public class NoConfigurationConditionalExample {

    public static void main(String[] args) {
        nonConfigurationClass();
        nonConfigurationClassWithAsm();
    }

    /**
     * 多个 {@link Conditional}，必须所有都为 true
     */
    private static void nonConfigurationClass() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(NonConfigurationClass.class);
        ctx.refresh();

        assertThat(ctx.containsBean("bean1")).isFalse();

        ctx.close();
    }

    private static void nonConfigurationClassWithAsm() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.registerBeanDefinition("config", new RootBeanDefinition(NonConfigurationClass.class.getName()));
        ctx.refresh();

        assertThat(ctx.containsBean("bean1")).isFalse();

        ctx.close();
    }
}
