package pers.sea.shield.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.query.SQLQuery;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * MybatisPlus代码生成器
 *
 * @author moon on 2023/11/24
 */
public class MyBatisPlusGenerator {

    public static final String OUTPUT_DIR = "D://Users/moon/Data";
    public static final List<String> TABLE_PREFIX = List.of("batch_");
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/shield_batch";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";
    public static final String AUTHOR = "moon";
    public static final String PACKAGE_NAME = "pers.sea.shield";
    public static final String MODEL_NAME = "batch";
    public static final String MAPPER_PACKAGE_PATH = OUTPUT_DIR + File.separator + PACKAGE_NAME.replace(".", "/") + "/Mapper";


    /**
     * 代码生成
     */
    public static void main(String[] args) {


        FastAutoGenerator.create(URL, USERNAME, PASSWORD).globalConfig(builder -> {
                    builder.author(AUTHOR) // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir()
                            .outputDir(OUTPUT_DIR); // 指定输出目录
                })
                // MYSQL 示例 切换至SQL查询方式,需要指定好`dbQuery`与`typeConvert`来生成
                .dataSourceConfig(builder -> builder.databaseQueryClass(SQLQuery.class).typeConvert(new MySqlTypeConvert()).dbQuery(new MySqlQuery())).packageConfig(builder -> {
                    builder.parent(PACKAGE_NAME) // 设置父包名
                            .moduleName(MODEL_NAME) // 设置父包模块名
                            .entity("pojo.entity") // 设置实体类包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_PACKAGE_PATH)); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    builder
                            .addTablePrefix(TABLE_PREFIX) // 设置过滤表前缀
                            // .addInclude("t_simple") // 设置需要生成的表名
                            .entityBuilder()
                            .disableSerialVersionUID() // 禁用序列号
                            // .enableChainModel() // 开启链式模型
                            .enableLombok() // 开启lombok模型
                            // .enableColumnConstant() // 开启字段常量
                            .enableTableFieldAnnotation() // 开启表字段注解
                            .enableFileOverride() // 覆盖已生成文件

                            .mapperBuilder()
                            .enableBaseResultMap() // 启用BaseResultMap生成
                            .enableBaseColumnList() // 启用BaseColumnList生成
                            .enableFileOverride() // 覆盖已生成文件

                            .serviceBuilder()
                            .enableFileOverride() // 覆盖已生成文件

                            .controllerBuilder()
                            .enableFileOverride() // 启用BaseController接口
                    ;
                }).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
