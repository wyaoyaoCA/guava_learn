package study.wyy.guava.eventbus;

import com.google.common.eventbus.EventBus;
import study.wyy.guava.eventbus.listener.DeadEventListener;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:04
 * @description：
 * @modified By：
 * @version: $
 */
public class DeadEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus("DeadEventBusd"){
            @Override
            public String toString() {
                return "DeadEventBus";
            }
        };
        eventBus.register(new DeadEventListener());

        eventBus.post("dead event");
    }
}
