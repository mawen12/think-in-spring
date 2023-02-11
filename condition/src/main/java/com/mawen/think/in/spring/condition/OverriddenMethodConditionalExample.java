package com.mawen.think.in.spring.condition;

import com.mawen.think.in.spring.condition.config.ConfigWithAlternativeBeans;
import com.mawen.think.in.spring.condition.model.ConfigWithBeanReactivated;
import com.mawen.think.in.spring.condition.model.ConfigWithBeanSkipped;
import com.mawen.think.in.spring.condition.model.ExampleBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
public class OverriddenMethodConditionalExample {

    public static void main(String[] args) {
        conditionOnOverriddenMethodHonored();
        noConditionOnOverriddenMethodHonored();
        configWithAlternativeBeans();
    }

    private static void conditionOnOverriddenMethodHonored() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithBeanSkipped.class);

        assertThat(ctx.getBeansOfType(ExampleBean.class)).isEmpty();

        ctx.close();
    }

    private static void noConditionOnOverriddenMethodHonored() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithBeanReactivated.class);
        Map<String, ExampleBean> beans = ctx.getBeansOfType(ExampleBean.class);
        assertThat(beans).hasSize(1);
        assertThat(beans.keySet().iterator().next()).isEqualTo("baz");

        ctx.close();
    }


    private static void configWithAlternativeBeans() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithAlternativeBeans.class);
        Map<String, ExampleBean> beans = ctx.getBeansOfType(ExampleBean.class);
        assertThat(beans).hasSize(1);
        assertThat(beans.keySet().iterator().next()).isEqualTo("baz");

        ctx.close();
    }
}
