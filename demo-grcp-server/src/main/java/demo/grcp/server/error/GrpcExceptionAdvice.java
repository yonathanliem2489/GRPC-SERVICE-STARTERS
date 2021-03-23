package demo.grcp.server.error;

import io.grpc.Status;
import io.grpc.StatusException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcExceptionAdvice {

    @GrpcExceptionHandler
    public Status handleInvalidArgument(IllegalArgumentException e) {
        return Status.INVALID_ARGUMENT.withDescription("Your description").withCause(e);
    }

    @GrpcExceptionHandler(DemoException.class)
    public StatusException handleResourceNotFoundException(DemoException e) {
        Status status = Status.INTERNAL.withDescription("Your description").withCause(e);
        return status.asException();
    }

}