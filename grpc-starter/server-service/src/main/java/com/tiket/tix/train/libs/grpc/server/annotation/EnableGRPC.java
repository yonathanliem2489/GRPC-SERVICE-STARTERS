package com.tiket.tix.train.libs.grpc.server.annotation;

import com.tiket.tix.train.libs.grpc.server.config.GRPCConfiguration;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GRPCConfiguration.class)
public @interface EnableGRPC {
}
