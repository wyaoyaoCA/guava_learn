package study.wyy.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:07
 * @description：
 * @modified By：
 * @version: $
 */
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
