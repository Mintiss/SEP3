package Model;

import Shared.Borrowed;
import Shared.Item;
import Shared.Reservation;
import Shared.User;
import networkingC.Client;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface IModel {
    void setClient(Client client);
    void logInAction(String user, String password);
    void logInConfirmed();

    User getStoredUser();

    void setStoredUser(User storedUser);

    void logInFailed();
    void addListener(String eventName, PropertyChangeListener listener);
    void updateMainTable();

    void updateUsersTable();

    void setMainTable(ArrayList<Item> items);
    ArrayList<Item> getMainTable();
    ArrayList<Reservation> getReservedTable();

    ArrayList<User> getUsersTable();

    void updateReservedTable();
    void setReservedTable(ArrayList<Reservation> reservations);
    Item getStoredItem();
    void setStoredItem(Item storedItem);

    void error(String text);

    void info(String text);

    void editItem(Item item);

    void addItem(Item item);

    void deleteItem();

    void changePassword(User user);

    ArrayList<Borrowed> getBorrowedTable();

    void updateBorrowedTable();

    void setUsersTable(ArrayList<User> users);

    void setBorrowedTable(ArrayList<Borrowed> borrowed);

    void deleteUser();

}
