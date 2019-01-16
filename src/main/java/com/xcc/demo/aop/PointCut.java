package com.xcc.demo.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class PointCut {

    @Pointcut("execution(public * com.xcc.demo.aop.AopTestController.test())")
    public void pointcut(){}
    
}
