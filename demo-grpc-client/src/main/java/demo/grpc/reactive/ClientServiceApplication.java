package demo.grpc.reactive;

import demo.grpc.reactive.service.DemoClientService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

	@Autowired
	private DemoClientService service;

	@Scheduled(fixedDelay = 10000)
	public void scheduleFixedDelayTask() {

		service.handle(Arrays.asList("1", "2")).block();
	}
}
