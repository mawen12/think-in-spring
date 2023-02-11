package com.mawen.think.in.spring.condition.config;

import com.mawen.think.in.spring.condition.condition.NoBeanOneCondition;
import com.mawen.think.in.spring.condition.model.ExampleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */

@Configuration
@Conditional(NoBeanOneCondition.class)
public class BeanTwoConfiguration {

    @Bean
    public ExampleBean bean2() {
        return new ExampleBean();
    }
}
