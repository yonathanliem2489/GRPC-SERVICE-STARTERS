package demo.grpc.reactive.server.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.35.0)",
    comments = "Source: ExampleService.proto")
public final class ExampleServiceGrpc {

  private ExampleServiceGrpc() {}

  public static final String SERVICE_NAME = "demo.grpc.reactive.server.proto.ExampleService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<demo.grpc.reactive.server.proto.ExampleRequest,
      demo.grpc.reactive.server.proto.ExampleResponse> getHandleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "handle",
      requestType = demo.grpc.reactive.server.proto.ExampleRequest.class,
      responseType = demo.grpc.reactive.server.proto.ExampleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<demo.grpc.reactive.server.proto.ExampleRequest,
      demo.grpc.reactive.server.proto.ExampleResponse> getHandleMethod() {
    io.grpc.MethodDescriptor<demo.grpc.reactive.server.proto.ExampleRequest, demo.grpc.reactive.server.proto.ExampleResponse> getHandleMethod;
    if ((getHandleMethod = ExampleServiceGrpc.getHandleMethod) == null) {
      synchronized (ExampleServiceGrpc.class) {
        if ((getHandleMethod = ExampleServiceGrpc.getHandleMethod) == null) {
          ExampleServiceGrpc.getHandleMethod = getHandleMethod =
              io.grpc.MethodDescriptor.<demo.grpc.reactive.server.proto.ExampleRequest, demo.grpc.reactive.server.proto.ExampleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "handle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  demo.grpc.reactive.server.proto.ExampleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  demo.grpc.reactive.server.proto.ExampleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ExampleServiceMethodDescriptorSupplier("handle"))
              .build();
        }
      }
    }
    return getHandleMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ExampleServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExampleServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExampleServiceStub>() {
        @java.lang.Override
        public ExampleServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExampleServiceStub(channel, callOptions);
        }
      };
    return ExampleServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ExampleServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExampleServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExampleServiceBlockingStub>() {
        @java.lang.Override
        public ExampleServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExampleServiceBlockingStub(channel, callOptions);
        }
      };
    return ExampleServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ExampleServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExampleServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExampleServiceFutureStub>() {
        @java.lang.Override
        public ExampleServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExampleServiceFutureStub(channel, callOptions);
        }
      };
    return ExampleServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ExampleServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void handle(demo.grpc.reactive.server.proto.ExampleRequest request,
        io.grpc.stub.StreamObserver<demo.grpc.reactive.server.proto.ExampleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHandleMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHandleMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                demo.grpc.reactive.server.proto.ExampleRequest,
                demo.grpc.reactive.server.proto.ExampleResponse>(
                  this, METHODID_HANDLE)))
          .build();
    }
  }

  /**
   */
  public static final class ExampleServiceStub extends io.grpc.stub.AbstractAsyncStub<ExampleServiceStub> {
    private ExampleServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExampleServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExampleServiceStub(channel, callOptions);
    }

    /**
     */
    public void handle(demo.grpc.reactive.server.proto.ExampleRequest request,
        io.grpc.stub.StreamObserver<demo.grpc.reactive.server.proto.ExampleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getHandleMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ExampleServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ExampleServiceBlockingStub> {
    private ExampleServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExampleServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExampleServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<demo.grpc.reactive.server.proto.ExampleResponse> handle(
        demo.grpc.reactive.server.proto.ExampleRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getHandleMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ExampleServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ExampleServiceFutureStub> {
    private ExampleServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExampleServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExampleServiceFutureStub(channel, callOptions);
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
          serviceImpl.handle((demo.grpc.reactive.server.proto.ExampleRequest) request,
              (io.grpc.stub.StreamObserver<demo.grpc.reactive.server.proto.ExampleResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ExampleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ExampleServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return demo.grpc.reactive.server.proto.ExampleProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ExampleService");
    }
  }

  private static final class ExampleServiceFileDescriptorSupplier
      extends ExampleServiceBaseDescriptorSupplier {
    ExampleServiceFileDescriptorSupplier() {}
  }

  private static final class ExampleServiceMethodDescriptorSupplier
      extends ExampleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ExampleServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ExampleServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ExampleServiceFileDescriptorSupplier())
              .addMethod(getHandleMethod())
              .build();
        }
      }
    }
    return result;
  }
}
