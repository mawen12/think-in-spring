package com.mawen.think.in.spring.aop.samples.service.impl;

import com.mawen.think.in.spring.aop.samples.service.EchoService;

/**
 * 默认 {@link EchoService} 实现
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/20
 */
public class DefaultEchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        return "[Echo] " + message;
    }
}
