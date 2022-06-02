package com.mawen.think.in.spring.aop.features.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * EchoService Pointcut
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class EchoServicePointcut extends StaticMethodMatcherPointcut {

    private String methodName;

    private Class targetClass;

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return Objects.equals(methodName, method.getName())
                && this.targetClass.isAssignableFrom(targetClass);
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public EchoServicePointcut(String methodName, Class targetClass) {
        this.methodName = methodName;
        this.targetClass = targetClass;
    }
}
