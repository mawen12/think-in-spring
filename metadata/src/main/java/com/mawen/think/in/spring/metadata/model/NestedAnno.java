package com.mawen.think.in.spring.metadata.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/10
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface NestedAnno {

    String value() default "default";

    SomeEnum anEnum() default SomeEnum.DEFAULT;

    Class<?>[] classArray() default Void.class;

}
