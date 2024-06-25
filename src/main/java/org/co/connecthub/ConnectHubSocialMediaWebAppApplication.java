package org.co.connecthub;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class ConnectHubSocialMediaWebAppApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConnectHubSocialMediaWebAppApplication.class, args);


	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("rakesh@123"));

	}
}
