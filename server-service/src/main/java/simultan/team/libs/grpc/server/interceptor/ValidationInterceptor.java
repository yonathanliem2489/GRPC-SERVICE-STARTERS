package simultan.team.libs.grpc.server.interceptor;

import com.google.protobuf.GeneratedMessageV3;
import simultan.team.libs.grpc.server.interceptor.support.AbstractValidationInterceptor;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.util.Optional;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.core.Ordered;

public class ValidationInterceptor extends AbstractValidationInterceptor implements ServerInterceptor,
    Ordered {

    @Setter
    @Accessors(fluent = true)
    private Integer order;


    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(final ServerCall<ReqT, RespT> call, final Metadata headers, final ServerCallHandler<ReqT, RespT> next) {
        ServerCall.Listener<ReqT> listener = next.startCall(call, headers);
        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {
            @Override
            public void onMessage(ReqT message) {
                GeneratedMessageV3 messageV3 = (GeneratedMessageV3) message;
                try {
                    validate(messageV3);
                    super.onMessage(message);
                } catch (Exception e) {
                    Status status = Status.INVALID_ARGUMENT.withDescription(e.getMessage());
                    call.close(status, headers);
                    throw new StatusRuntimeException(status);
                }
            }
        };
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat analogous to Servlet {@code
     * load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return Optional.ofNullable(order).orElse(HIGHEST_PRECEDENCE + 10);
    }
}
