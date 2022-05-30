package com.mawen.think.in.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP 拦截器示例
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class AopInterceptorDemo {

    public static void main(String[] args) {
        before();
    }

    private static void before() {
        // 前置模式
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    // 前置拦截器
                    BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
                        @Override
                        public Object before(Object proxy, Method method, Object[] args) {
                            return System.currentTimeMillis();
                        }
                    };

                    Long startTime = 0L, endTime = 0L;
                    Object result = null;
                    try {
                        startTime = (long) beforeInterceptor.before(proxy, method, args);
                        EchoService echoService = new DefaultEchoService();
                        result = echoService.echo((String) args[0]); // 目标对象执行

                        // 方法执行后置拦截器
                        AfterReturnInterceptor afterReturnInterceptor = new AfterReturnInterceptor() {
                            @Override
                            public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
                                return System.currentTimeMillis();
                            }
                        };
                        // 执行 after
                        endTime = (long) afterReturnInterceptor.after(proxy, method, args, result);
                        return result;
                    } catch (Throwable throwable) {
                        // 异常拦截器（处理方法执行后）
                        ExceptionInterceptor exceptionInterceptor = new ExceptionInterceptor() {
                            @Override
                            public void intercept(Object proxy, Method method, Object[] args, Throwable throwable) {

                            }
                        };
                        exceptionInterceptor.intercept(proxy, method, args, throwable);
                    } finally {
                        // finally 后置拦截器
                        FinallyInterceptor finallyInterceptor = new TimeFinallyInterceptor(startTime, endTime);
                        Long costTime = (Long) finallyInterceptor.finalize(proxy, method, args, result);
                        System.out.println("echo 方法执行的时间：" + costTime + " ms.");
                    }
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello World");
    }


    static class TimeFinallyInterceptor implements FinallyInterceptor {
        private final Long startTime;
        private final Long endTime;

        public TimeFinallyInterceptor(Long startTime, Long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public Object finalize(Object proxy, Method method, Object[] args, Object returnResult) {
            // 方法执行时间（毫秒）
            return endTime - startTime;
        }
    }
}

