package com.mawen.think.in.spring.condition.model;

import com.mawen.think.in.spring.condition.annotation.Never;
import com.mawen.think.in.spring.condition.config.ConfigWithBeanActive;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
public class ConfigWithBeanSkipped extends ConfigWithBeanActive {

    @Override
    @Bean
    @Never
    public ExampleBean baz() {
        return new ExampleBean();
    }

}
