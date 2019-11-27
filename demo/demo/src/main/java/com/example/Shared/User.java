package com.example.Shared;

public class User {
    private int id;
    private String Username;
    private String Password;
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

    public User(String username,String password){
        this.id=-1;
        this.type=-1;
        this.Username=username;
        this.Password=password;
    }

    public User(int id, String username, String password, int type) {
        this.id = id;
        Username = username;
        Password = password;
        this.type = type;
    }
}
