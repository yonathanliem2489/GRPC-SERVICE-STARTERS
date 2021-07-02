package demo.grpc.reactive.service;

import demo.grpc.reactive.server.proto.ExampleRequest;
import demo.grpc.reactive.server.proto.ExampleResponse;
import demo.grpc.reactive.server.proto.ExampleServiceGrpc;
import demo.grpc.reactive.server.proto.ReactorExampleServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.grpcmock.GrpcMock;
import org.grpcmock.junit5.GrpcMockExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.grpcmock.GrpcMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(GrpcMockExtension.class)
public class DemoClientServiceTest {

    private ManagedChannel channel;

    private DefaultDemoClientService demoClientService;

    @BeforeEach
    void setup() {
        channel = ManagedChannelBuilder.forAddress("localhost", GrpcMock.getGlobalPort())
                .usePlaintext()
                .build();

        demoClientService = new DefaultDemoClientService(
                ReactorExampleServiceGrpc.newReactorStub(channel));
    }

    @AfterEach
    void cleanup() {
        Optional.ofNullable(channel).ifPresent(ManagedChannel::shutdownNow);
    }

    @Test
    void sendDemoClient_thenShouldSuccess() {
        List<String> ids = Arrays.asList("1", "2");
        ExampleRequest request = ExampleRequest.newBuilder()
                .addAllIds(ids)
                .build();

        List<ExampleResponse> responses =
                Arrays.asList(ExampleResponse.newBuilder()
                        .setId("1")
                        .setValue("Example 1")
                        .build(), ExampleResponse.newBuilder()
                        .setId("2")
                        .setValue("Example 2")
                        .build());

        stubFor(serverStreamingMethod(ExampleServiceGrpc.getHandleMethod())
                .willReturn(responses));

        StepVerifier.create(demoClientService.handle(ids))
            .expectSubscription()
            .thenAwait()
            .expectNextMatches(result -> {
                if(!result.equals(responses.stream()
                        .map(ExampleResponse::getId)
                        .collect(Collectors.toList()))) {
                    throw new IllegalStateException("result not match");
                }

                return true;
            })
            .verifyComplete();

        verifyThat(calledMethod(ExampleServiceGrpc.getHandleMethod())
                .withRequest(request), times(1));
    }

    @Test
    void sendDemoClient_thenShouldAborted() {
        List<String> ids = Arrays.asList("1", "2");
        ExampleRequest request = ExampleRequest.newBuilder()
                .addAllIds(ids)
                .build();

        stubFor(serverStreamingMethod(ExampleServiceGrpc.getHandleMethod())
                .willReturn(Status.ABORTED));

        StepVerifier.create(demoClientService.handle(ids))
                .expectSubscription()
                .thenAwait()
                .expectErrorMatches(throwable -> {
                    StatusRuntimeException exception = (StatusRuntimeException) throwable;
                    assertEquals(exception.getStatus(), Status.ABORTED);
                    return true;
                });

        verifyThat(calledMethod(ExampleServiceGrpc.getHandleMethod())
                .withRequest(request), times(0));
    }
}
