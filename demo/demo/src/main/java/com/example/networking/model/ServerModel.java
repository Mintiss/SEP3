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
    private int borrowedMonths;

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

    public void checkUserInfoOnLogin(User user){

        User userGotFromDB =uc.checkLogInTier1Librarian(user);

        if (userGotFromDB==null)
        {
            System.out.println("null");
            support.firePropertyChange("LogInFailed",null,user);
        }
        else{
            support.firePropertyChange("LogInSuccess",null,user);
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
        ArrayList<Borrowed> borrowed = bc.getBorrowed();
        ArrayList<Reservation> reservations = rc.getReservations();
        boolean containsUser = false;
        for(int i = 0; i<borrowed.size();i++)
        {
            if(borrowed.get(i).getItemId()==Integer.parseInt(json))
                if(!borrowed.get(i).isReturned())
                    containsUser = true;
        }
        for(int i = 0; i<reservations.size();i++)
        {
            if(reservations.get(i).getItemId()==Integer.parseInt(json))
                containsUser = true;
        }
        if (!containsUser){
            ic.deleteItem(json);
            this.items = ic.getItems();
            support.firePropertyChange("UpdateMainTable",null,null);
        }
        else {
            support.firePropertyChange("CannotDeleteItem",null,null);

        }

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
                if(!borrowedTable.get(i).isReturned())
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

    public void deleteUser(String json) {
        ArrayList<Borrowed> borrowed = bc.getBorrowed();
        ArrayList<Reservation> reservations = rc.getReservations();
        boolean containsUser = false;
        for(int i = 0; i<borrowed.size();i++)
        {
            if(borrowed.get(i).getUsername().equals(json))
                if(!borrowed.get(i).isReturned())
                    containsUser = true;
        }
        for(int i = 0; i<reservations.size();i++)
        {
            if(reservations.get(i).getUsername().equals(json))
                containsUser = true;
        }
        if (!containsUser){
            uc.deleteUser(json);
            this.users = uc.getUsers();
            support.firePropertyChange("UpdateUsersTable",null,null);
        }
        else {
            support.firePropertyChange("CannotDeleteUser",null,null);

        }

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
    public void searchLendItem(String json) {
        ArrayList<User> users = uc.getUsers();
        ArrayList<User> searchUsers = new ArrayList<User>();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().contains(json)) {
                searchUsers.add(users.get(i));
                System.out.println("match found");
                System.out.println(users.get(i).getUsername());
            }
        }
        this.users = searchUsers;
        support.firePropertyChange("UpdateUsersTable", null, null);
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
        UpdateFinesTable();
    }


    public void deleteReservation(Reservation fromJson) {
        rc.deleteReservation(Integer.toString(fromJson.getReservationId()));
        this.reservations = rc.getReservations();
        support.firePropertyChange("UpdateReservationsTable", null, null);
    }

    public void setBorrowedMonths(String json) {
        this.borrowedMonths = Integer.parseInt(json);
    }

    public void moveToBorrowed(Reservation reservation) {
        rc.deleteReservation(Integer.toString(reservation.getReservationId()));
        Borrowed borrowed = new Borrowed(0,reservation.getUsername(), reservation.getItemId(),LocalDate.now().plusMonths(borrowedMonths),LocalDate.now());
        System.out.println(borrowed);
        borrowItem(borrowed);
        UpdateReservationTable();
    }


    public void searchReservedId(String json) {
        ArrayList<Reservation> reservations = rc.getReservations();
        ArrayList<Reservation> searchReservations = new ArrayList<Reservation>();

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId()==Integer.parseInt(json)) {
                searchReservations.add(reservations.get(i));
                System.out.println("match found");
                System.out.println(reservations.get(i).getReservationId());
            }
        }
        this.reservations = searchReservations;
        support.firePropertyChange("UpdateReservationsTable", null, null);
    }

    public void searchReservedItemId(String json) {
        ArrayList<Reservation> reservations = rc.getReservations();
        ArrayList<Reservation> searchReservations = new ArrayList<Reservation>();

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getItemId()==Integer.parseInt(json)) {
                searchReservations.add(reservations.get(i));
                System.out.println("match found");
                System.out.println(reservations.get(i).getItemId());
            }
        }
        this.reservations = searchReservations;
        support.firePropertyChange("UpdateReservationsTable", null, null);
    }

    public void searchReservedUsername(String json) {
        ArrayList<Reservation> reservations = rc.getReservations();
        ArrayList<Reservation> searchReservations = new ArrayList<Reservation>();

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getUsername().contains(json)) {
                searchReservations.add(reservations.get(i));
                System.out.println("match found");
                System.out.println(reservations.get(i).getUsername());
            }
        }
        this.reservations = searchReservations;
        support.firePropertyChange("UpdateReservationsTable", null, null);
    }

    public void searchBorrowedId(String json) {
        ArrayList<Borrowed> borrowed = bc.getBorrowed();
        ArrayList<Borrowed> searchBorrowed = new ArrayList<Borrowed>();

        for (int i = 0; i < borrowed.size(); i++) {
            if (borrowed.get(i).getBorrowedId()==Integer.parseInt(json)) {
                searchBorrowed.add(borrowed.get(i));
                System.out.println("match found");
                System.out.println(borrowed.get(i).getBorrowedId());
            }
        }
        this.borrowed = searchBorrowed;
        support.firePropertyChange("UpdateBorrowedTable", null, null);
    }

    public void searchBorrowedItemId(String json) {
        ArrayList<Borrowed> borrowed = bc.getBorrowed();
        ArrayList<Borrowed> searchBorrowed = new ArrayList<Borrowed>();

        for (int i = 0; i < borrowed.size(); i++) {
            if (borrowed.get(i).getItemId()==Integer.parseInt(json)) {
                searchBorrowed.add(borrowed.get(i));
                System.out.println("match found");
                System.out.println(borrowed.get(i).getItemId());
            }
        }
        this.borrowed = searchBorrowed;
        support.firePropertyChange("UpdateBorrowedTable", null, null);
    }

    public void searchBorrowedUsername(String json) {
        ArrayList<Borrowed> borrowed = bc.getBorrowed();
        ArrayList<Borrowed> searchBorrowed = new ArrayList<Borrowed>();

        for (int i = 0; i < borrowed.size(); i++) {
            if (borrowed.get(i).getUsername().contains(json)) {
                searchBorrowed.add(borrowed.get(i));
                System.out.println("match found");
                System.out.println(borrowed.get(i).getUsername());
            }
        }
        this.borrowed = searchBorrowed;
        support.firePropertyChange("UpdateBorrowedTable", null, null);
    }

    public void searchFinesId(String json) {
        UpdateFinesTable();
        ArrayList<Borrowed> fines = getFines();
        ArrayList<Borrowed> searchFines = new ArrayList<Borrowed>();

        for (int i = 0; i < fines.size(); i++) {
            if (fines.get(i).getBorrowedId()==Integer.parseInt(json)) {
                searchFines.add(fines.get(i));
                System.out.println("match found");
                System.out.println(fines.get(i).getBorrowedId());
            }
        }
        this.fines = searchFines;
        support.firePropertyChange("UpdateFinesTable", null, null);
    }

    public void searchFinesItemId(String json) {
        UpdateFinesTable();
        ArrayList<Borrowed> fines = getFines();
        ArrayList<Borrowed> searchFines = new ArrayList<Borrowed>();

        for (int i = 0; i < fines.size(); i++) {
            if (fines.get(i).getItemId()==Integer.parseInt(json)) {
                searchFines.add(fines.get(i));
                System.out.println("match found");
                System.out.println(fines.get(i).getItemId());
            }
        }
        this.fines = searchFines;
        support.firePropertyChange("UpdateFinesTable", null, null);
    }

    public void searchFinesUsername(String json) {
        UpdateFinesTable();
        ArrayList<Borrowed> fines = getFines();
        ArrayList<Borrowed> searchFines = new ArrayList<Borrowed>();

        for (int i = 0; i < fines.size(); i++) {
            if (fines.get(i).getUsername().equals(json)) {
                searchFines.add(fines.get(i));
                System.out.println("match found");
                System.out.println(fines.get(i).getBorrowedId());
            }
        }
        this.fines = searchFines;
        support.firePropertyChange("UpdateFinesTable", null, null);
    }
}
