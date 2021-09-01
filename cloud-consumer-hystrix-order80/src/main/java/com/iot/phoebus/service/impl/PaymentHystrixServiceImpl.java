package com.iot.phoebus.service.impl;

import com.iot.phoebus.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xinquan.w@phoebus-iot.com
 * @RequestMapping: bug
 * @date 2021/9/1
 */
@Slf4j
@Service
@RequestMapping("/payment/fallback")
public class PaymentHystrixServiceImpl implements PaymentHystrixService {

    @Override
    public String getPaymentById(Long id, Integer timeout) {
        String response = Thread.currentThread().getName() + " getPaymentByIdFallback(id=" + id + ",timeout=" + timeout + ")";
        log.warn(response);
        return response;
    }
}
