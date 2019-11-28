package com.example.Shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    @JsonProperty("username")
    private String Username;
    @JsonProperty("password")
    private String Password;
    @JsonProperty("type")
    private int type;

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public int getType() {
        return type;
    }

    public User(String username, String password){
        this.type=-1;
        this.Username=username;
        this.Password=password;
    }

    public User( String username, String password, int type) {
        Username = username;
        Password = password;
        this.type = type;
    }
}
