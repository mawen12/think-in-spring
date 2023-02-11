package com.mawen.think.in.spring.condition.config;

import com.mawen.think.in.spring.condition.annotation.MetaConditional;
import com.mawen.think.in.spring.condition.model.ExampleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
@Configuration
@MetaConditional("test")
public class ConfigurationWithMetaCondition {

    @Bean
    public ExampleBean bean() {
        return new ExampleBean();
    }
}
