package com.iot.phoebus.service;

import com.iot.phoebus.entities.Payment;

/**
 * @author xinquan.w
 * @date 2021/6/19
 */
public interface PaymentService {

    Long create(Payment payment);

    Payment getPaymentById(Long id);
}
