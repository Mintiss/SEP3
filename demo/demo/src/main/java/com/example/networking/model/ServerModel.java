package com.example.networking.model;


import com.example.Shared.User;
import com.example.demo.DemoApplication;
import com.example.forSocketsTest.Book;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServerModel {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private Connection conn;


    public ServerModel() {
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
        
    }
}
