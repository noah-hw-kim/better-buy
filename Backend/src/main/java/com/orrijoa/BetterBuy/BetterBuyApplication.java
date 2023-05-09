package com.orrijoa.BetterBuy;

import com.orrijoa.BetterBuy.models.Categories;
import com.orrijoa.BetterBuy.models.UnitToBaseMap;
import com.orrijoa.BetterBuy.models.UnitList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan(basePackages = "com.orrijoa.BetterBuy")
@ComponentScan({ "com.orrijoa.BetterBuy.*" })
public class BetterBuyApplication {

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
		Categories categories = new Categories();
		return categories;
	}

	// Cache Unitlist which stays same once created
	@Bean
	public UnitList getUnitTypeToUnit() {
		UnitList unitList = new UnitList();
		return unitList;
	}

	// Cache UnitToBaseMap which stays same once created
	@Bean
	public UnitToBaseMap getUnitToBaseMap() {
		UnitToBaseMap unitToBaseMap = new UnitToBaseMap();
		return unitToBaseMap;
	}

	public static void main(String[] args) {
		SpringApplication.run(BetterBuyApplication.class, args);
	}

}
