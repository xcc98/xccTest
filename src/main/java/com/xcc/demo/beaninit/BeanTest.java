package com.xcc.demo.beaninit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.xcc.demo.beaninit")
public class BeanTest {

    /**
     * bean1使用 @Bean(initMethod="init",destroyMethod="destroy") 方式实现初始化和销毁
     */
    @Bean(initMethod="init",destroyMethod="destroy") 
    Bean1 beanOne(){
        return new Bean1();
    }
    
    /**
     * bean2使用 @PostConstruct、 @PreDestroy 方式实现初始化和销毁
     */
    @Bean
    Bean2 beanTwo(){
        return new Bean2();
    }
    
}
