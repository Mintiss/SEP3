package com.example.SharedControllers;

import com.example.Shared.Borrowed;
import com.example.Shared.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;

@RestController
public class BorrowedController {

    private RestTemplate restTemplate;
    private Gson gson;
    private Type arrayOfItemsType;


    public BorrowedController(){
        restTemplate=new RestTemplate();
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        arrayOfItemsType = new TypeToken<ArrayList<Borrowed>>() {}.getType();
    }

    public ArrayList<Borrowed> getBorrowed()
    {
        String itemsJson=restTemplate.getForObject("http://localhost:5000/api/Borrowed", String.class);

        System.out.println(itemsJson);
        ArrayList<Borrowed> borrowed = gson.fromJson(itemsJson, arrayOfItemsType);

        return borrowed;
    }

}
