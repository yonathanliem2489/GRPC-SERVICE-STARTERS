package simultan.team.libs.grpc.server.interceptor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("simultan.team.libs.grpc.server")
@Getter
@Setter
public class GRPCInterceptorProperties {
    private Integer interceptorValidationOrder;

    GRPCInterceptorProperties() {
        this.interceptorValidationOrder = 1;
    }
}
