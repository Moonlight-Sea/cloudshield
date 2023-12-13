package pers.sea.shield.dispatch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 快速派遣服务
 *
 * @author moon on 6/13/2023
 */
@SpringBootApplication
@MapperScan("pers.sea.shield.dispatch.mapper")
public class DispatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DispatchApplication.class, args);
    }
}
