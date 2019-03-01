package com.xcc.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
//@Order(2)
public class AroundAOP {

    @Pointcut("execution(public * com.xcc.demo.aop.AopTestController.test())")
    public void aroundTest(){}
    
    @Around("aroundTest()")
    public Object around(ProceedingJoinPoint pjp) throws  Throwable{
        log.info("[Aspect1] around advise 1");
        Object o =  pjp.proceed();
        log.info("[Aspect1] around advise2");
        return o;
    }
    
}
