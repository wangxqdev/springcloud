package com.iot.phoebus.controller;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xinquan.w
 * @date 2021/7/14
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @GetMapping("/hystrix/{id}")
    public String getPaymentById(@PathVariable("id") Long id) {
        String response = Thread.currentThread().getName() + " getPaymentById(id=" + id + ")";
        log.info(response);
        return response;
    }

    @HystrixCommand(fallbackMethod = "getPaymentByIdFallback", commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"))
    @GetMapping("/hystrix/{id}/{timeout}")
    public String getPaymentById(@PathVariable("id") Long id, @PathVariable("timeout") Integer timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            log.warn("InterruptedException", e);
        }

        String response = Thread.currentThread().getName() + " getPaymentById(id=" + id + ",timeout=" + timeout + ")";
        log.info(response);
        return response;
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    @GetMapping("/hystrix/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id) {
        if (id < 0) {
            throw new RuntimeException("id「" + id + "」不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + " paymentCircuitBreaker(id=" + id + ") serialNumber=" + serialNumber;
    }

    private String getPaymentByIdFallback(Long id, Integer timeout) {
        String response = Thread.currentThread().getName() + " getPaymentByIdFallback(id=" + id + ",timeout=" + timeout + ")";
        log.warn(response);
        return response;
    }

    private String paymentCircuitBreakerFallback(Long id) {
        String response = Thread.currentThread().getName() + " getPaymentByIdFallback(id=" + id + ")";
        log.warn(response);
        return response;
    }
}
