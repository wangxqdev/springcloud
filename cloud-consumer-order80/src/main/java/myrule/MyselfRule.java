package myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xinquan.w
 * @date 2021/7/3
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}
