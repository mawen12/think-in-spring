package com.mawen.think.in.spring.aop.features;

import com.mawen.think.in.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Pointcut 示例
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectJAnnotatedPointcutDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotatedPointcutDemo.class, AspectConfiguration.class);
        context.refresh();

        AspectJAnnotatedPointcutDemo aspectJAnnotatedPointcutDemo = context.getBean(AspectJAnnotatedPointcutDemo.class);
        aspectJAnnotatedPointcutDemo.execute();

        context.close();
    }

    public void execute() {
        System.out.println("execute()...");
    }

}
