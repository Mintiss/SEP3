package com.example.demo;


import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
