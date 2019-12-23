package study.wyy.guava.eventbus;

import com.google.common.eventbus.EventBus;
import study.wyy.guava.eventbus.listener.MultipleEventListeners;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:04
 * @description：
 * @modified By：
 * @version: $
 */
public class MultipleEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new MultipleEventListeners());
        System.out.println("post the string event");
        eventBus.post("I am String event");
        System.out.println("post the int event");
        eventBus.post(1000);



    }
}
