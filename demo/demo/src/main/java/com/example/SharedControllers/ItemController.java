package com.example.SharedControllers;


import com.example.Shared.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/Items")
public class ItemController {

    private RestTemplate restTemplate;
    private Gson gson;
    private Type arrayOfItemsType;


    public ItemController(){
        restTemplate=new RestTemplate();
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                 .create();
        arrayOfItemsType = new TypeToken<ArrayList<Item>>() {}.getType();
    }

    public Item getItem(int id)
    {
        String itemJson=restTemplate.getForObject("https://localhost:44376/api/Items/" + id, String.class);
        return gson.fromJson(itemJson,Item.class);
    }

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

    public void decrementQuantityReserveBlazor(int id){
        Item item=getItem(id);
        item.setQuantity(item.getQuantity()-1);
        restTemplate.put("https://localhost:44376/api/Items/"+id,item);
    }

    public void postItem(Item fromJson) {
        restTemplate.postForObject("https://localhost:44376/api/Items",fromJson, Item.class);
    }

    public void deleteItem(String id) {
        restTemplate.delete("https://localhost:44376/api/Items/" + id);
    }



    //Exposing web api


    @RequestMapping(method = GET)
    public String getItemsBlazor() {
        return gson.toJson(getItems());
    }
    
}
