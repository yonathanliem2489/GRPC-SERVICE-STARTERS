package com.tiket.tix.train.libs.grpc.server.annotation;

import io.grpc.ServerInterceptor;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Configuration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface GRPCService {
    Class<? extends ServerInterceptor>[] interceptors() default {};
    boolean applyGlobalInterceptors() default true;
}
