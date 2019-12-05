package com.example.Shared;
import java.time.LocalDate;
import java.util.Date;

public class Borrowed {

    public int borrowedId;
    public User user;
    public Item item;
    public Date returnDate;
    public Date borrowDate;

    public Borrowed(int borrowedId, User user, Item item, Date returnDate, Date borrowDate) {
        this.borrowedId = borrowedId;
        this.user = user;
        this.item = item;
        this.returnDate = returnDate;
        this.borrowDate = borrowDate;
    }

    public int getBorrowedId() {
        return borrowedId;
    }

    public User getUser() {
        return user;
    }

    public Item getItem() {
        return item;
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
                ", user=" + user +
                ", item=" + item +
                ", returnDate=" + returnDate +
                ", borrowDate=" + borrowDate +
                '}';
    }
}
