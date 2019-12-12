package Shared;

import java.io.Serializable;
import java.time.LocalDate;

public class Borrowed {

    private int borrowedId;
    private String username;
    private int itemId;
    private String returnDate;
    private String borrowDate;
    private boolean isReturned;

    public Borrowed(String user, int itemid, LocalDate returnDate, LocalDate borrowDate) {
        this.borrowedId = 0;
        this.username = user;
        this.itemId = itemid;
        this.returnDate = returnDate.toString();
        this.borrowDate = borrowDate.toString();
        isReturned = false;
    }

    public Borrowed(int borrowedId, String user, int itemid, LocalDate returnDate, LocalDate borrowDate) {
        this.borrowedId = borrowedId;
        this.username = user;
        this.itemId = itemid;
        this.returnDate = returnDate.toString();
        this.borrowDate = borrowDate.toString();
        isReturned = false;
    }

    public int getBorrowedId() {
        return borrowedId;
    }

    public String getUsername() {
        return username;
    }

    public int getItemId() {
        return itemId;
    }

    public boolean getIsReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    @Override
    public String toString() {
        return "Borrowed{" +
                "borrowedId=" + borrowedId +
                ", username='" + username + '\'' +
                ", itemId=" + itemId +
                ", returnDate='" + returnDate + '\'' +
                ", borrowDate='" + borrowDate + '\'' +
                ", isReturned=" + isReturned +
                '}';
    }
}
