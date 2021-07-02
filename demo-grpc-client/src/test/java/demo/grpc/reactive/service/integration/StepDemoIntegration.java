package demo.grpc.reactive.service.integration;

import demo.grpc.reactive.service.DemoClientService;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StepDemoIntegration {

    @Autowired
    private DemoClientService service;

    @When("^the service calls to test demo grpc")
    public void the_service_calls_to_test_demo_grpc() throws Throwable {
        List<String> send = service.handle(Arrays.asList("1", "2")).block();
        assertNotNull(send);
    }
}
