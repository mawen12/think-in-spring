package com.mawen.think.in.spring.aop.features;

import com.mawen.think.in.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.AspectJAfterAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用 AspectJ 原生 API
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class AspectJAnnotationUsingAPIDemo {

    public static void main(String[] args) {
        // 通过创建一个 HashMap 缓存，作为被代理对象
        HashMap<String, Object> cache = new HashMap<>();
        // 创建 Proxy 工厂（AspectJ）
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        // 增加 Aspect 配置类
        proxyFactory.addAspect(AspectConfiguration.class);

        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    System.out.printf("[MethodBeforeAdvice] 当前存放的是 Key : %s, Value : %s \n", args[0], args[1]);
                }
            }
        });

        // 添加 AfterReturningAdvice
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    System.out.printf("[AfterReturningAdvice] 当前存放的是 Key : %s, 新存放的 Value : %s, 之前关联的 Value : %s \n",
                        args[0],    // key
                        args[1],    // new value
                        returnValue // old value
                    );
                }
            }
        });

        // 直接存储
//        cache.put("1", "A");
        // 通过代理对象存储
        Map<String, Object> proxy = proxyFactory.getProxy();
        proxy.put("1", "A");
        proxy.put("1", "B");
        System.out.println(proxy.get("1"));
    }

}
