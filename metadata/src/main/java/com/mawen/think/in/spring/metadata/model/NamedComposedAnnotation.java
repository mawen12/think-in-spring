package com.mawen.think.in.spring.metadata.model;

import java.lang.annotation.*;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/10
 */
@NamedAnnotation1(name = "name 1")
@NamedAnnotation2(name = "name 2")
@NamedAnnotation3(name = "name 3")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface NamedComposedAnnotation {
}
