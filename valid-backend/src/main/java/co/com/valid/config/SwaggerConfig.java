
package co.com.valid.config;

import com.google.common.base.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger configuration class
 * 
 * @author diego ibanez
 * @Since 1.0.0
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	/**
	 * Publish a bean to generate swagger2 endpoints
	 * 
	 * @return a swagger configuration bean
	 */
	@Bean
	public Docket usersApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(validPersonsApiInfo()).select().paths(userPaths())
				.apis(RequestHandlerSelectors.any()).build().useDefaultResponseMessages(false);
	}

	/**
	 * Api info
	 * 
	 * @return ApiInfo
	 */
	private ApiInfo validPersonsApiInfo() {
		return new ApiInfoBuilder().title("Service valid person").description("rest service to manage persons of valid company")
				.version("1.0").license("Apache License Version 2.0")
				.contact(new Contact("diego ibanez", "", "ingeniero.ibanez.sys@gmail.com")).build();
	}

	/**
	 * Config paths.
	 *
	 * @return the predicate
	 */
	private Predicate<String> userPaths() {
		return regex("/valid.*");
	}
}
