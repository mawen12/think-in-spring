package com.mawen.think.in.spring.aop.features;

import com.mawen.think.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.mawen.think.in.spring.aop.overview.DefaultEchoService;
import com.mawen.think.in.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * {@link ProxyFactory} 示例
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class ProxyFactoryDemo {

    public static void main(String[] args) {
        EchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象（被代理）
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        // 添加 Advice 实现 MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        // 获取代理对象，$Proxy0
        EchoService echoService = (EchoService) proxyFactory.getProxy();

        System.out.println(echoService.echo("Hello World"));
    }
}
