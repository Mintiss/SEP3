package Model;

import Shared.Borrowed;
import Shared.Item;
import networkingC.Client;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface IModel {
    void setClient(Client client);
    void logInAction(String user,String password);
    void logInConfirmed();
    void logInFailed();
    void addListener(String eventName, PropertyChangeListener listener);
    void updateMainTable();
    void setMainTable(ArrayList<Item> items);
    ArrayList<Item> getMainTable();
    Item getStoredItem();
    void setStoredItem(Item storedItem);

    void error(String text);

    void info(String text);

    void editItem(Item item);

    void addItem(Item item);

    void deleteItem();

    ArrayList<Borrowed> getBorrowedTable();

    void updateBorrowedTable();

    void setBorrowedTable(ArrayList<Borrowed> borrowed);
}
