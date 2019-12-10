package com.example.Shared;
import java.util.Date;

public class Borrowed {

    public int borrowedId;
    public String username;
    public int itemId;
    public Date returnDate;
    public Date borrowDate;

    public Borrowed(int borrowedId, String user, int item, Date returnDate, Date borrowDate) {
        this.borrowedId = borrowedId;
        this.username = user;
        this.itemId = item;
        this.returnDate = returnDate;
        this.borrowDate = borrowDate;
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

    public Date getReturnDate() {
        return returnDate;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    @Override
    public String toString() {
        return "Borrowed{" +
                "borrowedId=" + borrowedId +
                ", username=" + username +
                ", itemId=" + itemId +
                ", returnDate=" + returnDate +
                ", borrowDate=" + borrowDate +
                '}';
    }
}
