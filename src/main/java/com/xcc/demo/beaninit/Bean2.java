package com.xcc.demo.beaninit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Bean2 {
    @PostConstruct //1在构造函数执行完之后执行
    public void init(){
        System.out.println("jsr250-init-method-----2在构造函数执行完之后执行");
    }
    
    public Bean2() {
        super();
        System.out.println("初始化构造函数-Bean2");
    }
    
    @PreDestroy //2在bean销毁之前执行
    public void destroy(){
        System.out.println("jsr250-destory-method-------2在bean销毁之前执行");
    }
}
