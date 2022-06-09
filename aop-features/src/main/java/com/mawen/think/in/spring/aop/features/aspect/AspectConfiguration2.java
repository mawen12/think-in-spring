package com.mawen.think.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Aspect 配置类
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
@Aspect
public class AspectConfiguration2 implements Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Before("execution(public **(..))") // Join Point 拦截动作
    public void beforeAnyPublicMethod2() {
        System.out.println("@Before any public method.(2)");
    }


}
