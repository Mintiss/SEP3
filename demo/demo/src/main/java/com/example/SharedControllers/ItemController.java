package com.example.SharedControllers;


import com.example.Shared.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;





@RestController
public class ItemController {

    private RestTemplate restTemplate;
    private Gson gson;
    private Type arrayOfItemsType;


    public ItemController(){
        restTemplate=new RestTemplate();
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        arrayOfItemsType = new TypeToken<ArrayList<Item>>() {}.getType();
    }


    /*public Item getItem()
    {
        Item item=restTemplate.getForObject("http://localhost:5000/api/Items/1", Item.class);

        return item;
    }*/

    public ArrayList<Item> getItems()
    {
        String itemsJson=restTemplate.getForObject("https://localhost:44376/api/Items", String.class);

        System.out.println(itemsJson);
        ArrayList<Item> items = gson.fromJson(itemsJson, arrayOfItemsType);

        return items;
    }

    public void putItem(Item item){
        restTemplate.put("https://localhost:44376/api/Items/" + item.getItemId(),item);
    }

    public void postItem(Item fromJson) {
        restTemplate.postForObject("https://localhost:44376/api/Items",fromJson, Item.class);
    }

    public void deleteItem(String id) {
        restTemplate.delete("https://localhost:44376/api/Items/" + id);
    }
}
