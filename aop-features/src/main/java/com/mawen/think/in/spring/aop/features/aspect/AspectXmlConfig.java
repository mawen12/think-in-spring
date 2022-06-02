package com.mawen.think.in.spring.aop.features.aspect;

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
}
