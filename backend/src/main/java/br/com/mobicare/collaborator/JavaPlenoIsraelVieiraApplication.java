package br.com.mobicare.collaborator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableSwagger2
public class JavaPlenoIsraelVieiraApplication {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder()
				.rootUri("https://5e74cb4b9dff120016353b04.mockapi.io/api/v1")
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaPlenoIsraelVieiraApplication.class, args);
	}
}
