package com.mawen.think.in.spring.metadata.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/10
 */

@Component("myName")
@Scope("myScope")
@SpecialAttr(clazz = String.class, state = Thread.State.NEW,
        nestedAnno = @NestedAnno(value = "na", anEnum = SomeEnum.LABEL1, classArray = {String.class}),
        nestedAnnoArray = {@NestedAnno, @NestedAnno(value = "na1", anEnum = SomeEnum.LABEL2, classArray = {Number.class})})
@SuppressWarnings({"serial", "unused"})
@DirectAnnotation(value = "direct", additional = "", additionalArray = {})
@MetaMetaAnnotation
@EnumSubclasses({SubclassEnum.FOO, SubclassEnum.BAR})
@NamedComposedAnnotation
public class AnnotatedComponent implements Serializable {

    public AnnotatedComponent() {
    }

    public void doSleep() {
    }

    @DirectAnnotation("direct")
    @MetaMetaAnnotation
    public void meta() {
    }
}
