package com.yzf.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan("com.yzf.springboot")
@MapperScan(basePackages = "com.yzf.springboot.**.mapper")
@ImportResource(value = {
        "classpath:spring.xml"
})

/*
@MapperScan仅扫描业务接口包，不能扫描本地通用Mapper接口包，
  否则报java.lang.ClassCastException: sun.reflect.generics.reflectiveObjects.TypeVariableImpl
        cannot be cast to java.lang.Class异常
*/
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
