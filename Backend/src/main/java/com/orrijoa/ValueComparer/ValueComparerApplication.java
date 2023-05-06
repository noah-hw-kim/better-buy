package com.orrijoa.ValueComparer;

import com.orrijoa.ValueComparer.models.Categories;
import com.orrijoa.ValueComparer.models.UnitToBaseMap;
import com.orrijoa.ValueComparer.models.UnitList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
public class ValueComparerApplication {

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
	public UnitList getUnitList() {
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
		SpringApplication.run(ValueComparerApplication.class, args);
	}

}
