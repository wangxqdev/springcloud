package com.iot.phoebus.controller;

import com.iot.phoebus.entities.CommonResult;
import com.iot.phoebus.entities.Payment;
import myrule.LoadBalance;
import myrule.MyLoadBalance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

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

    @Resource
    private LoadBalance loadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping
    public CommonResult<?> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL, payment, CommonResult.class);
    }

    @GetMapping("/{id}")
    public CommonResult<?> getPaymentById(@PathVariable("id") Long id) {
//        getForObject
//        return restTemplate.getForObject(PAYMENT_URL + id, CommonResult.class);
//        getForEntity
        return restTemplate.getForEntity(PAYMENT_URL + id, CommonResult.class).getBody();
    }

    @GetMapping("/server/port")
    public Integer getServerPort() {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("cloud-payment-service");
        if (serviceInstanceList.isEmpty()) {
            return -1;
        }
        ServiceInstance serviceInstance = loadBalance.chooseInstance(serviceInstanceList);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/server/port", Integer.class);
    }
}
