/**
 * @Title: ArcheSheinConfig
 * @Package com.shein.pss.config
 * @Description: 应用配置
 * @author: zhangpeng
 * @date: 2018/1/18
 * @version: V1.0
 */
package com.xcc.comm.util;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableSwagger2Doc
public class AsyncThreadConfig {

    @Configuration
    static class WebMvcConfiguration extends WebMvcConfigurerAdapter {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new HandlerUtil());
        }
    }

    /**
     * 异步线程
     *
     * @return
     */
    @Bean
    public AsyncTaskExecutor recountMonthInfoPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("check-1");
        executor.setMaxPoolSize(20);
        executor.setCorePoolSize(10);
        return executor;
    }
    

    /**
     * 自定义单核对线程池
     *
     * @return executorPool
     */
    @Bean
    public ThreadPoolTaskExecutor checkExecutorPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("check");
        executor.setMaxPoolSize(1);
        executor.setCorePoolSize(1);
        return executor;
    }
}
