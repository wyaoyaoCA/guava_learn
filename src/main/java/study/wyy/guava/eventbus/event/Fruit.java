package study.wyy.guava.eventbus.event;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-19 21:30
 * @description：
 * @modified By：
 * @version: $
 */
public class Fruit {
    private String name;



    public Fruit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                '}';
    }
}
