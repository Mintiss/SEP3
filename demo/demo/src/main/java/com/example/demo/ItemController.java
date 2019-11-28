package com.example.demo;



import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ItemController {

        private RestTemplate restTemplate;

    public ItemController(){
        restTemplate=new RestTemplate();
    }


    public Item getItem()
    {
        Item item=restTemplate.getForObject("https://localhost:44376/api/Items/1", Item.class);

        return item;
    }
}
