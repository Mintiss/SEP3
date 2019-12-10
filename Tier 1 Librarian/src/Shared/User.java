package Shared;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private int type;

    public User(String username, String password, int type) {
        this.username = username;
        this.password = password;
        this.type=type;
    }

    public User(String username, String password){
        this.username =username;
        this.password =password;
        this.type=-1;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "String{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
