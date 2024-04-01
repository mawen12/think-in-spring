package com.mawen.think.in.spring.aop.features.interceptor;

import com.mawen.think.in.spring.aop.overview.EchoService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * {@link EchoService} 的方法拦截器
 *
 * @see {@link EchoService}
 * @see {@link MethodInterceptor}
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class EchoServiceMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println("拦截 EchoService 的方法：" + method);
        return invocation.proceed();
    }
}
