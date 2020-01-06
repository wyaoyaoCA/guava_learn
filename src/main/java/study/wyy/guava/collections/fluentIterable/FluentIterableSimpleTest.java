package study.wyy.guava.collections.fluentIterable;

import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author ：wyy
 * @date ：Created in 2020-01-06 20:40
 * @description：
 * @modified By：
 * @version: $
 */
public class FluentIterableSimpleTest {

    private FluentIterable<String> build() {
        return FluentIterable.from(Arrays.asList("java", "python", "php", "C#", "javaScript"));
    }

    /**
     * 如何创建FluentIterable
     */
    @Test
    public void testCreateFluentIterable() {
        // 1 from 参数可以是一个数组，也可是一个java.lang.Iterable 可迭代类，比如List
        FluentIterable<String> from = FluentIterable.from(Arrays.asList("java", "python", "php", "C#", "javaScript"));

        // 2 of方法
        FluentIterable.of();
        // 2.1 可变参数
        FluentIterable<String> of = FluentIterable.of("java", "python", "php", "C#", "javaScript");
    }

    /**
     * 测试filter：过滤符合条件的，需要的是Predicate
     */
    @Test
    public void testFilter() {
        FluentIterable<String> fit = build();
        assertThat(fit.size(), equalTo(5));

        // 过滤出不为null且长度大于4的
        FluentIterable<String> result = fit.filter(item -> item != null && item.length() > 4);
        assertThat(result.size(), equalTo(2));
    }

    /**
     * append: 追加元素，注意返回一个新的FluentIterable
     * 可以接收一个Iterable，也可以是可变参数
     */
    @Test
    public void testAppend() {
        FluentIterable<String> fit = build();
        assertThat(fit.size(), equalTo(5));

        List<String> strings = Arrays.asList("html", "Android", "css");
        FluentIterable<String> append = fit.append(strings);
        assertThat(append.size(), equalTo(8));
        assertThat(append.contains("Android"), equalTo(true));
    }

    /**
     * match: 匹配
     */
    @Test
    public void testMatch() {
        FluentIterable<String> fit = build();
        /**
         * allMatch: 全部满足
         */
        boolean b = fit.allMatch(item -> item != null && item.length() > 4);
        assertThat(b, equalTo(false));

        /**
         * anyMatch : 任意一个满足
         */
        boolean r = fit.anyMatch(item -> item != null && item.length() > 4);
        assertThat(r, equalTo(true));

        /**
         * firstMatch: 找到第一个满足条件的元素
         */
        Optional<String> optional = fit.firstMatch(item -> item != null && item.length() > 4);
        assertThat(optional.isPresent(),equalTo(true));
        assertThat(optional.get(),equalTo("python"));
    }


    /**
     * first：拿出第一个元素
     * last：最后一个元素
     */
    @Test
    public void testFirstAndLast(){
        /**
         * first:
         */
        Optional<String> first = build().first();
        assertThat(first.isPresent(),equalTo(true));
        assertThat(first.get(),equalTo("java"));

        /**
         * last
         */
        Optional<String> last = build().last();
        assertThat(last.isPresent(),equalTo(true));
        assertThat(last.get(),equalTo("javaScript"));
    }

    /**
     * limit: 截取前几个
     */
    @Test
    public void testLimit(){
        FluentIterable<String> fit = build();
        FluentIterable<String> limit = fit.limit(3);
        System.out.println(limit);
        assertThat(limit.size(),equalTo(3));
        assertThat(limit.get(0),equalTo("java"));
        assertThat(limit.get(1),equalTo("python"));
        assertThat(limit.get(2),equalTo("php"));
    }


    @Test
    public void testCopyInto(){
        FluentIterable<String> fit = build();

        ArrayList<String> strings = Lists.newArrayList("html", "Android", "css");

        List<String> result = fit.copyInto(strings);
        assertThat(result.size(),equalTo(8));
        assertThat(fit.size(),equalTo(5));
        assertThat(result.contains("css"),equalTo(true));
    }

    @Test
    public void testCycle(){
        FluentIterable<String> fit = build();
        FluentIterable<String> cycle = fit.cycle();
        // 会一直循环打印
        // cycle.forEach(System.out::println);
    }

    /**
     * java8 stream的map
     */
    @Test
    public void testTransform(){
        FluentIterable<String> fit = build();
        // 将String -> int
        fit.transform(item -> item.length()).forEach(System.out::println);
    }




}
