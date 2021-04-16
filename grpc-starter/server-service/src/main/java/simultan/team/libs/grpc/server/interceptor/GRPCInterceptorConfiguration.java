package simultan.team.libs.grpc.server.interceptor;

import simultan.team.libs.grpc.server.config.GRPCConfiguration;
import javax.validation.Validator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Validator.class)
@AutoConfigureBefore(GRPCConfiguration.class)
@EnableConfigurationProperties(GRPCInterceptorProperties.class)
public class GRPCInterceptorConfiguration {

    @Bean
    @ConditionalOnBean(Validator.class)
    @GRPCGlobalInterceptor
    public ValidationInterceptor validationInterceptor(
        GRPCInterceptorProperties validationProperties) {
        return  new ValidationInterceptor()
            .order(validationProperties.getInterceptorValidationOrder());
    }


}
