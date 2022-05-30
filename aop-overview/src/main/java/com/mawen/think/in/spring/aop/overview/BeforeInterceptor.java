package com.mawen.think.in.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * 前置拦截器
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public interface BeforeInterceptor {

    /**
     * 前置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);

}
