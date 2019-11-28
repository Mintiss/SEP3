package com.example.demo;


import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    private Gson gson;
    Type arrayOfItemsType;


    public ItemController(){
        restTemplate=new RestTemplate();
        gson = new Gson();
        arrayOfItemsType = new TypeToken<ArrayList<Item>>() {}.getType();
    }


    public Item getItem()
    {
        Item item=restTemplate.getForObject("http://localhost:5000/api/Items/1", Item.class);

        return item;
    }

    public ArrayList<Item> getItems()
    {
        String itemsJson=restTemplate.getForObject("http://localhost:5000/api/Items", String.class);

        System.out.println(itemsJson);
        ArrayList<Item> items = gson.fromJson(itemsJson, arrayOfItemsType);

        return items;
    }
}
