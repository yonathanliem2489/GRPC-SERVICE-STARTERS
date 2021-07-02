package demo.grpc.reactive.service.integration;

import demo.grpc.reactive.ClientServiceApplication;
import demo.grpc.reactive.TestingConfiguration;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

/**
 * <p>This is live test for grpc client-server</p>
 * <ol>
 *     <li>first run grpc server</li>
 *     <li>run {@link DemoServiceTestingLive}  to test service client</li>
 *     <li>grpc server receiving request and throw response</li>
 *     <li>service client receive response and assert it</li>
 * </ol>
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/integration/demo")
@CucumberContextConfiguration
@SpringBootTest(classes = {ClientServiceApplication.class, TestingConfiguration.class},
    webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = {"classpath:integration/demo/application.yml"})
public class DemoServiceTestingLive {

}
