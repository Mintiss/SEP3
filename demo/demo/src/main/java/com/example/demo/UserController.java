package com.example.demo;

import com.example.Shared.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

        private RestTemplate restTemplate;

        public UserController(){
            restTemplate=new RestTemplate();
        }


        public User getUserFromDB(String userFromLogin)
        {
            User userGotFromDB=restTemplate.getForObject("https://localhost:44376/api/Users/"+userFromLogin, User.class);

            return userGotFromDB;
        }
}
