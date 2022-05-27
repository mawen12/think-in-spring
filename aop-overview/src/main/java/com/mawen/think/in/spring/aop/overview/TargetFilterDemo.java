package com.mawen.think.in.spring.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * AOP 目标过滤示例
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class TargetFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        filter();
    }

    private static void filter() throws ClassNotFoundException {
        String targetClassName = "com.mawen.think.in.spring.aop.overview.EchoService";
        // 获取当前线程 ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 获取目标类 基于类的判断
        Class<?> targetClass = classLoader.loadClass(targetClassName);
        // 方法定义：String echo(String message);
        // Spring 反射工具类 基于方法的判断
        Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
        System.out.println(targetMethod);

        // 查找方法 throws 类型为 NullPointerException
        ReflectionUtils.doWithMethods(targetClass, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                System.out.println("仅抛出 NullPointerException 方法为：" + method);
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                // 基于参数的判断
                Class<?>[] parameterTypes = method.getParameterTypes();
                // 基于异常的判断
                Class<?>[] exceptionTypes = method.getExceptionTypes();
                return parameterTypes.length == 1
                        && String.class.equals(parameterTypes[0])
                        && exceptionTypes.length == 1
                        && NullPointerException.class.equals(exceptionTypes[0]);
            }
        });

    }

}
