package com.vinisnzy.github_repo_analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GithubRepoAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubRepoAnalysisApplication.class, args);
	}

}
