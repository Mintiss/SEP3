package com.example.Shared;


import java.time.LocalDate;

public class Reservation {

    private int reservationId;
    private String username;
    private int itemId;
    private String reservedAt;
    private String reservationExpirationDate;

    public Reservation() {
    }

    public Reservation(int reservationId, String username, int itemId, LocalDate reservedAt, LocalDate reservationExpirationDate) {

        this.reservationId = reservationId;
        this.username = username;
        this.itemId = itemId;
        this.reservedAt = reservedAt.toString();
        this.reservationExpirationDate = reservationExpirationDate.toString();
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(String reservedAt) {
        this.reservedAt = reservedAt;
    }

    public String getReservationExpirationDate() {
        return reservationExpirationDate;
    }

    public void setReservationExpirationDate(String reservationExpirationDate) {
        this.reservationExpirationDate = reservationExpirationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", username='" + username + '\'' +
                ", itemId=" + itemId +
                ", reservedAt=" + reservedAt +
                ", reservationExpirationDate=" + reservationExpirationDate +
                '}';
    }
}
