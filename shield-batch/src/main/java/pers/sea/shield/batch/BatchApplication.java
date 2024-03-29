package pers.sea.shield.batch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 批量模块启动类
 *
 * @author moon on 2023/11/20
 */
@SpringBootApplication
@MapperScan("pers.sea.shield.batch.mapper")
public class BatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

}