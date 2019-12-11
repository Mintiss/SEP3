package Model;

import Shared.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
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
        LocalDate today = LocalDate.now();
            client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(new Borrowed(
                    getStoredUser().getUsername(),
                    getStoredItem().getItemId(),
                    today.plusMonths(Long.parseLong(value)),
                    today
            )),"BorrowItem")));
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
        client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchMainId")));
    }@Override
    public void searchMainTitle(String value) {
        client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchMainTitle")));
    }@Override
    public void searchMainAuthor(String value) {
        client.sendInfo(gson.toJson(new JsonInstruction(value,"SearchMainAuthor")));
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
        info("Item Deleted.");
    }

    @Override
    public void deleteUser() {
        client.sendInfo(gson.toJson(new JsonInstruction(getStoredUser().getUsername(),"DeleteUser")));
        info("String Deleted.");
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
