package Shared;

import java.io.Serializable;

public class User implements Serializable {
    private String Username;
    private String Password;
    private int type;

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }


    public User(String username, String password){
        this.Username=username;
        this.Password=password;
        this.type=-1;
    }

    public User(String username, String password,int type) {
        Username = username;
        Password = password;
        this.type=type;
    }
}
