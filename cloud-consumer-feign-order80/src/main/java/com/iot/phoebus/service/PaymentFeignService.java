package com.iot.phoebus.service;

import com.iot.phoebus.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xinquan.w
 * @date 2021/7/14
 */
@RequestMapping("/payment")
@FeignClient(name = "cloud-payment-service")
public interface PaymentFeignService {

    /**
     * 根据 ID 查找
     *
     * @param id Long
     * @return CommonResult&lt;?&gt;
     */
    @GetMapping("/{id}")
    CommonResult<?> getPaymentById(@PathVariable("id") Long id);

    /**
     * 超时获取端口号
     *
     * @return Integer
     */
    @GetMapping("/feign/timeout")
    Integer getServerPortTimeout();
}
