package Shared;


import java.time.LocalDate;

public class Reservation {

    public int ReservationId;
    public String User;
    public Item Item;
    public LocalDate ReservedAt;
    public LocalDate ReservationExpirationDate;

    public Reservation(int reservationId, String user, Shared.Item item, LocalDate reservedAt, LocalDate reservationExpirationDate) {
        ReservationId = reservationId;
        User = user;
        Item = item;
        ReservedAt = reservedAt;
        ReservationExpirationDate = reservationExpirationDate;
    }

    public int getReservationId() {
        return ReservationId;
    }

    public void setReservationId(int reservationId) {
        ReservationId = reservationId;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public Shared.Item getItem() {
        return Item;
    }

    public void setItem(Shared.Item item) {
        Item = item;
    }

    public LocalDate getReservedAt() {
        return ReservedAt;
    }

    public void setReservedAt(LocalDate reservedAt) {
        ReservedAt = reservedAt;
    }

    public LocalDate getReservationExpirationDate() {
        return ReservationExpirationDate;
    }

    public void setReservationExpirationDate(LocalDate reservationExpirationDate) {
        ReservationExpirationDate = reservationExpirationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ReservationId=" + ReservationId +
                ", String=" + User +
                ", Item=" + Item +
                ", ReservedAt=" + ReservedAt +
                ", ReservationExpirationDate=" + ReservationExpirationDate +
                '}';
    }
}
