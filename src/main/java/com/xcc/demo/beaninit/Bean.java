package com.xcc.demo.beaninit;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Bean implements InitializingBean, DisposableBean  {
    public Bean() {
        super();
        System.out.println("@Bean-init-method：初始化构造函数");
    }
    
    @PostConstruct
    public void init1(){
        System.out.println("@Bean-init-method：postConstruct");
    }
    
    public void init(){
        System.out.println("@Bean-init-method：init-method");
    }
    
    @Override
    public void afterPropertiesSet() {
        System.out.println("@Bean-init-method：afterPropertiesSet");
    }


    @PreDestroy
    public void destroy1(){
        System.out.println("@Bean-destory-method：PreDestroy");
    }
    
    @Override
    public void destroy(){
        System.out.println("@Bean-destory-method：destroy");
    }
    
}
