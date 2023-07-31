package com.wipro.telefonica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2
//@EnableWebMvc
public class SwaggerConfig {
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.wipro.telefonica"))
				.build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
		
	}
//	@Bean
	public ApiInfo apiInfo() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

		return apiInfoBuilder.title("Covid-19 API").description("Swagger for Covid-19 Capstone")
				.termsOfServiceUrl("https://stackbooks.stackroute.in/terms-of-use.html")
				.contact("charansanthu4@gmail.com").license("charansanthu4@gmail.com")
				.licenseUrl("charansanthu4@gmail.com").version("v1.0.1").build();

	}

}
