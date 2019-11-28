package com.example.demo;

import com.example.SharedControllers.ItemController;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
		//System.out.println(ic.getItems());
	}
}