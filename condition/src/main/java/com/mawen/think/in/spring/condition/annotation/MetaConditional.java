package com.mawen.think.in.spring.condition.annotation;

import com.mawen.think.in.spring.condition.condition.MetaConditionalFilter;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
@Conditional(MetaConditionalFilter.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MetaConditional {

    String value();
}
