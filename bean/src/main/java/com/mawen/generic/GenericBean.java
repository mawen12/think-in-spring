package com.mawen.generic;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 范型Bean的查找
 */
@Configuration
public class GenericBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(GenericBean.class);
        applicationContext.scan("com.mawen.generic");

        applicationContext.refresh();

        AService aService = applicationContext.getBean(AService.class);
        BService bService = applicationContext.getBean(BService.class);
        applicationContext.getBean("AService");// success
//        applicationContext.getBean("AService<A extends Asset>");// error
//        applicationContext.getBean("AService<Asset>");// error

        applicationContext.close();
    }

}
