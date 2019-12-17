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

@SuppressWarnings("Duplicates")
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

    public void checkUserInfoOnLogin(User user,String username){
        User userGotFromDB =uc.checkLogInTier1Librarian(user);

        if (userGotFromDB==null)
        {
            System.out.println("null");
            support.firePropertyChange("LogInFailed",null,username);
        }
        else{
            support.firePropertyChange("LogInSuccess",null,username);
        }
    }

    public void UpdateMainTable(String user) {
        this.items = ic.getItems();
        support.firePropertyChange("UpdateMainTable",null,user);
    }

    public ArrayList<Borrowed> getBorrowed() {
        return borrowed;
    }

    public void UpdateBorrowedTable(String username) {
        this.borrowed = bc.getBorrowed();
        support.firePropertyChange("UpdateBorrowedTable", null, username);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void editItem(Item fromJson,String username) {
        ic.putItem(fromJson);
        this.items = ic.getItems();
        support.firePropertyChange("UpdateMainTable",null,username);
    }

    public void addItem(Item fromJson,String username) {
        ic.postItem(fromJson);
        this.items = ic.getItems();
        support.firePropertyChange("UpdateMainTable",null,username);
    }

    public void deleteItem(String json,String username) {
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
            support.firePropertyChange("UpdateMainTable",null,username);
        }
        else {
            support.firePropertyChange("CannotDeleteItem",null,username);

        }

    }
    public void UpdateReservationTable(String username) {
        this.reservations = rc.getReservations();
        support.firePropertyChange("UpdateReservationsTable", null, username);
    }

    public void UpdateUsersTable(String username) {
        this.users = uc.getUsers();
        support.firePropertyChange("UpdateUsersTable", null, username);
    }
    public void UpdateFinesTable(String username) {
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
        support.firePropertyChange("UpdateFinesTable", null, username);
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

    public void deleteUser(String json,String username) {
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
            support.firePropertyChange("UpdateUsersTable",null,username);
        }
        else {
            support.firePropertyChange("CannotDeleteUser",null,username);

        }

    }

    public void changePassword(User user,String username) {
        uc.changePassword(user);
        this.users = uc.getUsers();
        support.firePropertyChange("UpdateUsersTable",null,username);

    }

    public void borrowItem(Borrowed fromJson,String username) {
        Item borrowItem = ic.getItem(fromJson.getItemId());
        if (borrowItem.getQuantity()>=1) {
            borrowItem.setQuantity(borrowItem.getQuantity()-1);
            ic.putItem(borrowItem);
            bc.borrowItem(fromJson);
            this.items = ic.getItems();
            this.borrowed = bc.getBorrowed();
            support.firePropertyChange("UpdateBorrowedTable", null, username);
            support.firePropertyChange("UpdateMainTable", null, username);
            support.firePropertyChange("ItemBorrowed", null, username);

        }
        else
            support.firePropertyChange("NoItemsLeft", null, username);

    }

    public void searchMainId(String json,String username) {
        ArrayList<Item> items = ic.getItems();
        ArrayList<Item> searchItems = new ArrayList<Item>();

        int intjson;

        try{
            intjson=Integer.parseInt(json);
        }
        catch (Exception e){
            intjson=-1;
        }


        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemId()==intjson) {
                searchItems.add(items.get(i));
                System.out.println("match found");
                System.out.println(items.get(i).getItemId());
            }
        }
        this.items = searchItems;
        support.firePropertyChange("UpdateMainTable", null, username);
    }

    public void searchMainAuthor(String json,String username) {
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
        support.firePropertyChange("UpdateMainTable", null, username);
    }
    public void searchMainTitle(String json,String username) {
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
        support.firePropertyChange("UpdateMainTable", null, username);
    }
    public void searchLendItem(String json,String username) {
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
        support.firePropertyChange("UpdateUsersTable", null, username);
    }

    public void returnItem(Borrowed borrowed,String username) {
        Item borrowedItem  = ic.getItem(borrowed.getItemId());
        borrowedItem.setQuantity(borrowedItem.getQuantity()+1);
        ic.putItem(borrowedItem);
        borrowed.setReturned(true);
        bc.putBorrowed(borrowed);
        this.items = ic.getItems();
        this.borrowed = bc.getBorrowed();
        support.firePropertyChange("UpdateMainTable", null, username);
        support.firePropertyChange("UpdateBorrowedTable", null, username);
        UpdateFinesTable(username);
    }


    public void deleteReservation(Reservation fromJson,String username) {
        rc.deleteReservation(Integer.toString(fromJson.getReservationId()));
        this.reservations = rc.getReservations();
        support.firePropertyChange("UpdateReservationsTable", null, username);
    }

    public void setBorrowedMonths(String json) {
        this.borrowedMonths = Integer.parseInt(json);
    }

    public void moveToBorrowed(Reservation reservation,String username) {
        rc.deleteReservation(Integer.toString(reservation.getReservationId()));
        Borrowed borrowed = new Borrowed(0,reservation.getUsername(), reservation.getItemId(),LocalDate.now().plusMonths(borrowedMonths),LocalDate.now());
        System.out.println(borrowed);
        borrowItem(borrowed,username);
        UpdateReservationTable(username);
    }


    public void searchReservedId(String json,String username) {
        ArrayList<Reservation> reservations = rc.getReservations();
        ArrayList<Reservation> searchReservations = new ArrayList<Reservation>();

        int intjson;

        try{
            intjson=Integer.parseInt(json);
        }
        catch (Exception e){
            intjson=-1;
        }


        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId()==intjson) {
                searchReservations.add(reservations.get(i));
                System.out.println("match found");
                System.out.println(reservations.get(i).getReservationId());
            }
        }
        this.reservations = searchReservations;
        support.firePropertyChange("UpdateReservationsTable", null, username);
    }

    public void searchReservedItemId(String json,String username) {
        ArrayList<Reservation> reservations = rc.getReservations();
        ArrayList<Reservation> searchReservations = new ArrayList<Reservation>();

        int intjson;

        try{
            intjson=Integer.parseInt(json);
        }
        catch (Exception e){
            intjson=-1;
        }


        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getItemId()==intjson) {
                searchReservations.add(reservations.get(i));
                System.out.println("match found");
                System.out.println(reservations.get(i).getItemId());
            }
        }
        this.reservations = searchReservations;
        support.firePropertyChange("UpdateReservationsTable", null, username);
    }

    public void searchReservedUsername(String json,String username) {
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
        support.firePropertyChange("UpdateReservationsTable", null, username);
    }

    public void searchBorrowedId(String json,String username) {
        ArrayList<Borrowed> borrowed = bc.getBorrowed();
        ArrayList<Borrowed> searchBorrowed = new ArrayList<Borrowed>();

        int intjson;

        try{
            intjson=Integer.parseInt(json);
        }
        catch (Exception e){
            intjson=-1;
        }

        for (int i = 0; i < borrowed.size(); i++) {
            if (borrowed.get(i).getBorrowedId()==intjson) {
                searchBorrowed.add(borrowed.get(i));
                System.out.println("match found");
                System.out.println(borrowed.get(i).getBorrowedId());
            }
        }
        this.borrowed = searchBorrowed;
        support.firePropertyChange("UpdateBorrowedTable", null, username);
    }

    public void searchBorrowedItemId(String json,String username) {
        ArrayList<Borrowed> borrowed = bc.getBorrowed();
        ArrayList<Borrowed> searchBorrowed = new ArrayList<Borrowed>();

        int intjson;

        try{
            intjson=Integer.parseInt(json);
        }
        catch (Exception e){
            intjson=-1;
        }

        for (int i = 0; i < borrowed.size(); i++) {

            if (borrowed.get(i).getItemId() == intjson) {
                searchBorrowed.add(borrowed.get(i));
                System.out.println("match found");
                System.out.println(borrowed.get(i).getItemId());
            }
        }
        this.borrowed = searchBorrowed;
        support.firePropertyChange("UpdateBorrowedTable", null, username);
    }

    public void searchBorrowedUsername(String json,String username) {
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
        support.firePropertyChange("UpdateBorrowedTable", null, username);
    }

    public void searchFinesId(String json,String username) {
        UpdateFinesTable(username);
        ArrayList<Borrowed> fines = getFines();
        ArrayList<Borrowed> searchFines = new ArrayList<Borrowed>();

        int intjson;

        try{
            intjson=Integer.parseInt(json);
        }
        catch (Exception e){
            intjson=-1;
        }


        for (int i = 0; i < fines.size(); i++) {
            if (fines.get(i).getBorrowedId()==intjson) {
                searchFines.add(fines.get(i));
                System.out.println("match found");
                System.out.println(fines.get(i).getBorrowedId());
            }
        }
        this.fines = searchFines;
        support.firePropertyChange("UpdateFinesTable", null, username);
    }

    public void searchFinesItemId(String json,String username) {
        UpdateFinesTable(username);
        ArrayList<Borrowed> fines = getFines();
        ArrayList<Borrowed> searchFines = new ArrayList<Borrowed>();

        int intjson;

        try{
            intjson=Integer.parseInt(json);
        }
        catch (Exception e){
            intjson=-1;
        }


        for (int i = 0; i < fines.size(); i++) {
            if (fines.get(i).getItemId()==intjson) {
                searchFines.add(fines.get(i));
                System.out.println("match found");
                System.out.println(fines.get(i).getItemId());
            }
        }
        this.fines = searchFines;
        support.firePropertyChange("UpdateFinesTable", null, username);
    }

    public void searchFinesUsername(String json,String username) {
        UpdateFinesTable(username);
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
        support.firePropertyChange("UpdateFinesTable", null, username);
    }
}
