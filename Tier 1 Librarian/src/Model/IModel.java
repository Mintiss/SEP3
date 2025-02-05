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

    Borrowed getStoredFine();

    void setStoredFine(Borrowed storedFine);

    Reservation getStoredReservation();

    void setStoredReservation(Reservation storedReservation);

    void setStoredUser(User storedUser);

    Borrowed getStoredBorrow();

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

    void searchMainId(String value);

    void searchMainTitle(String value);

    void searchMainAuthor(String value);

    void editItem(Item item);

    void addItem(Item item);

    void deleteItem();

    void changePassword(User user);

    ArrayList<Borrowed> getBorrowedTable();

    void updateBorrowedTable();

    void setUsersTable(ArrayList<User> users);

    void setBorrowedTable(ArrayList<Borrowed> borrowed);

    void deleteUser();

    void confirmBorrow(String value);

    void noItemsLeft();

    ArrayList<Borrowed> getFinesTable();

    void updateFinesTable();

    void setFinesTable(ArrayList<Borrowed> fines);

    void setStoredBorrow(Borrowed storedValue);

    void returnItem();

    void payFine(Borrowed storedValue);

    void searchLendItem(String search);

    void searchUserList(String search);

    void deleteReservation();

    void moveToBorrowed(String months);

    void searchReservedId(String value);

    void searchReservedItemId(String value);

    void searchReservedUsername(String value);

    void searchBorrowedId(String value);

    void searchBorrowedItemId(String value);

    void searchBorrowedUsername(String value);

    void searchFinesId(String value);

    void searchFinesItemId(String value);

    void searchFinesUsername(String value);
}
