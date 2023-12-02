import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author moon on 2023/11/27
 */
public class TestReg {

    @Test
    public void test() {
        String reg = "test.data.flag";
        Pattern pattern = Pattern.compile(reg);

        String path = "test.data.flag";
        System.out.println(pattern.matcher(path).matches());
    }

    @Test
    void test1() {
        List<String> list = Arrays.asList("a.txt", "b.txt", "c.txt");
        String[] array = list.toArray(new String[0]);
        for (String s : array) {
            System.out.println(s);
        }
    }


    @Test
    void test2() {
        String data = "COMOA_ASDFSADF_SADFA > 0 AND ASDFSADF_sADFASDF_AS < 1";
        String charReg = "([a-zA-z1-9]+_)+[a-zA-z1-9]+";
        // 创建模式对象
        Pattern pattern = Pattern.compile(charReg);
        // 创建匹配器对象
        Matcher matcher = pattern.matcher(data);

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }

        matches.forEach(i -> System.out.println(i + ","));
    }


    @Test
    void test3() {
        String data = "a,  b ,  c    ,   d  ";
        Set<String> set = new HashSet<>(Arrays.asList(data.trim().split("( )*,( )*")));
        set.forEach(i -> System.out.println(i + ","));
    }
}
