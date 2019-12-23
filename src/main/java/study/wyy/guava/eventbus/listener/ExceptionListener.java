package study.wyy.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:47
 * @description：
 * @modified By：
 * @version: $
 */
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
