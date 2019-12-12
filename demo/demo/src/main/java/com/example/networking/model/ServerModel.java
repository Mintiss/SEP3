package com.example.networking.model;


import com.example.Shared.Borrowed;
import com.example.Shared.Item;
import com.example.Shared.Reservation;
import com.example.Shared.User;
import com.example.SharedControllers.BorrowedController;
import com.example.SharedControllers.ItemController;
import com.example.SharedControllers.ReservationsController;
import com.example.SharedControllers.UserController;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ServerModel {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private Connection conn;
    private UserController uc;
    private ItemController ic;
    private BorrowedController bc;
    private ReservationsController rc;
    private ArrayList<Item> items;
    private ArrayList<Borrowed> borrowed;
    private ArrayList<Borrowed> fines;
    private ArrayList<Reservation> reservations;
    private ArrayList<User> users;

    public ServerModel() {
        uc=new UserController();
        ic = new ItemController();
        bc = new BorrowedController();
        rc = new ReservationsController();
        items = new ArrayList<Item>();
        reservations = new ArrayList<Reservation>();
        borrowed = new ArrayList<Borrowed>();
        fines = new ArrayList<Borrowed>();
        users = new ArrayList<User>();
    }

    public void addListener(java.lang.String eventName, PropertyChangeListener listener) {
        if (eventName == null || "".equals(eventName)) {
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(eventName, listener);
        }
    }

    //FIX IT DO LOGIC IN T3
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
    public void UpdateReservationTable() {
        this.reservations = rc.getReservations();
        support.firePropertyChange("UpdateReservationsTable", null, null);
    }

    public void UpdateUsersTable() {
        this.users = uc.getUsers();
        support.firePropertyChange("UpdateUsersTable", null, null);
    }
    public void UpdateFinesTable() {
        ArrayList<Borrowed> borrowedTable = bc.getBorrowed();
        ArrayList<Borrowed> fines = new ArrayList<Borrowed>();
        LocalDate returnDate;
        System.out.println(bc.getBorrowed().toString());

        for(int i=0; i<borrowedTable.size();i++)
        {
            System.out.println(borrowedTable.get(i).getReturnDate());
            returnDate = LocalDate.parse(borrowedTable.get(i).getReturnDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            System.out.println(returnDate + "    "+LocalDate.parse(LocalDate.now().toString()) );
            if(LocalDate.parse(LocalDate.now().toString()).isAfter(returnDate)){
                fines.add(borrowedTable.get(i));
            }
        }
        this.fines= fines;
        support.firePropertyChange("UpdateFinesTable", null, null);
    }

    public ArrayList<Borrowed> getFines() {
        return fines;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
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

    public void borrowItem(Borrowed fromJson) {
        Item borrowItem = ic.getItem(fromJson.getItemId());
        if (borrowItem.getQuantity()>=1) {
            borrowItem.setQuantity(borrowItem.getQuantity()-1);
            ic.putItem(borrowItem);
            bc.borrowItem(fromJson);
            this.items = ic.getItems();
            this.borrowed = bc.getBorrowed();
            support.firePropertyChange("UpdateBorrowedTable", null, null);
            support.firePropertyChange("UpdateMainTable", null, null);
            support.firePropertyChange("ItemBorrowed", null, null);

        }
        else
            support.firePropertyChange("NoItemsLeft", null, null);

    }

    public void searchMainId(String json) {
        ArrayList<Item> items = ic.getItems();
        ArrayList<Item> searchItems = new ArrayList<Item>();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemId()==Integer.parseInt(json)) {
                searchItems.add(items.get(i));
                System.out.println("match found");
                System.out.println(items.get(i).getItemId());
            }
        }
        this.items = searchItems;
        support.firePropertyChange("UpdateMainTable", null, null);
    }
    public void searchMainAuthor(String json) {
        ArrayList<Item> items = ic.getItems();
        ArrayList<Item> searchItems = new ArrayList<Item>();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getAuthor().contains(json)) {
                searchItems.add(items.get(i));
                System.out.println("match found");
                System.out.println(items.get(i).getAuthor());
            }
        }
        this.items = searchItems;
        support.firePropertyChange("UpdateMainTable", null, null);
    }
    public void searchMainTitle(String json) {
        ArrayList<Item> items = ic.getItems();
        ArrayList<Item> searchItems = new ArrayList<Item>();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().contains(json)) {
                searchItems.add(items.get(i));
                System.out.println("match found");
                System.out.println(items.get(i).getAuthor());
            }
        }
        this.items = searchItems;
        support.firePropertyChange("UpdateMainTable", null, null);
    }

    public void returnItem(Borrowed borrowed) {
        Item borrowedItem  = ic.getItem(borrowed.getItemId());
        borrowedItem.setQuantity(borrowedItem.getQuantity()+1);
        ic.putItem(borrowedItem);
        borrowed.setReturned(true);
        bc.putBorrowed(borrowed);
        this.items = ic.getItems();
        this.borrowed = bc.getBorrowed();
        support.firePropertyChange("UpdateMainTable", null, null);
        support.firePropertyChange("UpdateBorrowedTable", null, null);
    }


}
