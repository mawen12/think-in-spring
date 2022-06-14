package com.mawen.think.in.spring.aop.design;

import com.mawen.think.in.spring.aop.design.pointcut.EchoServiceEchoMethodPointcut;
import com.mawen.think.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.mawen.think.in.spring.aop.overview.DefaultEchoService;
import com.mawen.think.in.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class PointcutAPIDemo {

    public static void main(String[] args) {
        EchoServiceEchoMethodPointcut pointcut = EchoServiceEchoMethodPointcut.INSTANCE;

        // 将 Pointcut 适配成 Advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());

        DefaultEchoService defaultEchoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        // 添加 Advisor
        proxyFactory.addAdvisor(advisor);
        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();

        System.out.println(echoService.echo("Hello World"));
    }
}
