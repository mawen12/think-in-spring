package com.mawen.think.in.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * （方法返回 ）后置拦截器
 * - @AfterReturning
 * - <aop:after-returning/>
 * - AfterReturningAdvice
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public interface AfterReturnInterceptor {

    /**
     * 后置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @param returnResult 执行方法返回结果
     * @return
     */
    Object after(Object proxy, Method method, Object[] args, Object returnResult);

}
