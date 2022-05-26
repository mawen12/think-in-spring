package com.mawen.think.in.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理示例，凭空去创建一个代理类，在运行时动态生成字节码
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class JdkDynamicProxyDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 判断执行的方法是否来自于EchoService，此处可以省略，但是因为第二个参数是数组，即多接口的，
                // 因而出于严谨性需要判断方法是否一致
                // isAssignableFrom 用来判断 method 所在的类是不是 EchoService 类或者其子类
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
                    return echoService.echo((String) args[0]);
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello World");
    }
}
