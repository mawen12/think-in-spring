package com.mawen.think.in.spring.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @AspectJ 注解驱动
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
@Aspect // 声明为 Aspect 切面
@Configuration // Configuration class
@EnableAspectJAutoProxy // 激活 Aspect 注解自动代理
public class AspectJAnnotationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotationDemo.class);
        context.refresh();

        AspectJAnnotationDemo aspectJAnnotationDemo = context.getBean(AspectJAnnotationDemo.class);

        context.close();
    }

}
