package demo.grpc.reactive.service;

import demo.grpc.reactive.server.proto.ExampleRequest;
import demo.grpc.reactive.server.proto.ExampleResponse;
import demo.grpc.reactive.server.proto.ReactorExampleServiceGrpc.ReactorExampleServiceStub;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class DefaultDemoClientService implements DemoClientService {

  private final ReactorExampleServiceStub stub;

  public DefaultDemoClientService(ReactorExampleServiceStub stub) {
    this.stub = stub;
  }

  @Override
  public Mono<List<String>> handle(List<String> ids) {

    Flux<ExampleResponse> flux = stub.handle(toMessage(ids));

    return flux.doOnNext(response -> log.info("receive response id {}", response.getId()))
        .map(ExampleResponse::getId)
        .collectList()
        .doOnError(throwable -> log.error("problem occurred {}", throwable.getMessage()));
  }

  private Mono<ExampleRequest> toMessage(List<String> ids) {
    return Mono.just(ExampleRequest.newBuilder()
        .addAllIds(ids)
        .build());
  }
}
