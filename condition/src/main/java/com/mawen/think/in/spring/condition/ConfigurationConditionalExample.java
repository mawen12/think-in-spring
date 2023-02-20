package com.mawen.think.in.spring.condition;

import com.mawen.think.in.spring.condition.config.BeanOneConfiguration;
import com.mawen.think.in.spring.condition.config.BeanThreeConfiguration;
import com.mawen.think.in.spring.condition.config.BeanTwoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Conditional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link Conditional} 示例
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
public class ConfigurationConditionalExample {

    public static void main(String[] args) {
        conditionalOnMissingBeanMatch();
        conditionalOnMissingBeanNoMatch();
        conditionalOnBeanMatch();
        conditionalOnBeanNoMatch();
    }

    private static void conditionalOnMissingBeanMatch() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(BeanOneConfiguration.class, BeanTwoConfiguration.class);
        ctx.refresh();

        assertThat(ctx.containsBean("bean1")).isTrue();
        assertThat(ctx.containsBean("bean2")).isFalse();
        assertThat(ctx.containsBean("beanTwoConfiguration")).isFalse();

        ctx.close();
    }

    private static void conditionalOnMissingBeanNoMatch() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(BeanTwoConfiguration.class);
        ctx.refresh();

        assertThat(ctx.containsBean("bean1")).isFalse();
        assertThat(ctx.containsBean("bean2")).isTrue();
        assertThat(ctx.containsBean("beanTwoConfiguration")).isTrue();

        ctx.close();
    }

    private static void conditionalOnBeanMatch() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(BeanOneConfiguration.class, BeanThreeConfiguration.class);
        ctx.refresh();

        assertThat(ctx.containsBean("bean1")).isTrue();
        assertThat(ctx.containsBean("bean3")).isTrue();
        assertThat(ctx.containsBean("beanOneConfiguration")).isTrue();

        ctx.close();
    }

    private static void conditionalOnBeanNoMatch() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(BeanThreeConfiguration.class);
        ctx.refresh();

        assertThat(ctx.containsBean("bean1")).isFalse();
        assertThat(ctx.containsBean("bean3")).isFalse();
        assertThat(ctx.containsBean("beanThreeConfiguration")).isFalse();

        ctx.close();
    }

}
