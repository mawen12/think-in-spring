package com.mawen.think.in.spring.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * AspectJ XML 配置
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
@Aspect
@Configuration
public class AspectJXMLDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");

//        AspectJXMLDemo aspectJXMLDemo = context.getBean(AspectJXMLDemo.class);

        context.close();
    }
}
