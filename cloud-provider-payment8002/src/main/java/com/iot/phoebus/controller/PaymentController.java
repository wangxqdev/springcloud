package com.iot.phoebus.controller;

import com.iot.phoebus.entities.CommonResult;
import com.iot.phoebus.entities.Payment;
import com.iot.phoebus.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xinquan.w
 * @date 2021/6/19
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private Integer serverPort;

    @PostMapping
    public CommonResult<?> create(@RequestBody Payment payment) {
        Long id = paymentService.create(payment);
        if (id > 0) {
            return new CommonResult<>(200, "新增成功, 端口: " + serverPort, id);
        } else {
            return new CommonResult<>(444, "新增失败", payment);
        }
    }

    @GetMapping("/{id}")
    public CommonResult<?> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (null != payment) {
            return new CommonResult<>(200, "查询成功, 端口: " + serverPort, payment);
        } else {
            return new CommonResult<>(444, "查询失败", id);
        }
    }
}
