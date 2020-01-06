## eventBus

### 入门案例

`study.wyy.guava.eventbus.SimpleEventBusExample`
#### 1 事件监听器
```java
public class SimpleListener {
    private final Logger logger = LoggerFactory.getLogger(SimpleListener.class);

    @Subscribe
    public void doAction(final String event){
        if(logger.isInfoEnabled()){
            logger.info("Received event [{}] and will take a action",event);
        }
    }
}
```
`@Subscribe` Subscribe 订购的意思，订购一个消息 通过这个注解说明doAction方法是一个监听方法，当我们触发事件时，走的逻辑的方法就是doAction方法
**注意** doAction方法只能接收一个参数

#### 2 注册一个eventBus监听器
```java
public class SimpleEventBusExample {

    public static void main(String[] args) {
        // 定义一个eventBus
        final EventBus eventBus = new EventBus();
        // 注册监听器
        eventBus.register(new SimpleListener());
        System.out.println("post the sample event");
        // 触发一个事件
        eventBus.post("Simple event");
    }
}
```

### 2 多个订购事件的监听方法
`study.wyy.guava.eventbus.listener.SimpleListener`
```java
public class MultipleEventListeners {
    private final Logger logger = LoggerFactory.getLogger(MultipleEventListeners.class);
    @Subscribe
    public void task1(String event){
        if(logger.isInfoEnabled()){
            logger.info("Received event [{}] and will take a action by ==task1==",event);
        }
    }
    @Subscribe
    public void task2(String event){
        if(logger.isInfoEnabled()){
            logger.info("Received event [{}] and will take a action by ==task2==",event);
        }
    }
    /**
     *  @Subscribe：标记执行逻辑的方法
     * @param event  必须是包装类
     */
    @Subscribe
    public void intTask(Integer event){
        if(logger.isInfoEnabled()){
            logger.info("Received event [{}] and will take a action by ==intTask==",event);
        }
    }
}
```
`study.wyy.guava.eventbus.MultipleEventBusExample`

```java
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
```
##### 结论
显然post String，会被task1和task2监听到
而post Integer， 会被intTask监听到


### 3 listener的继承

> 父级抽象 `study.wyy.guava.eventbus.listener.AbstractListener`

```java
public abstract class AbstractListener {

    private final Logger logger = LoggerFactory.getLogger(AbstractListener.class); 
    @Subscribe
    public void commonTask(String event){
        if(logger.isInfoEnabled()){
            logger.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(), "commonTask");
        }
    }
}
```

> BaseLister 继承AbstractListener `study.wyy.guava.eventbus.listener.BaseListener`

```java
public class BaseListener extends AbstractListener {
    private final Logger logger = LoggerFactory.getLogger(BaseListener.class);
    @Subscribe
    public void baseTask(String event){
        if(logger.isInfoEnabled()){
            logger.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(), "baseTask");
        }
    }
}
```
> ConcretListener 继承BaseLister `study.wyy.guava.eventbus.listener.ConcretListener`

```java
public class ConcretListener extends BaseListener {

    private final Logger logger = LoggerFactory.getLogger(ConcretListener.class);
    @Subscribe
    public void conTask(String event){
        if(logger.isInfoEnabled()){
            logger.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(), "conTask");
        }
    }
}
```
> 注册监听器 : `study.wyy.guava.eventbus.InheritListenerEventBusExample`
```java
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

```

### 4 event的继承关系
> 定义一个apple类和fruit类，apple继承fruit `study.wyy.guava.eventbus.event`

> 定义一个监听器 `study.wyy.guava.eventbus.listener.FruitListener`
```java
public class FruitListener {
    private final Logger logger = LoggerFactory.getLogger(FruitListener.class);

    @Subscribe
    public void eat(final Fruit event){
        if(logger.isInfoEnabled()){
            logger.info("Fruit eat [{}] ",event);
        }
    }
    @Subscribe
    public void eat(final Apple event){
        if(logger.isInfoEnabled()){
            logger.info("Apple eat [{}] ",event);
        }
    }
}
```
> 注册监听器 `study.wyy.guava.eventbus.InheritEventEventBusExample`
```java
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
```

### 4 异常的处理

> ExceptionListener  `study.wyy.guava.eventbus.listener.ExceptionListener`

```java
public class ExceptionListener {

    private final Logger logger = LoggerFactory.getLogger(ExceptionListener.class);

    @Subscribe
    public void m1(String event){
        logger.info("================m1=================",event);
    }

    @Subscribe
    public void m2(String event){
        logger.info("================m2=================",event);
    }


    @Subscribe
    public void m3(String event){
        logger.info("================m3=================",event);
        // 抛出异常
        throw new RuntimeException("m3 处理出现异常");
    }
}
```
> ExceptionEventBusExample `study.wyy.guava.eventbus.ExceptionEventBusExample`

```java
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
```

### 5  DEADEVENT

可以拿到事件来源 event.getSource()

```java
public class DeadEventListener {

    private final Logger logger = LoggerFactory.getLogger(DeadEventListener.class);

    /**
     * 参数必须是DeadEvent
     *  可以拿到事件来源 event.getSource()
     *
     * @param event
     */
    @Subscribe
    public void handle(DeadEvent event){
        System.out.println(event.getSource());
        System.out.println(event.getEvent());
    }
}
```
