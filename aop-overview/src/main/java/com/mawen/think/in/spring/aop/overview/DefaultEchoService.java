package com.mawen.think.in.spring.aop.overview;

/**
 * 默认的 {@link EchoService} 实现
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        return "[ECHO] " + message;
    }
}
