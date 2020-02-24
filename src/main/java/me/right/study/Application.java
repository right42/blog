package me.right.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.aws.autoconfigure.context.ContextResourceLoaderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ContextStackAutoConfiguration.class, ContextResourceLoaderAutoConfiguration.class})
public class Application  {

	private static final String CONFIG_LOCATIONS = "spring.config.location="
														+ "classpath:/application.yml,"
														+ "classpath:/aws.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.properties(
					CONFIG_LOCATIONS
				)
				.run(args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
