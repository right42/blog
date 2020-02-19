package me.right.study;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.aws.autoconfigure.context.ContextResourceLoaderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ContextStackAutoConfiguration.class, ContextResourceLoaderAutoConfiguration.class})
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
