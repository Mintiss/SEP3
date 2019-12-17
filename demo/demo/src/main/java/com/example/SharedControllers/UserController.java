package com.example.SharedControllers;

import com.example.Shared.StringContent;
import com.example.Shared.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/Users")
public class UserController {

        private RestTemplate restTemplate;
        private Gson gson;
        private Type arrayOfItemsType;
        private String url="https://localhost:44376/api/Users";


    public UserController(){
            restTemplate=new RestTemplate();
            gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            arrayOfItemsType = new TypeToken<ArrayList<User>>() {}.getType();
        }

    public ArrayList<User> getUsers()
    {
        java.lang.String usersJson=restTemplate.getForObject(url+"/", java.lang.String.class);

        System.out.println(usersJson);
        ArrayList<User> users = gson.fromJson(usersJson, arrayOfItemsType);
        return users;
    }

        public User getUserFromDB(String userFromLogin)
        {
            User userGotFromDB;

            try {
                userGotFromDB = restTemplate.getForObject(url+"/" + userFromLogin, User.class);
            } catch (Exception e){
                userGotFromDB=null;
            }

            return userGotFromDB;

        }

    public User checkLogInTier1Librarian(User user)
    {
        User userGotFromDB;

        try {
            userGotFromDB = restTemplate.getForObject(url+"/" + user.getUsername() + "/" + user.getPassword(), User.class);
        } catch (Exception e){
            userGotFromDB=null;
        }

        return userGotFromDB;

    }

    public void deleteUser(java.lang.String id) {
        restTemplate.delete(url+"/" + id);
    }

    public void changePassword(User user){
        restTemplate.put(url+"/" + user.getUsername(),user);
    }


    //Exposing web api

    @RequestMapping(method = GET, value = "/{id}")
    public String getUserBlazor(@PathVariable String id) {
        System.out.println(gson.toJson(getUserFromDB(id)));
        return gson.toJson(getUserFromDB(id));
    }


    @RequestMapping(method = POST, value = "/", consumes = "application/json")
    public void postRegisterUserBlazor(@RequestBody StringContent body){
            System.out.println(body);
            String bodyString=body.toString();

            String[] parts = bodyString.split(",");
            String part1 = parts[0];
            String part2 = parts[1];
            User user=new User(part1,part2,0);

            restTemplate.postForObject(url+"/",user,User.class);
    }
}
