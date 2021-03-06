package com.mawen.think.in.spring.event.javasource;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observable} 示例
 * Java 原生的事件驱动是基于观察者模式。
 * 使用起来必须继承Observable，使用不太简单
 *
 * @see Observable
 */
public class ObservableDemo {

    public static void main(String[] args) {
        Observable observable = new EventObservable();
        // 添加观察者（监听器）
        observable.addObserver(new EventObserver());
        // 发送消息
        observable.notifyObservers("Hello World");
    }

    static class EventObservable extends Observable {
        @Override
        protected void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object data) {
            setChanged();
            // 封装为事件
            EventObject event = new EventObject(data);
            super.notifyObservers(event);
        }
    }

    static class EventObserver implements Observer, EventListener {
        @Override
        public void update(Observable observable, Object event) {
            System.out.println("receive: " + event);
        }
    }


}
