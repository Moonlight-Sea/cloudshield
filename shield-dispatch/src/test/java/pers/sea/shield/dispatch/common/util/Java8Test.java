package pers.sea.shield.dispatch.common.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author moon on 2024/5/10 by idea
 */
public class Java8Test {

    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 10, 15, 4, 7, 9, 11);
        list.sort(Comparator.naturalOrder());
        list.forEach(System.out::println);
    }



}
