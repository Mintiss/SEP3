package com.example.networking.model;


import com.example.Shared.Borrowed;
import com.example.Shared.Item;
import com.example.Shared.User;
import com.example.SharedControllers.BorrowedController;
import com.example.SharedControllers.ItemController;
import com.example.SharedControllers.UserController;
import com.example.forSocketsTest.Book;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServerModel {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private Connection conn;
    private UserController uc;
    private ItemController ic;
    private BorrowedController bc;
    private ArrayList<Item> items;
    private ArrayList<Borrowed> borrowed;

    public ServerModel() {
        uc=new UserController();
        ic = new ItemController();
        bc = new BorrowedController();
        items = new ArrayList<Item>();
        borrowed = new ArrayList<Borrowed>();
    }

    public void addListener(String eventName, PropertyChangeListener listener) {
        if (eventName == null || "".equals(eventName)) {
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(eventName, listener);
        }
    }

    public void searchForBook(String book)
    {
        List<Book> books=null;
        support.firePropertyChange("SearchBook",null,books);
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

    public void deleteItem(String json) {
        ic.deleteItem(json);
        this.items = ic.getItems();
        support.firePropertyChange("UpdateMainTable",null,null);
    }


}
