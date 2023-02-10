package com.mawen.think.in.spring.metadata;

import com.mawen.think.in.spring.metadata.model.*;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link AnnotationMetadata} 示例
 *
 * @see {@link AnnotationMetadata}
 * @see {@link StandardAnnotationMetadata}
 * @see {@link org.springframework.core.type.classreading.SimpleAnnotationMetadataReadingVisitor}
 * @see {@link org.springframework.core.type.AnnotationMetadataTests}
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/10
 */
public class AnnotationMetadataExample {
    public static void main(String[] args) throws IOException {
        standardAnnotationMetadata();
        asmAnnotationMetadata();
    }

    /**
     * 基于 Java 反射的 {@link StandardAnnotationMetadata} 元信息读取
     */
    private static void standardAnnotationMetadata() {
        AnnotationMetadata metadata = AnnotationMetadata.introspect(AnnotatedComponent.class);
        doTestAnnotation(metadata);
    }

    /**
     * 基于 ASM {@link org.springframework.core.type.classreading.SimpleAnnotationMetadataReadingVisitor} 元信息读取
     */
    private static void asmAnnotationMetadata() {
        MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = null;
        try {
            metadataReader = metadataReaderFactory.getMetadataReader(AnnotatedComponent.class.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
        doTestAnnotation(metadata);
    }

    private static void doTestAnnotation(AnnotationMetadata metadata) {
        assertThat(metadata.getClassName()).isEqualTo(AnnotatedComponent.class.getName());
        assertThat(metadata.isInterface()).isFalse();
        assertThat(metadata.isAnnotation()).isFalse();
        assertThat(metadata.isAbstract()).isFalse();
        assertThat(metadata.isConcrete()).isTrue(); // 表示为是否具体类，不是抽象类和接口
        assertThat(metadata.hasSuperClass()).isTrue();
        assertThat(metadata.getSuperClassName()).isEqualTo(Object.class.getName());
        assertThat(metadata.getInterfaceNames()).hasSize(1);
        assertThat(metadata.getInterfaceNames()[0]).isEqualTo(Serializable.class.getName());

        assertThat(metadata.isAnnotated(Component.class.getName())).isTrue();

        assertThat(metadata.isAnnotated(NamedComposedAnnotation.class.getName())).isTrue();

        assertThat(metadata.hasAnnotation(Component.class.getName())).isTrue();
        assertThat(metadata.hasAnnotation(Scope.class.getName())).isTrue();
        assertThat(metadata.hasAnnotation(SpecialAttr.class.getName())).isTrue();

        assertThat(metadata.hasAnnotation(NamedComposedAnnotation.class.getName())).isTrue();
        assertThat(metadata.getAnnotationTypes()).containsExactlyInAnyOrder(
                Component.class.getName(), Scope.class.getName(),
                SpecialAttr.class.getName(), DirectAnnotation.class.getName(),
                MetaMetaAnnotation.class.getName(), EnumSubclasses.class.getName(),
                NamedComposedAnnotation.class.getName()
        );

        AnnotationAttributes compAttrs = (AnnotationAttributes) metadata.getAnnotationAttributes(Component.class.getName());
        assertThat(compAttrs).hasSize(1);
        assertThat(compAttrs.getString("value")).isEqualTo("myName");
        AnnotationAttributes scopeAttrs = (AnnotationAttributes) metadata.getAnnotationAttributes(Scope.class.getName());
        assertThat(scopeAttrs).hasSize(3);
        assertThat(scopeAttrs.getString("value")).isEqualTo("myScope");

        Set<MethodMetadata> methods =  metadata.getAnnotatedMethods(DirectAnnotation.class.getName()); // getAnnotatedMethods 获取使用了 DirectAnnotation 的方法
        MethodMetadata method = methods.iterator().next();
        assertThat(method.getAnnotationAttributes(DirectAnnotation.class.getName()).get("value")).isEqualTo("direct");
        assertThat(method.getAnnotationAttributes(DirectAnnotation.class.getName()).get("myValue")).isEqualTo("direct");
        List<Object> allMeta = method.getAllAnnotationAttributes(DirectAnnotation.class.getName()).get("value");
        assertThat(new HashSet<>(allMeta)).isEqualTo(new HashSet<Object>(Arrays.asList("direct", "meta")));
        allMeta = method.getAllAnnotationAttributes(DirectAnnotation.class.getName()).get("additional");
        assertThat(new HashSet<>(allMeta)).isEqualTo(new HashSet<Object>(Arrays.asList("direct")));

        assertThat(metadata.isAnnotated(IsAnnotatedAnnotation.class.getName())).isTrue();

        { // 使用 classValuesAsString = false (默认值) 执行测试
            AnnotationAttributes specialAttrs = (AnnotationAttributes) metadata.getAnnotationAttributes(SpecialAttr.class.getName());
            assertThat(specialAttrs).hasSize(6);
            assertThat(String.class.isAssignableFrom(specialAttrs.getClass("clazz"))).isTrue();
            assertThat(specialAttrs.getEnum("state").equals(Thread.State.NEW)).isTrue();

            AnnotationAttributes nestedAnno = specialAttrs.getAnnotation("nestedAnno");
            assertThat("na").isEqualTo(nestedAnno.getString("value"));
            assertThat(nestedAnno.getEnum("anEnum").equals(SomeEnum.LABEL1)).isTrue();
            assertThat((Class<?>[]) nestedAnno.get("classArray")).isEqualTo(new Class<?>[]{String.class});

            AnnotationAttributes[] nestedAnnoArrays = specialAttrs.getAnnotationArray("nestedAnnoArray");
            assertThat(nestedAnnoArrays).hasSize(2);
            assertThat(nestedAnnoArrays[0].getString("value")).isEqualTo("default");
            assertThat(nestedAnnoArrays[0].getEnum("anEnum").equals(SomeEnum.DEFAULT)).isTrue();
            assertThat((Class<?>[]) nestedAnnoArrays[0].get("classArray")).isEqualTo(new Class<?>[]{Void.class});
            assertThat(nestedAnnoArrays[0].get("classArray")).isEqualTo(new Class<?>[]{Void.class});
            assertThat(nestedAnnoArrays[1].getString("value")).isEqualTo("na1");
            assertThat(nestedAnnoArrays[1].getEnum("anEnum").equals(SomeEnum.LABEL2)).isTrue();
            assertThat((Class<?>[]) nestedAnnoArrays[1].get("classArray")).isEqualTo(new Class<?>[]{Number.class});
            assertThat(nestedAnnoArrays[1].get("classArray")).isEqualTo(new Class<?>[]{Number.class});

            AnnotationAttributes optional = specialAttrs.getAnnotation("optional");
            assertThat(optional.getString("value")).isEqualTo("optional");
            assertThat(optional.getEnum("anEnum").equals(SomeEnum.DEFAULT)).isTrue();
            assertThat((Class<?>[])optional.getClassArray("classArray")).isEqualTo(new Class<?>[]{Void.class});
            assertThat(optional.getClassArray("classArray")).isEqualTo(new Class<?>[]{Void.class});

            AnnotationAttributes[] optionalArray = specialAttrs.getAnnotationArray("optionalArray");
            assertThat(optionalArray).hasSize(1);
            assertThat(optionalArray[0].getString("value")).isEqualTo("optional");
            assertThat(optionalArray[0].getEnum("anEnum").equals(SomeEnum.DEFAULT)).isTrue();
            assertThat((Class<?>[])optionalArray[0].getClassArray("classArray")).isEqualTo(new Class<?>[]{Void.class});
            assertThat(optionalArray[0].getClassArray("classArray")).isEqualTo(new Class<?>[]{Void.class});

            assertThat(metadata.getAnnotationAttributes(DirectAnnotation.class.getName()).get("value")).isEqualTo("direct");
            allMeta = metadata.getAllAnnotationAttributes(DirectAnnotation.class.getName()).get("value");
            assertThat(new HashSet<>(allMeta)).isEqualTo(new HashSet<Object>(Arrays.asList("direct", "meta")));
            assertThat(metadata.getAnnotationAttributes(DirectAnnotation.class.getName()).get("additional")).isEqualTo(""); // getAnnotationAttributes 获取使用了 DirectAnnotation 的注解属性值
            assertThat((String[]) metadata.getAnnotationAttributes(DirectAnnotation.class.getName()).get("additionalArray")).isEmpty();
        }
        { // 使用 classValuesAsString = true 执行测试
            AnnotationAttributes specialAttrs = (AnnotationAttributes) metadata.getAnnotationAttributes(SpecialAttr.class.getName(), true);
            assertThat(specialAttrs).hasSize(6);
            assertThat(specialAttrs.get("clazz")).isEqualTo(String.class.getName());
            assertThat(specialAttrs.getString("clazz")).isEqualTo(String.class.getName());

            AnnotationAttributes nestedNo = specialAttrs.getAnnotation("nestedAnno");
            assertThat(nestedNo.getStringArray("classArray")).isEqualTo(new String[]{String.class.getName()});

            AnnotationAttributes[] nestedAnnoArrays = specialAttrs.getAnnotationArray("nestedAnnoArray");
            assertThat((String[]) nestedAnnoArrays[0].get("classArray")).isEqualTo(new String[]{Void.class.getName()});
            assertThat(nestedAnnoArrays[0].get("classArray")).isEqualTo(new String[]{Void.class.getName()});
            assertThat((String[]) nestedAnnoArrays[1].get("classArray")).isEqualTo(new String[]{Number.class.getName()});
            assertThat(nestedAnnoArrays[1].get("classArray")).isEqualTo(new String[]{Number.class.getName()});

            AnnotationAttributes optional = specialAttrs.getAnnotation("optional");
            assertThat((String[]) optional.get("classArray")).isEqualTo(new String[]{Void.class.getName()});
            assertThat(optional.get("classArray")).isEqualTo(new String[]{Void.class.getName()});

            AnnotationAttributes[] optionalArrays = specialAttrs.getAnnotationArray("optionalArray");
            assertThat((String[]) optionalArrays[0].get("classArray")).isEqualTo(new String[]{Void.class.getName()});
            assertThat(optionalArrays[0].get("classArray")).isEqualTo(new String[]{Void.class.getName()});

            assertThat(metadata.getAnnotationAttributes(DirectAnnotation.class.getName()).get("value")).isEqualTo("direct");
            allMeta = metadata.getAllAnnotationAttributes(DirectAnnotation.class.getName()).get("value");
            assertThat(new HashSet<>(allMeta)).isEqualTo(new HashSet<Object>(Arrays.asList("direct", "meta")));
        }
    }



}
