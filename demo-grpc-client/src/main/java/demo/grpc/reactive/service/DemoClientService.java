package demo.grpc.reactive.service;

import java.util.List;
import reactor.core.publisher.Mono;

public interface DemoClientService {

  Mono<List<String>> handle(List<String> ids);
}
