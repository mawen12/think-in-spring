package com.mawen.think.in.spring.aop.features;

import com.mawen.think.in.spring.aop.features.aspect.AspectConfiguration;
import com.mawen.think.in.spring.aop.features.aspect.AspectConfiguration2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Random;

/**
 * 基于注解实现 Pointcut 示例
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectJAnnotatedPointcutDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotatedPointcutDemo.class, AspectConfiguration.class, AspectConfiguration2.class);
        context.refresh();

        AspectJAnnotatedPointcutDemo aspectJAnnotatedPointcutDemo = context.getBean(AspectJAnnotatedPointcutDemo.class);
        aspectJAnnotatedPointcutDemo.execute();

        context.close();
    }

    public void execute() {
        if(true)
            throw new RuntimeException("For purpose.");
        System.out.println("execute()...");
    }

}
