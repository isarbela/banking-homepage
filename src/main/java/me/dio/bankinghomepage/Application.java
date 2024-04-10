package me.dio.bankinghomepage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Banking Homepage API", version = "1.0", description = "User homepage information"),
		servers = {@Server(url = "/", description = "Default server URL")})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
