package com.orrijoa.BetterBuy.config;

import com.orrijoa.BetterBuy.models.Categories;
import com.orrijoa.BetterBuy.models.UnitList;
import com.orrijoa.BetterBuy.models.UnitToBaseMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AppConfig {
    // Use for swagger api for testing
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    // Helper method for api()
    @Bean
    public ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        return builder.build();
    }

    // Cache Categories which stays same once created
    @Bean
    public Categories getCategories() {
        return new Categories();
    }

    // Cache Unitlist which stays same once created
    @Bean
    public UnitList getUnitTypeToUnit() {
        return new UnitList();
    }

    // Cache UnitToBaseMap which stays same once created
    @Bean
    public UnitToBaseMap getUnitToBaseMap() {
        return new UnitToBaseMap();
    }
}
