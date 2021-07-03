package myrule;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author xinquan.w
 * @date 2021/7/3
 */
public interface LoadBalance {

    ServiceInstance chooseInstance(List<ServiceInstance> serviceInstances);
}
