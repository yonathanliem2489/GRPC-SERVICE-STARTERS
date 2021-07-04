# README

## Introduction

This libraries for simplify configuration grpc

### Requirement
1. Spring boot 2.3.x with reactor

### Compile grpc starter

1. Open project grpc-starter  
2. `mvn clean install` in java 11

### Configuring GRPC Interface

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>simultan.team.grpc.libs</groupId>
      <artifactId>grpc-starter</artifactId>
      <version>1.0.0-1-SNAPSHOT</version>
    </dependency>
  </dependencies>
</dependencyManagement>    
```

Put grpc model dependency

 ```xml
<properties>
    <grpc.version>1.35.0</grpc.version>
    <protobuf.version>3.14.0</protobuf.version>
    <protobuf-plugin.version>0.6.1</protobuf-plugin.version>
    <reactive.grpc.version>1.0.0</reactive.grpc.version>
    <kr-motd.version>1.6.2</kr-motd.version>
</properties>

<dependencies>
   <dependency>
     <groupId>io.projectreactor</groupId>
     <artifactId>reactor-core</artifactId>
   </dependency>
   <dependency>
     <groupId>simultan.team.grpc.libs</groupId>
     <artifactId>model-service</artifactId>
     <version>1.0.0-1-SNAPSHOT</version>
   </dependency>

   <dependency>
     <groupId>io.grpc</groupId>
     <artifactId>grpc-all</artifactId>
     <version>${grpc.version}</version>
   </dependency>
    <dependency>
      <groupId>com.salesforce.servicelibs</groupId>
      <artifactId>reactor-grpc-stub</artifactId>
      <version>${reactive.grpc.version}</version>
    </dependency>
 </dependencies>

<build>
    <plugins>
      <plugin>
        <groupId>org.xolstice.maven.plugins</groupId>
        <artifactId>protobuf-maven-plugin</artifactId>
        <version>${protobuf-plugin.version}</version>
        <configuration>
          <protocArtifact>com.google.protobuf:protoc:${protobuf.version}:exe:${os.detected.classifier}</protocArtifact>
          <pluginId>grpc-java</pluginId>
          <pluginArtifact>io.grpc:protoc-gen-grpc-java:${grpc.version}:exe:${os.detected.classifier}</pluginArtifact>
        </configuration>
        <executions>
          <execution>
            <goals>
              <goal>compile</goal>
              <goal>compile-custom</goal>
            </goals>
            <configuration>
              <protocPlugins>
                <protocPlugin>
                  <id>reactor-grpc</id>
                  <groupId>com.salesforce.servicelibs</groupId>
                  <artifactId>reactor-grpc</artifactId>
                  <version>${reactive.grpc.version}</version>
                  <mainClass>com.salesforce.reactorgrpc.ReactorGrpcGenerator</mainClass>
                </protocPlugin>
              </protocPlugins>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>

    <extensions>
      <extension>
        <groupId>kr.motd.maven</groupId>
        <artifactId>os-maven-plugin</artifactId>
        <version>${kr-motd.version}</version>
      </extension>
    </extensions>
</build>
```

Create proto file
```protobuf
syntax = "proto3";
option java_multiple_files = true;
package demo.grpc.reactive.server.proto;

option java_package = "demo.grpc.reactive.server.proto";
option java_outer_classname = "ExampleProto";

import "validation.proto";

message ExampleRequest {
  repeated string ids = 1[(validation.repeatMin) = 0];
}

message ExampleResponse {
  string id = 1;
  string value = 2;

}

service ExampleService {
  rpc handle(ExampleRequest) returns (stream ExampleResponse);
}
``` 

Execute maven command 

```
mvn clean install
```


### Configuring GRPC Server

Add this starter library to your project.

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>simultan.team.grpc.libs</groupId>
      <artifactId>grpc-starter</artifactId>
      <version>1.0.0-1-SNAPSHOT</version>
    </dependency>
  </dependencies>
</dependencyManagement>    
```

Put grpc service dependency

 ```xml
<dependencies>
  // must set interface dependency you create grpc interface  
  <dependency>
    <groupId>simultan.team.grpc</groupId>
    <artifactId>demo-grpc-interface</artifactId>
    <version>1.0.0-1-SNAPSHOT</version>
    <scope>compile</scope>
  </dependency>

  // grpc dependency
  <dependency>
    <groupId>io.grpc</groupId>
    <artifactId>grpc-all</artifactId>
    <version>1.35.0</version>
  </dependency>
  <dependency>
    <groupId>simultan.team.grpc.libs</groupId>
    <artifactId>server-service</artifactId>
    <version>1.0.0-1-SNAPSHOT</version>
  </dependency>
</dependencies>
```

Put annotation `@EnableGRPC` in main class application, for example

```java
@EnableGRPC
@SpringBootApplication
public class ServerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerServiceApplication.class, args);
	}
}

```

Implement grpc service 
```java
@Slf4j
@GRPCService
public class ExampleService extends ReactorExampleServiceGrpc.ExampleServiceImplBase {

  @Override
  public Flux<ExampleResponse> handle(Mono<ExampleRequest> requestMono) {
    return requestMono
        .doOnSuccess(request -> log.info("receive request {}", request.toString()))
        .flatMapMany(request -> Mono.just(ExampleResponse.newBuilder().build()));
  }
}
```

Configuring GRPC Service Implement

```java
@Configuration
@AutoConfigureBefore(GRPCConfiguration.class)
public class DemoReactiveServiceConfiguration {

  @Bean
  BindableService exampleServiceImplBase() {
    return new ExampleService();
  }
}
```

Setup Properties
```properties
simultan.team.libs.grpc.server.port=9099
```

Run demo grpc server service 

### Configuring GRPC Client

Setup dependency

```xml
<dependency>
    <groupId>simultan.team.grpc.libs</groupId>
    <artifactId>model-service</artifactId>
    <version>1.0.0-1-SNAPSHOT</version>
</dependency>
<dependency>
    <groupId>simultan.team.grpc</groupId>
    <artifactId>demo-grpc-interface</artifactId>
    <version>1.0.0-1-SNAPSHOT</version>
</dependency>
```

Setup client service class implement

```java
@Slf4j
public class DefaultDemoClientService implements DemoClientService {

  private final ReactorExampleServiceStub stub;

  public DefaultDemoClientService(ReactorExampleServiceStub stub) {
    this.stub = stub;
  }

  @Override
  public Mono<List<String>> handle(List<String> ids) {

    Flux<ExampleResponse> flux = stub.handle(toMessage(ids));

    return flux.doOnNext(response -> log.info("receive response id {}", response.getId()))
        .map(ExampleResponse::getId)
        .collectList()
        .doOnError(throwable -> log.error("problem occurred {}", throwable.getMessage()));
  }

  private Mono<ExampleRequest> toMessage(List<String> ids) {
    return Mono.just(ExampleRequest.newBuilder()
        .addAllIds(ids)
        .build());
  }
}
```

Setup client configuration
```java
@Configuration
public class ClientConfiguration {

  @Bean
  DemoClientService demoClientService(ManagedChannel demoChannel) {
    ReactorExampleServiceGrpc.ReactorExampleServiceStub stub =
        ReactorExampleServiceGrpc.newReactorStub(demoChannel);
    return new DefaultDemoClientService(stub);
  }

  @Bean
  ManagedChannel demoChannel() {
    ManagedChannel channel = ManagedChannelBuilder
        .forAddress("localhost", 9099)
        .usePlaintext()
        .idleTimeout(3, TimeUnit.SECONDS)
        .keepAliveTimeout(5, TimeUnit.SECONDS)
        .build();
    return channel;
  }
}
```


Run demo grpc client

ENJOY!!!