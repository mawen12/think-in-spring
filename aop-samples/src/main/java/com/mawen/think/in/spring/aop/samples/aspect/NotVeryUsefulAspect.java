package com.mawen.think.in.spring.aop.samples.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/24
 */
@Aspect // 声明一个 Aspect
public class NotVeryUsefulAspect {

    @Pointcut("execution(* transfer(..))") // Pointcut 表达式
    private void anyOldTransfer() { // Pointcut
    }


}
