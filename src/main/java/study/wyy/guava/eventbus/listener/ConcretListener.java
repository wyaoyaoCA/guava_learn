package study.wyy.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:22
 * @description：
 * @modified By：
 * @version: $
 */
public class ConcretListener extends BaseListener {

    private final Logger logger = LoggerFactory.getLogger(ConcretListener.class);
    @Subscribe
    public void conTask(String event){
        if(logger.isInfoEnabled()){
            logger.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(), "conTask");
        }
    }
}
