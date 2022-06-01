package com.mawen.think.in.spring.aop.overview;

/**
 * 类加载示例
 *
 * @author mawen12
 * @since 1.0
 */
public class ClassLoadingDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 打印类加载器
        System.out.println(classLoader);

        // 依次打印父级加载器
        ClassLoader parent = classLoader.getParent();
        while (parent != null) {
            System.out.println(parent);
            parent = parent.getParent();
        }
    }
}
