package com.mawen.think.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Aspect XML 配置类
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class AspectXmlConfig {

    public void beforeAnyPublicMethod() {
        System.out.println("@Before any public method.");
    }

    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around any public method." + pjp.getSignature());
        return pjp.proceed();
    }

    public void afterAnyPublicMethod() {
        System.out.println("@AfterReturning any public method.");
    }

    public void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing any public method.");
    }

    public void finalizeAnyPublicMethod() {
        System.out.println("@After any public method.");
    }
}
