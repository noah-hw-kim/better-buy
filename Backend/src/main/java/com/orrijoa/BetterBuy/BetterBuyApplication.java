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
//@EntityScan(basePackages = "com.orrijoa.BetterBuy")
//@ComponentScan({ "com.orrijoa.BetterBuy.*" })
public class BetterBuyApplication {
	public static void main(String[] args) {
		SpringApplication.run(BetterBuyApplication.class, args);
	}
}
