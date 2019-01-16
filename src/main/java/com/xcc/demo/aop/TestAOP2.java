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
@Order(10)
public class TestAOP2 {
    @Pointcut("execution(public * com.xcc.demo.aop.AopTestController.test())")
    public void test2(){}

    @Before("test2()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("test2 切入点前置方法");
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }
    @After("test2()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        log.info("test2 切入点后置方法");
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(returning = "ret", pointcut = "test2()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("test2  RESPONSE : " + ret);
    }


}
