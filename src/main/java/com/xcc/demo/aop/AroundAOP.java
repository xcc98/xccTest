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


    /**
     * 声明一个前置通知
     */
    @Before("aroundTest()")
    public void beforeAdvide(JoinPoint point){
        log.info("around  Before() 触发了前置通知！");
    }

    /**
     * 声明一个后置通知
     */
    @After("aroundTest()")
    public void afterAdvie(JoinPoint point){
        log.info("around After() 触发了后置通知，抛出异常也会被触发！");
    }

    /**
     * 声明一个返回后通知
     */
    @AfterReturning(pointcut="aroundTest()", returning="ret")
    public void afterReturningAdvice(JoinPoint point, Object ret){
        log.info("around AfterReturning() 触发了返回后通知，抛出异常时不被触发，返回值为：" + ret);
    }

    /**
     * 声明一个异常通知
     */
    @AfterThrowing(pointcut="aroundTest()", throwing="throwing")
    public void afterThrowsAdvice(JoinPoint point, RuntimeException throwing){
        log.info("around AfterThrowing() 触发了异常通知，抛出了异常！");
    }
    
    
    
    @Around("aroundTest()")
    public Object around(ProceedingJoinPoint pjp) throws  Throwable{
        log.info("[Aspect1] around advise 1");
        Object o =  pjp.proceed();
        log.info("[Aspect1] around advise2");
        return o;
    }
    
    
}
