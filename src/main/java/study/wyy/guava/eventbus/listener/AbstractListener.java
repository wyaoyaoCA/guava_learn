package study.wyy.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:17
 * @description：
 * @modified By：
 * @version: $
 */
public abstract class AbstractListener {

    private final Logger logger = LoggerFactory.getLogger(AbstractListener.class);

    @Subscribe
    public void commonTask(String event){
        if(logger.isInfoEnabled()){
            logger.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(), "commonTask");
        }
    }
}
