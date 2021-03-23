package demo.grcp.client;

import demo.grcp.server.HelloRequest;
import demo.grcp.server.HelloResponse;
import demo.grcp.server.HelloServiceGrpc.HelloServiceBlockingStub;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;


@Slf4j
public class DemoClientService {

    @GrpcClient("demo-client")
    private HelloServiceBlockingStub blockingStub;

    public HelloResponse receiveGreeting() {
        HelloRequest request = HelloRequest.newBuilder()
                .setFirstName("yonathan")
                .setLastName("liem")
                .build();

        HelloResponse response = blockingStub.hello(request);
        log.info("receive response, greeting {}", response.getGreeting());
        return response;
    }

}