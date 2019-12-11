package com.example.Shared;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Borrowed {
    @JsonProperty("borrowedId")
    private int borrowedId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("itemId")
    private int itemId;
    @JsonProperty("returnDate")
    private String returnDate;
    @JsonProperty("borrowDate")
    private String borrowDate;
    @JsonProperty("isReturned")
    private boolean isReturned;

    public Borrowed(){

    }

    public Borrowed(int borrowedId, String user, int item, LocalDate returnDate, LocalDate borrowDate) {
        this.borrowedId = borrowedId;
        this.username = user;
        this.itemId = item;
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



    public boolean isReturned() {
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
