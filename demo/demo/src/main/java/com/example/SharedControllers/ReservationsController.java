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
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/Reservations")
public class ReservationsController {

    private RestTemplate restTemplate;
    private Gson gson;
    private Type arrayOfItemsType;
    private ItemController ic;
    private String url="https://localhost:44376/api/Reservations";


    public ReservationsController() {
        restTemplate = new RestTemplate();
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        arrayOfItemsType = new TypeToken<ArrayList<Reservation>>() {
        }.getType();
        ic = new ItemController();
    }
    public void deleteReservation(String id) {
        restTemplate.delete(url+"/" + id);
    }

    public ArrayList<Reservation> getReservations()
    {
        String itemsJson=restTemplate.getForObject(url, String.class);

        System.out.println(itemsJson);

        ArrayList<Reservation> items = gson.fromJson(itemsJson, arrayOfItemsType);

        return items;
    }

    public ArrayList<Reservation> getBorrowedByReservation(String username)
    {
        String itemsJson=restTemplate.getForObject(url+"/User/"+username, String.class);

        ArrayList<Reservation> reservations = gson.fromJson(itemsJson, arrayOfItemsType);

        System.out.println(itemsJson);

        return reservations;
    }


    public Reservation getBorrowedByReservationID(int id)
    {
        String itemJson=restTemplate.getForObject(url+"/"+id, String.class);

        Reservation reservation = gson.fromJson(itemJson,Reservation.class );

        System.out.println(reservation);

        return reservation;
    }


    @RequestMapping(method = POST, value = "/", consumes = "application/json")
    public void postReserveItemBlazor(@RequestBody StringContent reservationBody){
        System.out.println(reservationBody);
        String bodyString=reservationBody.toString();

        String[] parts = bodyString.split(",");
        int part1 = Integer.parseInt( parts[0] );
        String part2 = parts[1];

        ic.decrementQuantityReserveBlazor(part1);



        Reservation reservation=new Reservation(part2,part1, LocalDateTime.now(),LocalDateTime.now().plusWeeks(1));

        restTemplate.postForObject(url,reservation,Reservation.class);
    }


    @RequestMapping(method = DELETE, value = "/{id}")
    public void cancelReservationBlazor(@PathVariable String id) {
        ic.incrementQuantityReserveBlazor(getBorrowedByReservationID(Integer.parseInt(id)).getItemId());
        restTemplate.delete(url+"/"+id);
    }

    @RequestMapping(method = GET, value = "/User/{username}")
    public String getBorrowedBlazor(@PathVariable String username) {
        return gson.toJson(getBorrowedByReservation(username));
    }
}
