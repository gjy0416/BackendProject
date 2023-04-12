package com.yeogi_jeogi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.yeogi_jeogi"})
public class YeogiJeogiApplication {

	public static void main(String[] args) {
//		System.setProperty("server.servlet.context-path", "/"); 이렇게도 가능 .properties에 이미함
		SpringApplication.run(YeogiJeogiApplication.class, args);
	}

}
