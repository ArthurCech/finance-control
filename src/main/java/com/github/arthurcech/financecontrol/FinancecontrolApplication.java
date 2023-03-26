package com.github.arthurcech.financecontrol;

import com.github.arthurcech.financecontrol.config.property.ApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class FinancecontrolApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancecontrolApplication.class, args);
	}

}
