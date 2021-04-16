package demo.grpc.reactive;

import demo.grpc.reactive.server.proto.ReactorExampleServiceGrpc;
import demo.grpc.reactive.service.DefaultDemoClientService;
import demo.grpc.reactive.service.DemoClientService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

  @Bean
  DemoClientService demoClientService(ManagedChannel demoChannel) {
    ReactorExampleServiceGrpc.ReactorExampleServiceStub stub =
        ReactorExampleServiceGrpc.newReactorStub(demoChannel);
    return new DefaultDemoClientService(stub);
  }

  @Bean
  ManagedChannel demoChannel() {
    ManagedChannel channel = ManagedChannelBuilder
        .forAddress("localhost", 9099)
        .usePlaintext()
        .idleTimeout(3, TimeUnit.SECONDS)
        .keepAliveTimeout(5, TimeUnit.SECONDS)
        .build();
    return channel;
  }
}
