package com.iot.phoebus;

import myrule.MyLoadBalance;
import myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Import;

/**
 * @author xinquan.w
 * @date 2021/6/20
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "cloud-payment-service", configuration = MyselfRule.class)
@Import(MyLoadBalance.class)
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
