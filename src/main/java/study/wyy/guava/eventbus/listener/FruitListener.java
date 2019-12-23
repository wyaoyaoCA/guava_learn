package study.wyy.guava.eventbus.listener;


import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.wyy.guava.eventbus.event.Apple;
import study.wyy.guava.eventbus.event.Fruit;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 20:49
 * @description：
 * @modified By：
 * @version: $
 */
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
