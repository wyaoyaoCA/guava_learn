package study.wyy.guava.eventbus;

import com.google.common.eventbus.EventBus;
import study.wyy.guava.eventbus.event.Apple;
import study.wyy.guava.eventbus.event.Fruit;
import study.wyy.guava.eventbus.listener.FruitListener;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:04
 * @description： 测试event的继承
 * @modified By：
 * @version: $
 */
public class InheritEventEventBusExample {


    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitListener());
        // 这个事件会被参数为Apple的方法监听到，
        eventBus.post(new Apple("apple"));
        System.out.println("========================");
        // 这个事件是会被两个方法都监听到的，因为子类是是可以用父类接收的
        eventBus.post(new Fruit("apple"));
    }
}
