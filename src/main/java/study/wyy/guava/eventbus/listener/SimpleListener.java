package study.wyy.guava.eventbus.listener;


import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 20:49
 * @description：
 * @modified By：
 * @version: $
 */
public class SimpleListener {
    private final Logger logger = LoggerFactory.getLogger(SimpleListener.class);

    @Subscribe
    public void doAction(final String event){
        if(logger.isInfoEnabled()){
            logger.info("Received event [{}] and will take a action",event);
        }
    }
}
