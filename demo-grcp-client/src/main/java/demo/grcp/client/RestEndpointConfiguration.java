package demo.grcp.client;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class RestEndpointConfiguration {

  @Bean
  DemoClientService demoClientService() {
    return new DemoClientService();
  }

  @Bean
  RouterFunction<ServerResponse> demoClient(DemoClientService clientService) {

    return route(RequestPredicates.GET("grcp-client"), request ->
        Mono.fromCallable(clientService::receiveGreeting)
          .flatMap(helloResponse -> ServerResponse.ok()
              .bodyValue(Response.builder()
                  .greeting(helloResponse.getGreeting())
                  .build()))
    );
  }

  @Getter
  @ToString
  @EqualsAndHashCode
  public static class Response implements Serializable {

    private String greeting;

    @JsonCreator
    @lombok.Builder(builderClassName = "Builder")
    Response(@JsonProperty  String greeting) {
      this.greeting = greeting;
    }
  }
}
