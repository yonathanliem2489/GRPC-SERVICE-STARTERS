package demo.reactive;

import com.tiket.tix.train.libs.grpc.server.annotation.EnableGRPC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableGRPC
@SpringBootApplication
public class ServerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerServiceApplication.class, args);
	}
}
