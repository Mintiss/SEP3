package com.example.SharedControllers;

import com.example.Shared.Reservation;
import com.example.Shared.StringContent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/Reservations")
public class ReservationsController {

    private RestTemplate restTemplate;
    private Gson gson;
    private Type arrayOfItemsType;
    private ItemController ic;


    public ReservationsController() {
        restTemplate = new RestTemplate();
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        arrayOfItemsType = new TypeToken<ArrayList<Reservation>>() {
        }.getType();
        ic = new ItemController();
    }

    public ArrayList<Reservation> getReservations()
    {
        String itemsJson=restTemplate.getForObject("http://localhost:5000/api/Reservations", String.class);

        System.out.println(itemsJson);
        ArrayList<Reservation> items = gson.fromJson(itemsJson, arrayOfItemsType);

        return items;
    }

    public ArrayList<Reservation> getBorrowedByReservation(String username)
    {
        String itemsJson=restTemplate.getForObject("http://localhost:5000/api/Reservations/User/"+username, String.class);

        ArrayList<Reservation> reservations = gson.fromJson(itemsJson, arrayOfItemsType);

        System.out.println(itemsJson);

        return reservations;
    }

    @RequestMapping(method = POST, value = "/", consumes = "application/json")
    public void postReserveItemBlazor(@RequestBody StringContent reservationBody){
        System.out.println(reservationBody);
        String bodyString=reservationBody.toString();

        String[] parts = bodyString.split(",");
        int part1 = Integer.parseInt( parts[0] );
        String part2 = parts[1];

        ic.decrementQuantityReserveBlazor(part1);

        Reservation reservation=new Reservation(part2,part1, LocalDate.now(),LocalDate.now().plusWeeks(1));

        restTemplate.postForObject("http://localhost:5000/api/Reservations",reservation,Reservation.class);
    }


    @RequestMapping(method = GET, value = "/User/{username}")
    public String getBorrowedBlazor(@PathVariable String username) {
        return gson.toJson(getBorrowedByReservation(username));
    }
}
