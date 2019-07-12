package com.xcc.demo.Thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolTest {
    
    
    public static void main(String[] args) {

//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setThreadNamePrefix("check-deduct-executor");
//        executor.setMaxPoolSize(1);
//        executor.setCorePoolSize(1);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i= 0;i<10000;i++){
            executorService.execute(() -> {
                try {
                    System.out.println("测试");
                } catch (Exception e) {
                    log.error("扣款单明细异步核对出错", e);
                }
            });
        }
        
    }
}
