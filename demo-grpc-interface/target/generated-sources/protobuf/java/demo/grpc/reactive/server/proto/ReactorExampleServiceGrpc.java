package demo.grpc.reactive.server.proto;

import static demo.grpc.reactive.server.proto.ExampleServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: ExampleService.proto")
public final class ReactorExampleServiceGrpc {
    private ReactorExampleServiceGrpc() {}

    public static ReactorExampleServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorExampleServiceStub(channel);
    }

    public static final class ReactorExampleServiceStub extends io.grpc.stub.AbstractStub<ReactorExampleServiceStub> {
        private ExampleServiceGrpc.ExampleServiceStub delegateStub;

        private ReactorExampleServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = ExampleServiceGrpc.newStub(channel);
        }

        private ReactorExampleServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = ExampleServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorExampleServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorExampleServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Flux<demo.grpc.reactive.server.proto.ExampleResponse> handle(reactor.core.publisher.Mono<demo.grpc.reactive.server.proto.ExampleRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactorRequest, delegateStub::handle);
        }

        public reactor.core.publisher.Flux<demo.grpc.reactive.server.proto.ExampleResponse> handle(demo.grpc.reactive.server.proto.ExampleRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::handle);
        }

    }

    public static abstract class ExampleServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Flux<demo.grpc.reactive.server.proto.ExampleResponse> handle(reactor.core.publisher.Mono<demo.grpc.reactive.server.proto.ExampleRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            demo.grpc.reactive.server.proto.ExampleServiceGrpc.getHandleMethod(),
                            asyncServerStreamingCall(
                                    new MethodHandlers<
                                            demo.grpc.reactive.server.proto.ExampleRequest,
                                            demo.grpc.reactive.server.proto.ExampleResponse>(
                                            this, METHODID_HANDLE)))
                    .build();
        }
    }

    private static final int METHODID_HANDLE = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final ExampleServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(ExampleServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_HANDLE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToMany((demo.grpc.reactive.server.proto.ExampleRequest) request,
                            (io.grpc.stub.StreamObserver<demo.grpc.reactive.server.proto.ExampleResponse>) responseObserver,
                            serviceImpl::handle);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
