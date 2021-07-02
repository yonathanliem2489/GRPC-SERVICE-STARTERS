package demo.server.service;

import simultan.team.libs.grpc.server.annotation.GRPCService;
import demo.grpc.reactive.server.proto.ExampleRequest;
import demo.grpc.reactive.server.proto.ExampleResponse;
import demo.grpc.reactive.server.proto.ReactorExampleServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@GRPCService
public class ExampleService extends ReactorExampleServiceGrpc.ExampleServiceImplBase {

  @Override
  public Flux<ExampleResponse> handle(Mono<ExampleRequest> requestMono) {
    return requestMono
        .doOnSuccess(request -> log.info("receive request {}", request.toString()))
        .flatMapMany(request -> Flux.fromIterable(request.getIdsList())
          .map(id -> ExampleResponse.newBuilder()
              .setId(id)
              .build()));
  }
}
