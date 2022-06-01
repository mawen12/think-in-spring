package com.mawen.think.in.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * 方法执行异常拦截器
 * - @AfterThrowing
 * - <aop:after-throwing/>
 * - AfterAdvice
 *
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public interface ExceptionInterceptor {

    /**
     * 异常拦截器
     *
     * @param proxy
     * @param method
     * @param args
     * @param throwable 异常信息
     */
    void intercept(Object proxy, Method method, Object[] args, Throwable throwable);

}
