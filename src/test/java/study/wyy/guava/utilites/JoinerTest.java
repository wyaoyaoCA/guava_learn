package study.wyy.guava.utilites;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.hamcrest.core.IsSame;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-18 20:58
 * @description：
 * @modified By：
 * @version: $
 */
public class JoinerTest {

    private final List<String> stringList = Arrays.asList("Google","Guava","Java","Scala","Kafka");

    private final List<String> stringListWithNullValue = Arrays.asList("Google","Guava","Java","Scala",null);

    private final String targrtFileName = "/Users/wyaoyao/ideaWorkSpace/guava_learn/src/main/resources/guava-joiner.txt";

    private final String targrtFileNameWithMap = "/Users/wyaoyao/ideaWorkSpace/guava_learn/src/main/resources/guava-joiner-map.txt";

    private final Map<String, String> stringMap = ImmutableMap.of("Hello", "Guava", "Java", "Scala");



    /****************************JOIN***********************************/

    @Test
    public void testJoinOnJoin(){
        String result = Joiner.on("#").join(stringList);
        assertThat(result,equalTo("Google#Guava#Java#Scala#Kafka"));
    }

    @Test(expected = NullPointerException.class)
    public void testJoinOnJoinWithNullValue(){
        String result = Joiner.on("#").join(stringListWithNullValue);
        assertThat(result,equalTo("Google#Guava#Java#Scala"));
    }

    /**
     * 跳过null值
     */
    @Test
    public void testJoinOnJoinWithNullValueButSkip(){
        String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        assertThat(result,equalTo("Google#Guava#Java#Scala"));
    }

    /**
     * 将null值用一个默认的值替换
     */
    @Test
    public void testJoin_On_Join_WithNullValue_UserDefaultyValue(){
        String result = Joiner.on("#").useForNull("HTML5").join(stringListWithNullValue);
        assertThat(result,equalTo("Google#Guava#Java#Scala#HTML5"));
    }


    /****************************append**************************
     *  append:append到一个appendable（StringBuffer，BufferReader，BufferWriter，StringBuilder）
     * ***********************************************************/

    @Test
    public void testJoin_On_Append_TO_StringBuilder(){
        final StringBuilder builder = new StringBuilder();
        // appendTo:泛型。给入什么返回什么，这里传入的是一个StringBuilder，返回的也是一个StringBuider
        StringBuilder resultBuilder = Joiner.on("#").useForNull("HTML5").appendTo(builder, stringListWithNullValue);

        // 发现虽然是返回，但是这个返回值和我们传入是同一个实例，并不是返回一个新的StringBuilder给我们
        assertThat(resultBuilder, IsSame.sameInstance(builder));
        assertThat(resultBuilder.toString(),equalTo("Google#Guava#Java#Scala#HTML5"));
    }



    @Test
    public void testJoin_On_Append_TO_Writer(){
        try (FileWriter writer = new FileWriter(new File(targrtFileName))){
            FileWriter fileWriter = Joiner.on("#").useForNull("HTML5").appendTo(writer, stringListWithNullValue);
            assertThat(Files.isFile().test(new File(targrtFileName)),equalTo(true));
        } catch (IOException e) {
           fail("append to writer occur fetal error cause by " + e.getMessage());
        }
    }


    /*********************使用java8****************/

    @Test
    public void testJoinByStreamSkipNull(){
        String result = stringListWithNullValue.stream().filter(item -> item != null && !item.isEmpty()).collect(Collectors.joining("#"));
        assertThat(result,equalTo("Google#Guava#Java#Scala"));

    }

    @Test
    public void testJoinByStreamWithDefaultValue(){
        String result = null;
//       result = stringListWithNullValue.stream().
//                map(item -> item == null || item.isEmpty() ? "HTML5" : item).collect(Collectors.joining("#"));
        result = stringListWithNullValue.stream().map(this::defaultValue).collect(Collectors.joining("#"));
        assertThat(result,equalTo("Google#Guava#Java#Scala#HTML5"));

    }

    private String defaultValue(final String item){
        return item == null || item.isEmpty() ? "HTML5" : item;
    }


    @Test
    public void testJoinOnWithMap(){
        String result = Joiner.on("#").withKeyValueSeparator("->").join(stringMap);
        assertThat(result,equalTo("Hello->Guava#Java->Scala"));

    }


    @Test
    public void testJoinOnWithMapTAppendable(){
        try (FileWriter fileWriter = new FileWriter(new File(targrtFileNameWithMap));){
            Joiner.on("#").withKeyValueSeparator("->").appendTo(fileWriter,stringMap);
            assertThat(Files.isFile().test(new File(targrtFileNameWithMap)),equalTo(true));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
