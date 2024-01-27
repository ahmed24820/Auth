package com.example.Authentication;

import com.example.Authentication.AuthenticationController.AuthService;
import com.example.Authentication.AuthenticationController.RegisterRequest;
import com.example.Authentication.models.User;
import com.example.Authentication.models.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.Authentication.models.UserRole.Admin;
import static com.example.Authentication.models.UserRole.MANGER;

@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}


	@Bean
       public CommandLineRunner commandLineRunner(AuthService service){
		return args -> {
			var admin = RegisterRequest.builder()
			.firstname("yousef")
			.lastname("ibrahim")
			.email("yz@dsfas")
			.password("1234")
			.role(Admin).
				build();
			System.out.println("the token is:" + service.register(admin).getToken());

		    var manger = RegisterRequest.builder()
				.firstname("ziad")
				.lastname("ibrahim")
				.email("manger@dsfas")
				.password("1234")
				.role(MANGER).
				build();
		System.out.println("the token is:" + service.register(manger).getToken());
	};
	}


}


