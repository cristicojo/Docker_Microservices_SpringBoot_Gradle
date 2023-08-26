package employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CRUDServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CRUDServiceApplication.class, args);
	}

}
