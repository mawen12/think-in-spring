package com.mawen.think.in.spring.metadata.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecialAttr {

    Class<?> clazz();

    Thread.State state();

    NestedAnno nestedAnno();

    NestedAnno[] nestedAnnoArray();

    NestedAnno optional() default @NestedAnno(value = "optional", anEnum = SomeEnum.DEFAULT, classArray = Void.class);

    NestedAnno[] optionalArray() default { @NestedAnno(value = "optional", anEnum = SomeEnum.DEFAULT, classArray = Void.class) };

}
