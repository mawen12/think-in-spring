package com.mawen.think.in.spring.aop.overview;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 动态代理示例
 * - ConfigurationClassEnhancer#newEnhancer(Class, ClassLoader)
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class CglibDynamicProxyDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 指定 Super Class = DefaultEchoService.class
        Class<?> superClass = DefaultEchoService.class;
        enhancer.setSuperclass(superClass);
        // 指定拦截接口
        enhancer.setInterfaces(new Class[]{EchoService.class});
        // 指定拦截方法，类似于 JDK 动态代理中的 InvocationHandler
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object source, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                long startTime = System.currentTimeMillis();
                // source -> CGLIB 子类
                // 目标类 -> DefaultEchoService
                // 错误使用
//                Object result = method.invoke(source, objects);
                // 正确使用
                Object result = methodProxy.invokeSuper(source, objects);
                long costTime = System.currentTimeMillis() - startTime;
                System.out.println("[CGLIB 字节码提升] echo 方法执行的实现：" + costTime + "ms.");
                return result;
            }
        });
        // 创建代理对象
        EchoService echoService = (EchoService) enhancer.create();
        System.out.println(echoService.echo("Hello World"));
    }

}
