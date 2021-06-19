package com.iot.phoebus.dao;

import com.iot.phoebus.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xinquan.w
 * @date 2021/6/19
 */
@Mapper
public interface PaymentDao {

    Long create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
