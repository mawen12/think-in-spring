package com.mawen.think.in.spring.aop.features;

import com.mawen.think.in.spring.aop.overview.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于 XML 配置的自动代理示例
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class AspectJSchemaBasedAutoProxyDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-auto-proxy-context.xml");

        context.refresh();

        EchoService echoService = context.getBean("echoService", EchoService.class);
        System.out.println(echoService.echo("Hello World"));

        context.close();
    }
}
