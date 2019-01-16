package com.xcc.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
@Order(11)
public class TestAOP1 {
    
    @Pointcut("execution(public * com.xcc.demo.aop.AopTestController.test())")
    public void test1(){}


    @Before("test1()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("test1 切入点前置方法");
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }
    @After("test1()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        log.info("test1 切入点后置方法");
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "test1()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("test1 RESPONSE : " + ret);
    }



    
//    @Before("com.shein.fmis.comm.supplier.controller.PointCut.pointcut()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        log.info("test1 切入点前置方法");
//        System.out.println(Arrays.toString(joinPoint.getArgs()));
//    }
}
