package com.mawen.think.in.spring.condition;

import com.mawen.think.in.spring.condition.config.ConfigurationWithMetaCondition;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
public class ConfigurationMetaConditionalExample {

    public static void main(String[] args) {
        metaConditional();
        metaConditionalWithAsm();
    }

    private static void metaConditional() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ConfigurationWithMetaCondition.class);
        ctx.refresh();

        assertThat(ctx.containsBean("bean")).isTrue();

        ctx.close();
    }

    private static void metaConditionalWithAsm() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.registerBeanDefinition("config", new RootBeanDefinition(ConfigurationWithMetaCondition.class.getName()));
        ctx.refresh();

        assertThat(ctx.containsBean("bean")).isTrue();

        ctx.close();
    }

}
