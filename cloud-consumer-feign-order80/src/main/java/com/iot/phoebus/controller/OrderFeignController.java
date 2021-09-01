package com.iot.phoebus.controller;

import com.iot.phoebus.entities.CommonResult;
import com.iot.phoebus.service.PaymentFeignService;
import com.iot.phoebus.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xinquan.w
 * @date 2021/7/14
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/{id}")
    public CommonResult<?> getOrderById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/feign/timeout")
    public Integer getServerPortTimeout() {
        return paymentFeignService.getServerPortTimeout();
    }

    @HystrixCommand(fallbackMethod = "getOrderByIdFallback", commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500"))
    @GetMapping("/{id}/{timeout}")
    public String getOrderById(@PathVariable("id") Long id, @PathVariable("timeout") Integer timeout) {
        return paymentHystrixService.getPaymentById(id, timeout);
    }

    private String getOrderByIdFallback(Long id, Integer timeout) {
        String response = Thread.currentThread().getName() + " getOrderByIdFallback(id=" + id + ",timeout=" + timeout + ")";
        log.warn(response);
        return response;
    }
}
