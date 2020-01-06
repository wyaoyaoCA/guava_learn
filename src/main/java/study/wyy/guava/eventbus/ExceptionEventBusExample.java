package study.wyy.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import study.wyy.guava.eventbus.listener.ExceptionListener;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:04
 * @description：
 * @modified By：
 * @version: $
 */
public class ExceptionEventBusExample {


    /**
     * 其中监听器中其中一个监听方法出现异常时，不会影响该监听器其他的监听方法的，默认的异常处理就是会打印出异常的堆栈信息
     *
     */
    public static void main(String[] args) {
        //testDeafult();
        //testMyExceptionHandler();
        testMyExceptionHandler2();
    }


    /**
     * 测试出现异常的处理：打印异常的堆栈信息
     */
    public static void testDeafult(){
        final EventBus eventBus = new EventBus();
        eventBus.register(new ExceptionListener());
        eventBus.post("exception post");

    }

    /**
     * 如何自定义异常处理
     *      实现com.google.common.eventbus.SubscriberExceptionHandler接口
     */
    public static void testMyExceptionHandler(){
        final EventBus eventBus = new EventBus(new MyExceptionHandler());
        eventBus.register(new ExceptionListener());
        eventBus.post("exception post");
    }
    static class MyExceptionHandler implements SubscriberExceptionHandler{

        @Override
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            System.out.println("event => " +context.getEvent());
            System.out.println("eventBus => " +context.getEventBus());
            System.out.println("subscriber => " +context.getSubscriber());
            System.out.println("SubscriberMethod => " +context.getSubscriberMethod());
        }
    }


    public static void testMyExceptionHandler2(){
        final EventBus eventBus = new EventBus((exception,context) ->{
            System.out.println("event => " +context.getEvent());
            System.out.println("eventBus => " +context.getEventBus());
            System.out.println("subscriber => " +context.getSubscriber());
            System.out.println("SubscriberMethod => " +context.getSubscriberMethod());
        });
        eventBus.register(new ExceptionListener());
        eventBus.post("exception post");
    }
}
