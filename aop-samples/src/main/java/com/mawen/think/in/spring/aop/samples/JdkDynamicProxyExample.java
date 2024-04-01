package com.mawen.think.in.spring.aop.samples;

import com.mawen.think.in.spring.aop.samples.service.EchoService;
import com.mawen.think.in.spring.aop.samples.service.impl.DefaultEchoServiceImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JDK 动态代理 示例
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/20
 */
public class JdkDynamicProxyExample {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxyObject = Proxy.newProxyInstance(classLoader,
                new Class[]{EchoService.class},
                (proxy, method, args1) -> {
                    if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                        return new DefaultEchoServiceImpl().echo((String)args1[0]);
                    }
                    return null;
                }
        );

        isProxy(proxyObject);
        assertAdvisedOfInvocationHandler(proxyObject);
    }

    public static void isProxy(Object proxyObject) {
        assertThat(Proxy.isProxyClass(proxyObject.getClass())).isTrue();
    }

    public static void assertAdvisedOfInvocationHandler(Object proxyObject) {
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(proxyObject);
        Object advised = fieldGetter(invocationHandler.getClass(), "advised").apply(proxyObject);
        System.out.println(advised);

        Object mapperInterface = fieldGetter(invocationHandler.getClass(), "mapperInterface").apply(proxyObject);
        System.out.println(mapperInterface);

        Object target = fieldGetter(invocationHandler.getClass(), "target").apply(proxyObject);
        System.out.println(target);

        Class<?>[] interfaces = invocationHandler.getClass().getInterfaces();
        System.out.println(Arrays.asList(interfaces));
    }


    private static <T> Function<T, ?> fieldGetter(Class<?> clazz, String propertyName) {
        try {
            Field field = clazz.getDeclaredField(propertyName);
            return (field == null) ?
                    t -> null :
                    t -> {
                        field.setAccessible(true);

                        try {
                            return field.get(t);
                        } catch (IllegalAccessException e) {
                            return null;
                        }
                    };
        } catch (NoSuchFieldException e) {
            return t -> null;
        }
    }

}
