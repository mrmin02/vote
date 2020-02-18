package com.vote.vote;

import com.vote.vote.config.StorageConfig;
import com.vote.vote.service.StorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class) 
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean  
	CommandLineRunner init(StorageService storageService) {   
		return (args) -> {    
			System.out.println("폴더 생성");
			storageService.init();   // 파일들을 저장할 폴더가 없는 경우 만들어 줌.
		};  
	} 
}
