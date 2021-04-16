package simultan.team.libs.grpc.server.config;

import simultan.team.libs.grpc.server.interceptor.GRPCInterceptorConfiguration;
import io.grpc.ServerBuilder;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.services.HealthStatusManager;
import java.net.InetSocketAddress;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.SocketUtils;

@AutoConfigureOrder
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(ValidationAutoConfiguration.class)
@EnableConfigurationProperties(GRPCServerProperties.class)
@ImportAutoConfiguration(GRPCInterceptorConfiguration.class)
public class GRPCConfiguration {

  @Autowired
  private GRPCServerProperties grpcServerProperties;

  @Bean
  @ConditionalOnProperty(value = "simultan.team.grpc.server.enabled", havingValue = "true",
      matchIfMissing = true)
  public GRPCServerRunner grpcServerRunner(
      @Qualifier("grpcInternalConfigurator") Consumer<ServerBuilder<?>> configurator) {
    ServerBuilder<?> serverBuilder = Optional.ofNullable(grpcServerProperties.getNettyServer())
        .<ServerBuilder<?>> map(n->{
          final NettyServerBuilder builder = Optional.ofNullable(n.getPrimaryListenAddress())
              .map(NettyServerBuilder::forAddress)
              .orElse(NettyServerBuilder.forPort(grpcServerProperties.getRunningPort()));

          Optional.ofNullable(n.getAdditionalListenAddresses())
              .ifPresent(l->l.forEach(builder::addListenAddress));

          Optional.ofNullable(n.getFlowControlWindow())
              .ifPresent(builder::flowControlWindow);

          Optional.ofNullable(n.getInitialFlowControlWindow())
              .ifPresent(builder::initialFlowControlWindow);

          Optional.ofNullable(n.getKeepAliveTime())
              .ifPresent(t->builder.keepAliveTime(t.toMillis(), TimeUnit.MILLISECONDS));

          Optional.ofNullable(n.getKeepAliveTimeout())
              .ifPresent(t->builder.keepAliveTimeout(t.toMillis(), TimeUnit.MILLISECONDS));

          Optional.ofNullable(n.getPermitKeepAliveTime())
              .ifPresent(t->builder.permitKeepAliveTime(t.toMillis(), TimeUnit.MILLISECONDS));


          Optional.ofNullable(n.getMaxConnectionAge())
              .ifPresent(t->builder.maxConnectionAge(t.toMillis(), TimeUnit.MILLISECONDS));

          Optional.ofNullable(n.getMaxConnectionAgeGrace())
              .ifPresent(t->builder.maxConnectionAgeGrace(t.toMillis(), TimeUnit.MILLISECONDS));

          Optional.ofNullable(n.getMaxConnectionIdle())
              .ifPresent(t->builder.maxConnectionIdle(t.toMillis(), TimeUnit.MILLISECONDS));

          Optional.ofNullable(n.getMaxConcurrentCallsPerConnection())
              .ifPresent(builder::maxConcurrentCallsPerConnection);

          Optional.ofNullable(n.getPermitKeepAliveWithoutCalls())
              .ifPresent(builder::permitKeepAliveWithoutCalls);

          Optional.ofNullable(n.getMaxInboundMessageSize())
              .ifPresent(s->builder.maxInboundMessageSize((int)s.toBytes()));

          Optional.ofNullable(n.getMaxInboundMetadataSize())
              .ifPresent(s->builder.maxInboundMetadataSize((int)s.toBytes()));


          return builder;

        })
        .orElse(ServerBuilder.forPort(grpcServerProperties.getRunningPort()));
    return new GRPCServerRunner(configurator, serverBuilder);
  }

  @Bean
  public HealthStatusManager healthStatusManager() {
    return new HealthStatusManager();
  }

  @Bean
  @ConditionalOnMissingBean(GRpcServerBuilderConfigurer.class)
  public GRpcServerBuilderConfigurer serverBuilderConfigurer() {
    return new GRpcServerBuilderConfigurer();
  }

  @Bean(name = "grpcInternalConfigurator")
  public Consumer<ServerBuilder<?>> configurator(GRpcServerBuilderConfigurer configurer) {
    return serverBuilder -> configurer.configure(serverBuilder);
  }

  @Bean
  @ConfigurationPropertiesBinding
  public static Converter<String, InetSocketAddress> socketAddressConverter() {
    return new Converter<String, InetSocketAddress>() {
      @Override
      public InetSocketAddress convert(String source) {
        final String[] chunks = source.split(":");
        int port;
        switch (chunks.length) {
          case 1:
            port = GRPCServerProperties.DEFAULT_GRPC_PORT;
            break;
          case 2:
            port = Integer.parseInt(chunks[1]);
            if(port<1){
              port = SocketUtils.findAvailableTcpPort();
            }
            break;
          default:
            throw new IllegalArgumentException(source +" can't be converted to socket address");

        }

        return new InetSocketAddress(chunks[0], port);
      }
    };
  }
}
