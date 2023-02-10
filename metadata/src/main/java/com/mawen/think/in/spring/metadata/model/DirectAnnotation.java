package com.mawen.think.in.spring.metadata.model;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/10
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DirectAnnotation {

    @AliasFor("myValue")
    String value() default "";

    @AliasFor("value")
    String myValue() default "";

    String additional() default "direct";

    String[] additionalArray() default "direct";

}
