package com.iot.phoebus.service.impl;

import com.iot.phoebus.dao.PaymentDao;
import com.iot.phoebus.entities.Payment;
import com.iot.phoebus.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xinquan.w
 * @date 2021/6/19
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public Long create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
