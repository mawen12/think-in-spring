package com.mawen.think.in.spring.aop.overview;

/**
 * {@link EchoService} 的静态代理示例，基于继承和组合来实现的 echo 方法执行时间统计
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class ProxyEchoService implements EchoService {

    private final EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String message) {
        long startTime = System.currentTimeMillis();
        String result = echoService.echo(message);
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("echo 方法执行的时间：" + costTime + " ms.");
        return result;
    }
}
