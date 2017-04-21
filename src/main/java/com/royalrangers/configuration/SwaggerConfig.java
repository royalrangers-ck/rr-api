package com.royalrangers.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${springfox.documentation.swagger.v2.title}")
    private String title;

    @Value("${springfox.documentation.swagger.v2.description}")
    private String description;

    @Value("${springfox.documentation.swagger.v2.termsOfServiceUrl}")
    private String termsOfServiceUrl;

    @Value("${springfox.documentation.swagger.v2.license}")
    private String license;

    @Value("${springfox.documentation.swagger.v2.licenseUrl}")
    private String licenseUrl;

    @Value("${springfox.documentation.swagger.v2.contactName}")
    private String contactName;

    @Value("${springfox.documentation.swagger.v2.contactUrl}")
    private String contactUrl;

    @Value("${springfox.documentation.swagger.contactEmail}")
    private String contactEmail;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.royalrangers"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .license(license)
                .licenseUrl(licenseUrl)
                .build();
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                "",
                "",
                "",
                "",
                "",
                ApiKeyVehicle.HEADER,
                "Authorization",
                "," /*scope separator*/);
    }

    @Bean
    SecurityScheme apiKey() {
        return new ApiKey("token", "token", "header");
    }

}
