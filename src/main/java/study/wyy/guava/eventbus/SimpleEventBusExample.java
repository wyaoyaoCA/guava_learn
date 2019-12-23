package study.wyy.guava.eventbus;

import com.google.common.eventbus.EventBus;
import study.wyy.guava.eventbus.listener.SimpleListener;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:04
 * @description：
 * @modified By：
 * @version: $
 */
public class SimpleEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        System.out.println("post the sample event");
        eventBus.post("Simple event");
    }
}
