package networkingC;



import Shared.Borrowed;
import Shared.Item;
import Model.IModel;
import Shared.Reservation;
import Shared.User;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketClient implements Client{

    IModel model;

    ClientSocketHandler socketHandler;

    public SocketClient(IModel model){

        this.model=model;
        model.setClient(this);

        try {
            Socket socket= new Socket("localhost", 2910);
            socketHandler = new ClientSocketHandler(
                    new ObjectOutputStream(socket.getOutputStream()),
                    new ObjectInputStream(socket.getInputStream()), this);
            Thread t = new Thread(socketHandler);
            t.start();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void searchBookResult(List<Item> items) {
        ;
    }
    @Override
    public void logIn(){
        model.logInConfirmed();
    }
    @Override
    public void logInFailed(){
        model.logInFailed();
    }
    @Override
    public void noItemsLeft()
    {
        model.noItemsLeft();
    }

    @Override
    public void cannotDeleteUser() {
        Platform.runLater(()->model.error("Can not delete a user that is currently reserving\n or borrowing an item."));
    }

    @Override
    public void cannotDeleteItem() {
        Platform.runLater(()->model.error("Can not delete an item that is currently reserved\n or borrowed."));

    }

    @Override
    public void itemBorrowed() {
        Platform.runLater(()->model.info("Item has been borrowed."));
    }

    @Override
    public void searchBook(String bookName) {
        socketHandler.searchForBook(bookName);
    }

    @Override
    public void sendInfo(String jsonInstruction){
        socketHandler.sendInfo(jsonInstruction);
    }

    @Override
    public void updateMainTable(ArrayList<Item> items) {
        model.setMainTable(items);
    }

    @Override
    public void UpdateFinesTable(ArrayList<Borrowed> fines) {
        model.setFinesTable(fines);
    }

    @Override
    public void UpdateReservationsTable(ArrayList<Reservation> fromJson) {
        model.setReservedTable(fromJson);
    }

    @Override
    public void updateBorrowedTable(ArrayList<Borrowed> borrowed){
        model.setBorrowedTable(borrowed);
    }
    @Override
    public void updateUsersTable(ArrayList<User> users){
        model.setUsersTable(users);
    }
}
