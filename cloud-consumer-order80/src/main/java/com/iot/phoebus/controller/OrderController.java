package com.iot.phoebus.controller;

import com.iot.phoebus.entities.CommonResult;
import com.iot.phoebus.entities.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xinquan.w
 * @date 2021/6/20
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final String PAYMENT_URL = "http://cloud-payment-service/payment/";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping
    public CommonResult<?> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL, payment, CommonResult.class);
    }

    @GetMapping("/{id}")
    public CommonResult<?> getPaymentById(@PathVariable("id") String id) {
//        getForObject
//        return restTemplate.getForObject(PAYMENT_URL + id, CommonResult.class);
//        getForEntity
        return restTemplate.getForEntity(PAYMENT_URL + id, CommonResult.class).getBody();
    }
}
