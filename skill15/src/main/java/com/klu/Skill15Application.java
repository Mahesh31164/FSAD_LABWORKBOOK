package com.klu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(
	    scanBasePackages = "com.klu",
	    exclude = {UserDetailsServiceAutoConfiguration.class} // 🔥 ADD THIS
	)
public class Skill15Application {

	public static void main(String[] args) {
		SpringApplication.run(Skill15Application.class, args);
	}

}
