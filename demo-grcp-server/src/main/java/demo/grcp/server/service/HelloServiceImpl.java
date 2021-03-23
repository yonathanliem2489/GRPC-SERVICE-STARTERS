package demo.grcp.server.service;

import demo.grcp.server.HelloRequest;
import demo.grcp.server.HelloResponse;
import demo.grcp.server.HelloServiceGrpc.HelloServiceImplBase;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class HelloServiceImpl extends HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

      log.info("receive request {}", request.getFirstName().concat(" ")
          .concat(request.getLastName()));

      HelloResponse response = HelloResponse.newBuilder()
                .setGreeting("hai ".concat(request.getFirstName())
                    .concat(" ").concat(request.getLastName()))
                .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

}