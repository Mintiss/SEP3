package com.example.SharedControllers;

import com.example.Shared.Reservation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
@RestController
@RequestMapping("/Items")
public class ReservationsController {

    private RestTemplate restTemplate;
    private Gson gson;
    private Type arrayOfItemsType;

    public ReservationsController() {
        restTemplate = new RestTemplate();
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        arrayOfItemsType = new TypeToken<ArrayList<Reservation>>() {
        }.getType();
    }

    public ArrayList<Reservation> getReservations()
    {
        String itemsJson=restTemplate.getForObject("http://localhost:5000/api/Reservations", String.class);

        System.out.println(itemsJson);
        ArrayList<Reservation> items = gson.fromJson(itemsJson, arrayOfItemsType);

        return items;
    }
}
