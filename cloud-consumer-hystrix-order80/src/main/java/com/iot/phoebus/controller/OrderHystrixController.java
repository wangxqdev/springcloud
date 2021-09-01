package com.iot.phoebus.controller;

import com.iot.phoebus.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xinquan.w@phoebus-iot.com
 * @date 2021/9/1
 */
@Slf4j
@RestController
@RequestMapping("order")
@DefaultProperties(defaultFallback = "defaultFallback", commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500"))
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    //    @HystrixCommand(fallbackMethod = "getOrderByIdFallback", commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500"))
    @HystrixCommand
    @GetMapping("/{id}/{timeout}")
    public String getOrderById(@PathVariable("id") Long id, @PathVariable("timeout") Integer timeout) {
        return paymentHystrixService.getPaymentById(id, timeout);
    }

    private String getOrderByIdFallback(Long id, Integer timeout) {
        String response = Thread.currentThread().getName() + " getOrderByIdFallback(id=" + id + ",timeout=" + timeout + ")";
        log.warn(response);
        return response;
    }

    /**
     * 默认无参
     *
     * @return String
     */
    private String defaultFallback() {
        String response = Thread.currentThread().getName() + " defaultFallback()";
        log.warn(response);
        return response;
    }
}
