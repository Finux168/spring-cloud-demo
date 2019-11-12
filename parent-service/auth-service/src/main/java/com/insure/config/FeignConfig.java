package com.insure.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign配置
 *
 * @Author: fgd
 * @Date: 2019-11-12
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        //四种级别中,只有full会打印出响应,所以开启full
        return Logger.Level.FULL;
    }
}
