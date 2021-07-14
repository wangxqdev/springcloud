package com.iot.phoebus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xinquan.w
 * @date 2021/7/14
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @GetMapping("/hystrix/{id}")
    public String getPaymentById(@PathVariable("id") Long id) {
        String response = Thread.currentThread().getName() + " getPaymentById(id=" + id + ")";
        log.info(response);
        return response;
    }

    @GetMapping("/hystrix/{id}/{timeout}")
    public String getPaymentById(@PathVariable("id") Long id, @PathVariable("timeout") Integer timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            log.warn("InterruptedException", e);
        }

        String response = Thread.currentThread().getName() + " getPaymentById(id=" + id + ",timeout=" + timeout + ")";
        log.info(response);
        return response;
    }
}
