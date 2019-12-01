package com.example.networking.model;


import com.example.Shared.Item;
import com.example.Shared.User;
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

    public ServerModel() {
        uc=new UserController();
        ic = new ItemController();

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
        ArrayList<Item> items = new ArrayList<Item>();
        items = ic.getItems();


    }
}
