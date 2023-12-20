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
    // String randomKey = AES.generateRandomKey();
    // System.out.println(randomKey);
    String encrypt = AES.encrypt(url, "");
    System.out.println(encrypt);
  }
}
