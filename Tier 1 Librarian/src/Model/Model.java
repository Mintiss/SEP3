package Model;

import Shared.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import networkingC.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;

public class Model implements IModel {

    private Client client;
    private PropertyChangeSupport support=new PropertyChangeSupport(this);
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();
    private ArrayList<Item> mainTable;
    private ArrayList<Borrowed> borrowedTable;
    private ArrayList<Reservation> reservationTable;
    private ArrayList<User> usersTable;
    private ArrayList<Borrowed> finesTable;
    private Item storedItem;
    private User storedUser;
    private Borrowed storedBorrow;
    private Borrowed storedFine;
    private Reservation storedReservation;


    public Model() {
        mainTable = new ArrayList<Item>();
        borrowedTable = new ArrayList<Borrowed>();
        reservationTable = new ArrayList<Reservation>();
        usersTable = new ArrayList<User>();
        finesTable = new ArrayList<Borrowed>();
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        if(eventName == null || "".equals(eventName)) {
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(eventName, listener);
        }
    }

    public void setClient(Client client){
        this.client=client;
    }

    public void logInAction(String username, String password){
        client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(new User(username,password)),"LoginInfo")));
    }


    public void logInConfirmed() {
        support.firePropertyChange("LogInConfirmed",null,null);
    }

    @Override
    public User getStoredUser() {
        return storedUser;
    }
    @Override
    public Borrowed getStoredFine() {
        return storedFine;
    }
    @Override
    public void setStoredFine(Borrowed storedFine) {
        this.storedFine = storedFine;
    }
    @Override
    public Reservation getStoredReservation() {
        return storedReservation;
    }
    @Override
    public void setStoredReservation(Reservation storedReservation) {
        this.storedReservation = storedReservation;
    }

    @Override
    public void setStoredUser(User storedUser) {
        this.storedUser = storedUser;
    }

    @Override
    public void setStoredBorrow(Borrowed storedValue) {
        this.storedBorrow = storedValue;
    }

    @Override
    public Borrowed getStoredBorrow() {
        return storedBorrow;
    }

    public void logInFailed() {
        support.firePropertyChange("LogInFailed",null,null);
    }

    public void updateMainTable(){
        client.sendInfo("UpdateMainTable");
    }
    @Override
    public void returnItem()
    {
        client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(storedBorrow),"ReturnedItem")));
    }

    @Override
    public void deleteReservation() {
        client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(getStoredReservation()),"DeleteReservation")));

    }

    @Override
    public void payFine(Borrowed storedValue) {
        client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(storedValue),"PayFine")));
    }

    @Override
    public void moveToBorrowed(String months) {
        try{

            client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(Integer.parseInt(months)),"Months")));
            client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(getStoredReservation()),"MoveToBorrowed")));

        }
        catch (Exception e)
        {
            error("Enter a number in the months field");
        }

    }

    @Override
    public void updateBorrowedTable() {
        client.sendInfo("UpdateBorrowedTable");
    }

    @Override
    public void updateFinesTable() {
        client.sendInfo("UpdateFinesTable");

    }

    @Override
    public void updateUsersTable() { client.sendInfo("UpdateUsersTable");}

    @Override
    public void setMainTable(ArrayList<Item> items) {
        mainTable = items;
        System.out.println("main table" + mainTable.toString());
        support.firePropertyChange("UpdateMainTable",null,null);
    }
    @Override
    public void setUsersTable(ArrayList<User> users) {
        usersTable = users;
        System.out.println("users table" + usersTable.toString());
        support.firePropertyChange("UpdateUsersTable",null,null);
    }


    @Override
    public void setBorrowedTable(ArrayList<Borrowed> borrowed) {
        System.out.println(borrowed.toString());
        borrowedTable = borrowed;
        System.out.println("borrowed table" + borrowedTable.toString());
        support.firePropertyChange("UpdateBorrowedTable",null,null);
    }

    @Override
    public void setFinesTable(ArrayList<Borrowed> fines) {
        System.out.println(fines.get(0).getBorrowDate().toString());
        finesTable = fines;
        System.out.println("borrowed table" + finesTable.toString());
        support.firePropertyChange("UpdateFinesTable",null,null);
    }

    @Override
    public ArrayList<Reservation> getReservedTable() {
        return reservationTable;
    }
    @Override
    public ArrayList<User> getUsersTable() {
        return usersTable;
    }

    @Override
    public void confirmBorrow(String value) {
        try {
            LocalDate today = LocalDate.now();
            client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(new Borrowed(
                    getStoredUser().getUsername(),
                    getStoredItem().getItemId(),
                    today.plusMonths(Long.parseLong(value)),
                    today
            )), "BorrowItem")));
        }
        catch (Exception e)
        {
            error("Enter a number in the months field");
        }
    }

    @Override
    public void updateReservedTable() {
        client.sendInfo("UpdateReservationTable");
    }

    @Override
    public void setReservedTable(ArrayList<Reservation> reservations) {
        reservationTable = reservations;
        System.out.println("reservation table" + reservationTable.toString());
        support.firePropertyChange("UpdateReservationTable",null,null);
    }

    @Override
    public ArrayList<Item> getMainTable() {
        return mainTable;
    }

    @Override
    public Item getStoredItem() {
        return storedItem;
    }
    @Override
    public void setStoredItem(Item storedItem) {
        this.storedItem = storedItem;
    }
    @Override
    public void error(String text)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(text);
        alert.showAndWait();
    }

    @Override
    public void noItemsLeft() {
        Platform.runLater(()->error("No Items Left To Borrow."));
    }

    @Override
    public void info(String text)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @Override
    public void searchMainId(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchMainId")));
    }@Override
    public void searchMainTitle(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
        client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchMainTitle")));
    }@Override
    public void searchMainAuthor(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
        client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchMainAuthor")));
    }

    @Override
    public void searchReservedId(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchReservedId")));
    }

    @Override
    public void searchReservedItemId(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchReservedItemId")));
    }

    @Override
    public void searchReservedUsername(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchReservedUsername")));
    }

    @Override
    public void searchBorrowedId(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchBorrowedId")));
    }

    @Override
    public void searchBorrowedItemId(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchBorrowedItemId")));
    }

    @Override
    public void searchBorrowedUsername(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchBorrowedUsername")));
    }

    @Override
    public void searchFinesId(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchFinesId")));
    }

    @Override
    public void searchFinesItemId(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchFinesItemId")));
    }

    @Override
    public void searchFinesUsername(String value) {
        if(value.equals("")|| value==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchFinesUsername")));
    }

    @Override
    public void searchLendItem(String search) {
        if(search.equals("")|| search==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(search,"SearchLendItem")));
    }

    @Override
    public void searchUserList(String search) {
        if(search.equals("")|| search==null)
            error("Search is empty");
        else
            client.sendInfo(gson.toJson(new JsonInstruction(search,"SearchUserList")));
    }

    @Override
    public void editItem(Item item) {
        client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(item),"EditItem")));
    }

    @Override
    public void addItem(Item item) {
        client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(item),"AddItem")));
    }

    @Override
    public void deleteItem() {
        client.sendInfo(gson.toJson(new JsonInstruction(Integer.toString(getStoredItem().getItemId()),"DeleteItem")));
    }

    @Override
    public void deleteUser() {
        client.sendInfo(gson.toJson(new JsonInstruction(getStoredUser().getUsername(),"DeleteUser")));
    }

    @Override
    public void changePassword(User user) {
        client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(user),"ChangePassword")));
        info("Password Changed");
    }

    @Override
    public ArrayList<Borrowed> getBorrowedTable() {
        return borrowedTable;
    }
    @Override
    public ArrayList<Borrowed> getFinesTable() {
        return finesTable;
    }

}
