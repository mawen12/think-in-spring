package com.mawen.think.in.spring.condition.model;

import com.mawen.think.in.spring.condition.annotation.MetaNever;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
@Component
@MetaNever
public class NonConfigurationClass {

    @Bean
    public ExampleBean bean1() {
        return new ExampleBean();
    }

}
