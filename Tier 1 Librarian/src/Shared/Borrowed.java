package Shared;

import java.util.Date;
import java.time.LocalDate;

public class Borrowed {

    private int borrowedId;
    private User user;
    private Item item;
    private Date returnDate;
    private Date borrowDate;

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
