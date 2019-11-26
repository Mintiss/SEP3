package com.example.demo;

import com.example.networking.model.ServerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class DemoApplication {

	/*private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public Item getQuote() {
		return quote;
	}

	public void setQuote(Item quote) {
		this.quote = quote;
	}

	private Item quote;

	public static void main(String[] args){}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Item quote = restTemplate.getForObject(

					"https://localhost:44376/api/Books/316", Item.class);
			setQuote(quote);
			log.info(quote.toString());
		};
	}

	@PostConstruct
	public void test(){
		try {
			wait(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getQuote();
	System.out.println(quote);
	}*/


	public static void main(String[] args) {
		ItemController ic=new ItemController();
		System.out.println(ic.getItem());
	}
}