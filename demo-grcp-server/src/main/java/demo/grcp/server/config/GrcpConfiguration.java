//package demo.grcp.server.config;
//
//import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
//import java.util.concurrent.TimeUnit;
//import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GrcpConfiguration {
//
//  @Bean
//  public GrpcServerConfigurer keepAliveServerConfigurer() {
//    return serverBuilder -> {
//      if (serverBuilder instanceof NettyServerBuilder) {
//        ((NettyServerBuilder) serverBuilder)
//            .keepAliveTime(30, TimeUnit.SECONDS)
//            .keepAliveTimeout(5, TimeUnit.SECONDS)
//            .permitKeepAliveWithoutCalls(true);
//      }
//    };
//  }
//}
