package com.mawen.think.in.spring.condition;

import com.mawen.think.in.spring.condition.config.ConditionOnMethodConfiguration;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
public class MethodConditionalExample {

    public static void main(String[] args) {
        methodConditional();
        methodConditionalWithAsm();
    }

    private static void methodConditional() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ConditionOnMethodConfiguration.class);
        ctx.refresh();

        Assertions.assertThat(ctx.containsBean("bean1")).isFalse();

        ctx.close();
    }

    private static void methodConditionalWithAsm() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.registerBeanDefinition("config", new RootBeanDefinition(ConditionOnMethodConfiguration.class.getName()));
        ctx.refresh();

        Assertions.assertThat(ctx.containsBean("bean1")).isFalse();

        ctx.close();
    }

}
