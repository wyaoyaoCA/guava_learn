package study.wyy.guava.utilites;

import com.google.common.base.Preconditions;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-20 20:44
 * @description： Precondtion: 做断言的
 * @modified By：
 * @version: $
 */
public class PreconditionTest {
    public static void main(String[] args) {
        String s = null;
        String s1 = String.valueOf(null);
        Preconditions.checkNotNull("hello","hsahdkashld ", 12);
        //System.out.println(s1);

    }
}
