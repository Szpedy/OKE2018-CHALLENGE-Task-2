package com.OKEChallenge;

import com.OKEChallenge.nlp.Pipeline;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OKEChallengeApp {
	public static void main(String[] args) {
		Pipeline.getPipeline();
		SpringApplication.run(OKEChallengeApp.class, args);
	}
}
