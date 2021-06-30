package demo.grpc.reactive.service;

import demo.grpc.reactive.ClientConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ImportAutoConfiguration(ClientConfiguration.class)
public class DemoClientServiceTest {


}
