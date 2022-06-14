package com.mawen.think.in.spring.aop.features;

import com.mawen.think.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.mawen.think.in.spring.aop.features.pointcut.EchoServicePointcut;
import com.mawen.think.in.spring.aop.overview.DefaultEchoService;
import com.mawen.think.in.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 基于 API 实现的 Pointcut 示例
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class PointcutAPIDemo {

    public static void main(String[] args) {
        EchoServicePointcut pointcut = new EchoServicePointcut("echo", EchoService.class);
        EchoServiceMethodInterceptor advice = new EchoServiceMethodInterceptor();
        // 将 Pointcut 适配成 Advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        // 定义需要被代理的对象
        DefaultEchoService defaultEchoService = new DefaultEchoService();

        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        // 添加 Advisor
        proxyFactory.addAdvisor(advisor);
        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.echo("Hello World!"));
    }
}
