package com.self;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.DatabaseMetaData;
import java.util.Collections;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.self.*.mapper")
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class);
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/test_user", "root", "@root1234")
                .globalConfig(builder -> {
                    builder.author("H-lj") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Volumes/volute/code/self/test/mybatis_homework/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.self") // 设置父包名
                            .moduleName("manager") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Volumes/volute/code/self/test/mybatis_homework/src/main/java/com/self/manager/mapper/xml")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user"); // 设置需要生成的表名
             //               .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}