package com.xcc.demo.beaninit;

public class Bean1 {
    public void init(){
        System.out.println("@Bean-init-method-----Bean1在构造函数执行完之后执行");
    }
    
    public Bean1() {
        super();
        System.out.println("初始化构造函数-Bean1");
    }
    
    public void destroy(){
        System.out.println("@Bean-destory-method------Bean1在bean销毁之前执行");
    }
}
