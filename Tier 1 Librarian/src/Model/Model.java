package Model;

import Shared.Borrowed;
import Shared.Item;
import Shared.JsonInstruction;
import Shared.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import networkingC.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Model implements IModel {

    private Client client;
    private PropertyChangeSupport support=new PropertyChangeSupport(this);
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();
    private ArrayList<Item> mainTable;
    private ArrayList<Borrowed> borrowedTable;
    private Item storedItem;


    public Model() {
        mainTable = new ArrayList<Item>();
        borrowedTable = new ArrayList<Borrowed>();
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

    public void logInAction(String username,String password){
        client.sendInfo(gson.toJson(new JsonInstruction(gson.toJson(new User(username,password)),"LoginInfo")));
    }


    public void logInConfirmed() {
        support.firePropertyChange("LogInConfirmed",null,null);
    }


    public void logInFailed() {
        support.firePropertyChange("LogInFailed",null,null);
    }

    public void updateMainTable(){
        client.sendInfo("UpdateMainTable");
    }

    @Override
    public void updateBorrowedTable() {
        client.sendInfo("UpdateBorrowedTable");
    }

    @Override
    public void setMainTable(ArrayList<Item> items) {
        mainTable = items;
        System.out.println("main table" + mainTable.toString());
        support.firePropertyChange("UpdateMainTable",null,null);
    }

    @Override
    public void setBorrowedTable(ArrayList<Borrowed> borrowed) {
        borrowedTable = borrowed;
        System.out.println("borrowed table" + borrowedTable.toString());
        support.firePropertyChange("UpdateBorrowedTable",null,null);
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
    public void info(String text)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.showAndWait();
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
    public ArrayList<Borrowed> getBorrowedTable() {
        return borrowedTable;
    }
}
