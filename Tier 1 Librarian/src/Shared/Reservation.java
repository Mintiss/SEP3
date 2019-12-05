package Shared;

import java.util.Date;

public class Reservation {

    public int ReservationId;
    public User User;
    public Item Item;
    public Date ReservedAt;
    public Date ReservationExpirationDate;

    public Reservation(int reservationId, Shared.User user, Shared.Item item, Date reservedAt, Date reservationExpirationDate) {
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

    public Shared.User getUser() {
        return User;
    }

    public void setUser(Shared.User user) {
        User = user;
    }

    public Shared.Item getItem() {
        return Item;
    }

    public void setItem(Shared.Item item) {
        Item = item;
    }

    public Date getReservedAt() {
        return ReservedAt;
    }

    public void setReservedAt(Date reservedAt) {
        ReservedAt = reservedAt;
    }

    public Date getReservationExpirationDate() {
        return ReservationExpirationDate;
    }

    public void setReservationExpirationDate(Date reservationExpirationDate) {
        ReservationExpirationDate = reservationExpirationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ReservationId=" + ReservationId +
                ", User=" + User +
                ", Item=" + Item +
                ", ReservedAt=" + ReservedAt +
                ", ReservationExpirationDate=" + ReservationExpirationDate +
                '}';
    }
}
