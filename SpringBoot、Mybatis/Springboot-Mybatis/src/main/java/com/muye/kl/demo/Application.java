package com.muye.kl.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author gwh
 */

@SpringBootApplication
@EnableCaching
@EnableWebMvc
@EnableAsync
@MapperScan(basePackages="com.bonc.**.mapper")
@ComponentScan(
        basePackages = {"com.muye.kl.demo","com.bonc.industry.**"}
        )
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
