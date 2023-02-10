package com.mawen.think.in.spring.metadata;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.StandardClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link ClassMetadata} 示例
 *
 * @see {@link ClassMetadata}
 * @see {@link StandardClassMetadata}
 * @see {@link StandardAnnotationMetadata}
 * @see {@link org.springframework.core.type.classreading.SimpleMetadataReader}
 * @see {@link MetadataReader}
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/10
 */
public class ClassMetadataExample {

    public static void main(String[] args) throws IOException {
        standardClassMetadata();
        standardAnnotationMetadata();
        asmClassMetadata();
    }

    /**
     * 基于 Java 反射读取{@link StandardClassMetadata}元信息
     *
     * @since 2.5
     * @deprecated as of 5.2, in favor of {@link #standardAnnotationMetadata()}
     */
    @Deprecated
    private static void standardClassMetadata() {
        ClassMetadata classMetadata = new StandardClassMetadata(L0_a.class);
        withNoMemberClasses(classMetadata);

        classMetadata = new StandardClassMetadata(L0_b.class);
        withPublicMemberClasses(classMetadata);

        classMetadata = new StandardClassMetadata(L0_c.class);
        withNonPublicMemberClasses(classMetadata);

        classMetadata = new StandardClassMetadata(L0_b.L1.class);
        againstMemberClass(classMetadata);
    }

    /**
     * 基于 Java 反射读取{@link StandardAnnotationMetadata}元信息
     *
     * @since 5.2
     */
    private static void standardAnnotationMetadata() {
        ClassMetadata classMetadata = new StandardAnnotationMetadata(L0_a.class);
        withNoMemberClasses(classMetadata);

        classMetadata = new StandardAnnotationMetadata(L0_b.class);
        withPublicMemberClasses(classMetadata);

        classMetadata = new StandardAnnotationMetadata(L0_c.class);
        withNonPublicMemberClasses(classMetadata);

        classMetadata = new StandardAnnotationMetadata(L0_b.L1.class);
        againstMemberClass(classMetadata);
    }

    /**
     * 基于 ASM 读取{@link org.springframework.core.type.classreading.SimpleMetadataReader}元信息
     *
     * @since 2.5
     */
    private static void asmClassMetadata() throws IOException {
        SimpleMetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();

        ClassMetadata classMetadata = metadataReaderFactory.getMetadataReader(L0_a.class.getName()).getAnnotationMetadata();
        withNoMemberClasses(classMetadata);

        classMetadata = metadataReaderFactory.getMetadataReader(L0_b.class.getName()).getAnnotationMetadata();
        withPublicMemberClasses(classMetadata);

        classMetadata = metadataReaderFactory.getMetadataReader(L0_c.class.getName()).getAnnotationMetadata();
        withNonPublicMemberClasses(classMetadata);

        classMetadata = metadataReaderFactory.getMetadataReader(L0_b.L1.class.getName()).getAnnotationMetadata();
        againstMemberClass(classMetadata);

    }

    private static void withNoMemberClasses(ClassMetadata metadata) {
        String[] nestedClasses = metadata.getMemberClassNames();
        assertThat(nestedClasses).isEmpty();
    }

    private static void withPublicMemberClasses(ClassMetadata metadata) {
        String[] nestedClasses = metadata.getMemberClassNames();
        assertThat(nestedClasses).containsOnly(L0_b.L1.class.getName());
    }

    private static void withNonPublicMemberClasses(ClassMetadata metadata) {
        String[] nestedClasses = metadata.getMemberClassNames();
        assertThat(nestedClasses).containsOnly(L0_c.L1.class.getName());
    }

    private static void againstMemberClass(ClassMetadata metadata) {
        String[] nestedClasses = metadata.getMemberClassNames();
        assertThat(nestedClasses).isEmpty();
    }


    public static class L0_a {
    }

    public static class L0_b {
        public static class L1 { }
    }

    public static class L0_c {
        private static class L1 { }
    }

}
