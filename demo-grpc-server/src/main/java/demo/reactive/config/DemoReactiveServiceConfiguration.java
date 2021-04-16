package demo.reactive.config;

import simultan.team.libs.grpc.server.config.GRPCConfiguration;
import demo.reactive.service.ExampleService;
import io.grpc.BindableService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(GRPCConfiguration.class)
public class DemoReactiveServiceConfiguration {

  @Bean
  BindableService exampleServiceImplBase() {
    return new ExampleService();
  }
}
