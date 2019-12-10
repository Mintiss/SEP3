package networkingC;

import Shared.Borrowed;
import Shared.Item;
import Shared.User;

import java.util.ArrayList;

public interface Client {
    void searchBook(String bookName);
    void sendInfo(String jsonInstruction);
    void updateMainTable(ArrayList<Item> items);

    void updateBorrowedTable(ArrayList<Borrowed> borrowed);

    void updateUsersTable(ArrayList<User> users);
}
