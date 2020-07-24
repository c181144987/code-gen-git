package com.xiyuanping.gen.codegen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiyuanping
 * @date 1992-10-01 12:50:23
 */
@SpringBootApplication
@MapperScan("com.xiyuanping.gen.codegen.mapper")
public class CodeGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeGenApplication.class, args);
    }
}
