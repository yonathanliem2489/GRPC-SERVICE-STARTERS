package demo.grpc.reactive;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

public class TestingConfiguration {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(30))
                .build();
    }
}
