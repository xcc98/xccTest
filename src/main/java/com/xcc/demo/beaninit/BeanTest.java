package com.xcc.demo.beaninit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.xcc.demo.beaninit")
public class BeanTest {

    /**
     * bean1使用 @Bean(initMethod="init",destroyMethod="destroy") 方式实现初始化和销毁
     */
    @org.springframework.context.annotation.Bean(initMethod="init",destroyMethod="destroy")
    Bean beanOne(){
        return new Bean();
    }
    
}
