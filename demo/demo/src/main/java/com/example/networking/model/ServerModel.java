package com.example.networking.model;


import com.example.Shared.Borrowed;
import com.example.Shared.Item;
import com.example.Shared.User;
import com.example.SharedControllers.BorrowedController;
import com.example.SharedControllers.ItemController;
import com.example.SharedControllers.UserController;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.*;
import java.util.ArrayList;

public class ServerModel {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private Connection conn;
    private UserController uc;
    private ItemController ic;
    private BorrowedController bc;
    private ArrayList<Item> items;
    private ArrayList<Borrowed> borrowed;
    private ArrayList<User> users;

    public ServerModel() {
        uc=new UserController();
        ic = new ItemController();
        bc = new BorrowedController();
        items = new ArrayList<Item>();
        borrowed = new ArrayList<Borrowed>();
        users = new ArrayList<User>();
    }

    public void addListener(java.lang.String eventName, PropertyChangeListener listener) {
        if (eventName == null || "".equals(eventName)) {
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(eventName, listener);
        }
    }

    public void checkUserInfoOnLogin(User user){
        User userGotFromDB =uc.getUserFromDB(user.getUsername());
        if (userGotFromDB.getUsername()==null)
        {
            System.out.println("null");
        support.firePropertyChange("LogInFailed",null,null);
        }
        else if (userGotFromDB.getUsername().equals(user.getUsername())){
            if (userGotFromDB.getType()==1)
            {
                if (userGotFromDB.getPassword().equals(user.getPassword()))
                    support.firePropertyChange("LogInSuccess",null,null);
                else
                    support.firePropertyChange("LogInFailed",null,null);

            }
            else
                support.firePropertyChange("LogInFailed",null,null);
        }
    }

    public void UpdateMainTable() {
        this.items = ic.getItems();
        support.firePropertyChange("UpdateMainTable",null,null);
    }

    public ArrayList<Borrowed> getBorrowed() {
        return borrowed;
    }

    public void UpdateBorrowedTable() {
        this.borrowed = bc.getBorrowed();
        support.firePropertyChange("UpdateBorrowedTable", null, null);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void editItem(Item fromJson) {
        ic.putItem(fromJson);
        this.items = ic.getItems();
        support.firePropertyChange("UpdateMainTable",null,null);
    }

    public void addItem(Item fromJson) {
        ic.postItem(fromJson);
        this.items = ic.getItems();
        support.firePropertyChange("UpdateMainTable",null,null);
    }

    public void deleteItem(java.lang.String json) {
        ic.deleteItem(json);
        this.items = ic.getItems();
        support.firePropertyChange("UpdateMainTable",null,null);
    }


    public void UpdateUsersTable() {
        this.users = uc.getUsers();
        support.firePropertyChange("UpdateUsersTable", null, null);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void deleteUser(java.lang.String json) {
        uc.deleteUser(json);
        this.users = uc.getUsers();
        support.firePropertyChange("UpdateUsersTable",null,null);

    }

    public void changePassword(User user) {
        uc.changePassword(user);
        this.users = uc.getUsers();
        support.firePropertyChange("UpdateUsersTable",null,null);

    }
}
