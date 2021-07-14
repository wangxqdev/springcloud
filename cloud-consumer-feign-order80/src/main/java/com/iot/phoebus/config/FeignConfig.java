package com.iot.phoebus.config;

import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xinquan.w
 * @date 2021/7/14
 */
@ConditionalOnProperty(prefix = "feign.client.config.default", name = "loggerLevel", havingValue = "false")
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }
}
