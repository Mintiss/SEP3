package Shared;

import java.io.Serializable;

public class User implements Serializable {
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

    public User(String string){
        String array[]= string.split(",");

        this.Username=array[0];
        this.Password= array[1];
        this.type=Integer.parseInt(array[2]);
    }

    public User(String username, String password){
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
