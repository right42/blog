package me.right.study;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class Application  {

	private static final String AWS = "spring.config.location=classpath:/aws.yml";
	private static final String APPLICATION = "spring.config.location=classpath:/application.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.properties(
						APPLICATION, AWS
				)
				.run(args);
	}

}
