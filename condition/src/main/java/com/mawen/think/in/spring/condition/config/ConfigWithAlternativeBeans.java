package com.mawen.think.in.spring.condition.config;

import com.mawen.think.in.spring.condition.condition.AlwaysCondition;
import com.mawen.think.in.spring.condition.condition.NeverCondition;
import com.mawen.think.in.spring.condition.model.ExampleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
@Configuration
public class ConfigWithAlternativeBeans {

    @Bean(name = "baz")
    @Conditional(AlwaysCondition.class)
    public ExampleBean baz1() {
        return new ExampleBean();
    }

    @Bean(name = "baz")
    @Conditional(NeverCondition.class)
    public ExampleBean baz2() {
        return new ExampleBean();
    }
}
