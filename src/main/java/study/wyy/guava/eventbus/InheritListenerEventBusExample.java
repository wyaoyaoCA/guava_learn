package study.wyy.guava.eventbus;

import com.google.common.eventbus.EventBus;
import study.wyy.guava.eventbus.listener.ConcretListener;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:04
 * @description：
 * @modified By：
 * @version: $
 */
public class InheritListenerEventBusExample {

    /**
     * 发现，ConcretListener的所有的父类的Listener都会监听到这条消息
     * @param args
     */
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new ConcretListener());
        System.out.println("post the string event");
        eventBus.post("I am String event");
    }
}
