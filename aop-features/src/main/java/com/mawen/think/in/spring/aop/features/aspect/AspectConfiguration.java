package com.mawen.think.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

import java.util.Random;

/**
 * Aspect 配置类
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
@Aspect
@Order
public class AspectConfiguration {

    /**
     * 匹配所有public方法的 Pointcut
     * 方法体中的内容不会输出
     */
    @Pointcut("execution(public * *(..))") // 匹配 JoinPoint
    private void anyPublicMethod() { // 方法名即 Pointcut 名称
        System.out.println("@Pointcut at any public method.");
    }

    @Before("anyPublicMethod()") // Join Point 拦截动作
    public void beforeAnyPublicMethod() throws Throwable {
        System.out.println("@Before any public method.");
    }

    @Around("anyPublicMethod()") // Join Point 拦截动作
    public Object aroundAnyPublicMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("@Around any public method.");
        return proceedingJoinPoint.proceed();
    }

    @After("anyPublicMethod()")
    public void finalizeAnyPublicMethod() {
        System.out.println("@After any public method.");
    }

    @AfterReturning("anyPublicMethod()")
    public void afterAnyPublicMethod() {
        System.out.println("@AfterReturning any public method.");
    }

    @AfterThrowing(value = "anyPublicMethod()", throwing = "ex")
    public void afterThrowingAnyPublicMethod(RuntimeException ex) {
        System.out.println("@AfterThrowing any public method." + ex);
    }


}
