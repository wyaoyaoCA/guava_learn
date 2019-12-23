package study.wyy.guava.utilites;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-20 21:27
 * @description：
 * @modified By：
 * @version: $
 */
public class StringsTest {

    @Test
    public void testStringsMethod() {
        // 1 emptyToNull -> 将空串转为null
        assertThat(Strings.emptyToNull(""), nullValue());

        // 2 nullToEmpty -> 将null转为空串
        assertThat(Strings.nullToEmpty(null), equalTo(""));

        // 3 commonPrefix -> 返回两个字符串公共的前缀   commonSuffix -> 返回两个字符串公共的后缀
        assertThat(Strings.commonPrefix("Hello", "HTML5"), equalTo("H"));
        // 4 如果没有公共的前缀呢？-> 返回一个空串
        assertThat(Strings.commonPrefix("Hello", "World"), equalTo(""));

        // 5 Strings.repeat -> 平铺指定的字符串
        assertThat(Strings.repeat("Java", 3), equalTo("JavaJavaJava"));

        // 6 isNullOrEmpty -> 判断字符串是否为空或者为null
        assertThat(Strings.isNullOrEmpty(null), equalTo(true));
        assertThat(Strings.isNullOrEmpty(""), equalTo(true));

        // 7 padStart -> 如果指定字符串长度小于指定长度，则在其前面以指定的字符填充
        // padEnd -> 如果指定字符串长度小于指定长度，则在其后面以指定的字符填充
        assertThat(Strings.padStart("Java", 3, 'y'), equalTo("Java"));
        assertThat(Strings.padStart("Java", 5, 'y'), equalTo("yJava"));
    }


    @Test
    public void testCharsets() {
        // java提供的
        Charset charsetJava = Charset.forName("UTF-8");
        // guava
        Charset charsetGuava = Charsets.UTF_8;
        assertThat(charsetGuava, equalTo(charsetJava));
    }

    /**
     * 匹配字符的
     */
    @Test
    public void testCharMatcher() {

        // 1 判断是不是一个数字类型
        assertThat(CharMatcher.javaDigit().matches('4'), equalTo(true));
        assertThat(CharMatcher.javaDigit().matches('n'), equalTo(false));

        // 2 统计指定字符在指定字符串出现的个数
        assertThat(CharMatcher.is('o').countIn("HELLO world"),equalTo(1));

        // 3 使用指定字符替换空格（多个连续空格作为一个空格处理）
        assertThat(CharMatcher.breakingWhitespace().collapseFrom("     hello world ",'*'),equalTo("*hello*world*"));


        // 4 移除字符串中的数字和空格
        assertThat(
                CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 234 world"),
                equalTo("helloworld")
        );

        // 4 保留字符串中的数字和空格
        assertThat(
                CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello 234 world"),
                equalTo(" 234 ")
        );


    }




}
