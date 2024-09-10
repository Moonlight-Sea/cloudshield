package pers.sea.shield.store.common.util;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import pers.sea.shield.store.common.enums.IndustryEnum;

/**
 * @author moon on 2024/5/16 by idea
 */
public class UserTest {


    @Test
    public void test() {
        String str = "F5165";
        if (Pattern.compile("^[A-Z]\\d+$").matcher(str).matches()) {
            str = str.substring(1);
        }
        IndustryEnum byCategory = IndustryEnum.getByCategory(str);
        if (byCategory == null) {
            System.out.println("未找到行业");
            return;
        }
        System.out.println(byCategory.getName());
    }



}
