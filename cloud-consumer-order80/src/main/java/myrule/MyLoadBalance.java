package myrule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xinquan.w
 * @date 2021/7/3
 */
@Slf4j
@Component
public class MyLoadBalance implements LoadBalance {

    private final AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public ServiceInstance chooseInstance(List<ServiceInstance> serviceInstanceList) {
        int index = getAndIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }

    /**
     * CAS 获取下一个值
     *
     * @return int
     */
    private int getAndIncrement() {
        int current;
        int next;

        do {
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!atomicInteger.compareAndSet(current, next));

        log.info("第{}次访问接口", next);

        return next;
    }
}
