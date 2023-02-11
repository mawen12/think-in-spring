package com.mawen.think.in.spring.condition.model;

import org.springframework.context.annotation.Bean;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
public class ConfigWithBeanReactivated extends ConfigWithBeanSkipped {

    @Bean
    @Override
    public ExampleBean baz() {
        return super.baz();
    }
}
