package study.wyy.guava.utilites;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-18 22:17
 * @description：
 * @modified By：
 * @version: $
 */
public class SplitterTest {


    /**
     * 按照指定字符切分字符串
     */
    @Test
    public void testSplitOnSplit(){
        List<String> result = Splitter.on("|").splitToList("hello|world");
        assertThat(result,notNullValue());
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0), equalTo("hello"));
        assertThat(result.get(1), equalTo("world"));
    }

    /**
     * 忽略empty
     */
    @Test
    public void testSplit_On_Split_OmitEmpty(){
        List<String> result = Splitter.on("|").splitToList("hello|world|||");
        assertThat(result,notNullValue());
        assertThat(result.size(), equalTo(5));

        result = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
        assertThat(result,notNullValue());
        assertThat(result.size(), equalTo(2));
    }


    /**
     * 去掉首尾空格
     */
    @Test
    public void testSplit_On_Split_OmitEmpty_TrimResult(){
        List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello | world|||");
        assertThat(result,notNullValue());
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0), equalTo("hello "));
        assertThat(result.get(1), equalTo(" world"));

        result = Splitter.on("|").omitEmptyStrings().trimResults().splitToList("hello | world|||");
        assertThat(result,notNullValue());
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0), equalTo("hello"));
        assertThat(result.get(1), equalTo("world"));

    }


    /***
     * aaaabbbbccccdddd 每4个拆分
     */
    @Test
    public void testSplitFixLength(){
        List<String> list = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(4));
        assertThat(list.get(0),equalTo("aaaa"));
        assertThat(list.get(1),equalTo("bbbb"));
        assertThat(list.get(2),equalTo("cccc"));
        assertThat(list.get(3),equalTo("dddd"));
    }

    /**
     * 分割字符串，limit
     */
    @Test
    public void testSplitOnSplitLimit(){
        List<String> list = Splitter.on("#").limit(3).splitToList("hello#world#java#google#scala");
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(3));
        assertThat(list.get(0),equalTo("hello"));
        assertThat(list.get(1),equalTo("world"));
        assertThat(list.get(2),equalTo("java#google#scala"));
    }


    /**
     * 根据正则表达式进行分割
     */
    @Test
    public void testSplitOnPatternString(){
        List<String> list = Splitter.onPattern("\\|").trimResults().omitEmptyStrings().splitToList("hello | world|||");
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(2));
        assertThat(list.get(0),equalTo("hello"));
        assertThat(list.get(1),equalTo("world"));
    }


    /**
     * 放到map中
     */
    @Test
    public void testSpiltOnSplitToMap(){
        Map<String, String> map = Splitter.on("|").trimResults().omitEmptyStrings().withKeyValueSeparator("=").
                split("hello=HELLO| world=WORLD|||");
        System.out.println(map);
        assertThat(map,notNullValue());
        assertThat(map.get("hello"),equalTo("HELLO"));
        assertThat(map.get("world"),equalTo("WORLD"));
    }
}
