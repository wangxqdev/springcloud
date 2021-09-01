package com.iot.phoebus.controller;

import com.iot.phoebus.entities.CommonResult;
import com.iot.phoebus.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xinquan.w
 * @date 2021/7/14
 */
@RestController
@RequestMapping("order")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/{id}")
    public CommonResult<?> getOrderById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/feign/timeout")
    public Integer getServerPortTimeout() {
        return paymentFeignService.getServerPortTimeout();
    }
}
