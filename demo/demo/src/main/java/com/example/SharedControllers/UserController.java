package com.example.SharedControllers;

import com.example.Shared.Item;
import com.example.Shared.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;

@RestController
public class UserController {

        private RestTemplate restTemplate;
        private Gson gson;
        Type arrayOfItemsType;

        public UserController(){
            restTemplate=new RestTemplate();
            gson = new Gson();
            arrayOfItemsType = new TypeToken<ArrayList<Item>>() {}.getType();
        }


        public User getUserFromDB(String userFromLogin)
        {
            User userGotFromDB;

            try {
                userGotFromDB = restTemplate.getForObject("http://localhost:5000/api/Users/" + userFromLogin, User.class);
            } catch (Exception e){
                userGotFromDB=new User(null,null,0);
            }

            return userGotFromDB;

        }
}
