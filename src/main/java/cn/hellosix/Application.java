package cn.hellosix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by lzz on 2018/10/1.
 */
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages={"cn.hellosix"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}