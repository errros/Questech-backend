package com.elab.elearning.elearning;

import com.elab.elearning.elearning.service.FileService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;


@SpringBootApplication
@OpenAPIDefinition
public class ELearningApplication implements CommandLineRunner {
	@Resource
	FileService storageService;

	public static void main(String[] args) {
		SpringApplication.run(ELearningApplication.class, args);
	}

		@Override
		public void run(String... arg) throws Exception {
			storageService.deleteAll();
			storageService.init();
		}

}
