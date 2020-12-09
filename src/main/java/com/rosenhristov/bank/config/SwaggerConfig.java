package com.rosenhristov.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.paths.DefaultPathProvider;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Import({SpringDataRestConfiguration.class, BeanValidatorPluginsConfiguration.class})
@Configuration
public class SwaggerConfig {

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<>(Arrays.asList("application/json"));

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8080")
                .directModelSubstitute(LocalDate.class, Date.class)
                .pathProvider(new DefaultPathProvider())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dataart.bank.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(apiDetails())
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    private ApiInfo apiDetails() {
        return new ApiInfoBuilder()
                .title("Bank API")
                .description("Sample API for Bank Application")
                .termsOfServiceUrl("Free to use")
                .version("1.0")
                .contact(new Contact("Rosen Hristov", "http://wwww.dataart.com", "rosen.hristov@dataart.com"))
                .license("API License")
                .licenseUrl("http://wwww.dataart.com")
                .build();
    }
}
