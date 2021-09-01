package com.iot.phoebus.service;

import com.iot.phoebus.service.impl.PaymentHystrixServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xinquan.w@phoebus-iot.com
 * @date 2021/9/1
 */
@RequestMapping("/payment")
@FeignClient(name = "cloud-payment-hystrix-service", fallback = PaymentHystrixServiceImpl.class)
public interface PaymentHystrixService {

    /**
     * 根据 ID 查找支付服务
     *
     * @param id      Long
     * @param timeout Integer
     * @return String
     */
    @GetMapping("/hystrix/{id}/{timeout}")
    String getPaymentById(@PathVariable("id") Long id, @PathVariable("timeout") Integer timeout);
}
