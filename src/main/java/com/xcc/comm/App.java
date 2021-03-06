package com.xcc.comm;

import com.xcc.comm.util.ApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {"com.xcc"})
public class App 
{
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
        ApplicationContextUtil.initApplicationContext(ctx);
    }
}
