package study.wyy.guava.eventbus.listener;

import com.google.common.eventbus.DeadEvent;
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
