package demo.server.service;

import demo.grpc.reactive.server.proto.ExampleRequest;
import demo.grpc.reactive.server.proto.ReactorExampleServiceGrpc;
import demo.server.config.DemoReactiveServiceConfiguration;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import simultan.team.libs.grpc.server.config.GRPCConfiguration;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = TestingConfiguration.class, properties = {
    "simultan.team.libs.grpc.server.port=2000",
    "simultan.team.libs.grpc.server.shutdownWait=1000"
})
@ImportAutoConfiguration({GRPCConfiguration.class, DemoReactiveServiceConfiguration.class})
public class ExampleServiceIT {

  private ManagedChannel channel;

  @BeforeEach
  void setUp() {
    // Connect to the sever
    this.channel = ManagedChannelBuilder
        .forAddress("localhost", 2000).usePlaintext()
        .build();
  }

  @Test
  void whenRequestExample_thenShouldSuccess() {

    ReactorExampleServiceGrpc.ReactorExampleServiceStub stub =
            ReactorExampleServiceGrpc.newReactorStub(channel)
            .withDeadlineAfter(1000, TimeUnit.SECONDS);

    StepVerifier.create(stub.handle(ExampleRequest.newBuilder()
          .addAllIds(Arrays.asList("1", "2"))
          .build()).collectList())
        .expectSubscription().thenAwait()
        .expectNextMatches(exampleResponses -> {
          Assertions.assertEquals(2, exampleResponses.size());
          return true;
        })
        .expectComplete()
        .verify(Duration.ofSeconds(5));
  }
}

