package com.gyjian.restemplatenative;

import com.gyjian.restemplatenative.config.CustomHints;
import com.gyjian.restemplatenative.entity.GptResultDto;
import com.gyjian.restemplatenative.service.TransApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@Slf4j
@ImportRuntimeHints(CustomHints.class)
public class RestemplateNativeApplication implements CommandLineRunner{
	@Autowired
	private TransApiService apiService;

	public static void main(String[] args) {
		SpringApplication.run(RestemplateNativeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		GptResultDto dto = apiService.getTransResult("", "", "");
		log.info("dto result:{}", dto);
	}
}
