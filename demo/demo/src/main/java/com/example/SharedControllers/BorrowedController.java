package com.example.SharedControllers;

import com.example.Shared.Borrowed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/Borrowed")
public class BorrowedController {

    private RestTemplate restTemplate;
    private Gson gson;
    private Type arrayOfItemsType;
    private String url="https://localhost:44376/api/Borrowed";

    public BorrowedController(){
        restTemplate=new RestTemplate();
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        arrayOfItemsType = new TypeToken<ArrayList<Borrowed>>() {}.getType();
    }

    public ArrayList<Borrowed> getBorrowed()
    {
        String itemsJson=restTemplate.getForObject(url, String.class);

        System.out.println(itemsJson);
        ArrayList<Borrowed> borrowed = gson.fromJson(itemsJson, arrayOfItemsType);

        return borrowed;
    }


    public ArrayList<Borrowed> getBorrowedByUsername(String username)
    {
        String itemsJson=restTemplate.getForObject(url + "/User/"+username, String.class);

        ArrayList<Borrowed> borrowed = gson.fromJson(itemsJson, arrayOfItemsType);

        System.out.println(itemsJson);

        return borrowed;
    }


    public ArrayList<Borrowed> getFinesByUsername(String username)
    {
        String itemsJson=restTemplate.getForObject(url +"/User/Fines/"+username, String.class);

        ArrayList<Borrowed> borrowed = gson.fromJson(itemsJson, arrayOfItemsType);

        System.out.println(itemsJson);

        return borrowed;
    }

    public void putBorrowed(Borrowed borrowed){
        restTemplate.put(url +"/"+ borrowed.getBorrowedId(),borrowed);
    }


    public void borrowItem(Borrowed fromJson) {
        System.out.println(gson.toJson(fromJson));
        restTemplate.postForObject(url,fromJson, Borrowed.class);
    }

    @RequestMapping(method = GET, value = "/{username}")
    public String getBorrowedBlazor(@PathVariable String username) {
        return gson.toJson(getBorrowedByUsername(username));
    }


    @RequestMapping(method = GET, value = "/Fines/{username}")
    public String getFinesBlazor(@PathVariable String username) {
        return gson.toJson(getFinesByUsername(username));
    }

}
