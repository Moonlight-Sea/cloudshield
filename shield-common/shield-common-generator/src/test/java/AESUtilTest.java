import com.baomidou.mybatisplus.core.toolkit.AES;
import org.junit.jupiter.api.Test;

/**
 * AES test
 *
 * @author moon on 12/7/2023
 */
public class AESUtilTest {

    @Test
    public void case1() {
        String url = "jdbc:mysql://127.0.0.1:3306/shield_bpm?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
        String user = "root";
        String password = "123456";
        // String randomKey = AES.generateRandomKey();
        String randomKey = "2f9a1c7026c2eee1";
        System.out.println(randomKey);

        String encrypt = AES.encrypt(url, randomKey);
        System.out.println(encrypt);

        String encryptUser = AES.encrypt(user, randomKey);
        System.out.println(encryptUser);

        String encryptPwd = AES.encrypt(password, randomKey);
        System.out.println(encryptPwd);
    }
}
