package  com.example.networking;



import com.example.Shared.*;
import com.example.networking.model.ServerModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerSocketHandler implements Runnable{
    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;
    private Socket socket;
    private ServerModel model;
    private Gson gson;

    public ServerSocketHandler(Socket socket, ServerModel model) {
        this.socket = socket;
        this.model = model;
        this.gson=new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addListener("LogInSuccess",this::logInTheUser);
        model.addListener("LogInFailed",this::failLogInTheUser);
        model.addListener("UpdateMainTable", this::updateMainTable);
        model.addListener("UpdateBorrowedTable", this::updateBorrowedTable);
        model.addListener("UpdateUsersTable", this::updateUsersTable);
        model.addListener("UpdateFinesTable", this::updateFinesTable);
        model.addListener("UpdateReservationsTable", this::updateReservationsTable);
        model.addListener("NoItemsLeft", this::noItemsLeft);
        model.addListener("ItemBorrowed", this::ItemBorrowed);
        model.addListener("FinePaid", this::FinePaid);

    }



    @Override
    public void run() {
        while (true){

            try {
                Object obj=inFromClient.readObject();

                if (obj instanceof java.lang.String)
                {
                    System.out.println(obj);
                    if (((String)obj).equals("UpdateMainTable"))
                    {
                        model.UpdateMainTable();
                    }
                    else if(((String)obj).equals("UpdateBorrowedTable"))
                    {
                        model.UpdateBorrowedTable();
                    }
                    else if(((String)obj).equals("UpdateUsersTable"))
                    {
                        model.UpdateUsersTable();
                    }
                    else if(((String)obj).equals("UpdateFinesTable"))
                    {
                        model.UpdateFinesTable();
                    }
                    else if(((String)obj).equals("UpdateReservationTable"))
                    {
                        model.UpdateReservationTable();
                    }
                    else {
                        JsonInstruction jsonInstruction = gson.fromJson((java.lang.String) obj, JsonInstruction.class);

                        if (jsonInstruction.getInstruction().equals("LoginInfo")) {
                            System.out.println(jsonInstruction.getInstruction());
                            User user = gson.fromJson(jsonInstruction.getJson(), User.class);
                            model.checkUserInfoOnLogin(user);
                        }
                        if(jsonInstruction.getInstruction().equals("EditItem")){
                            model.editItem(gson.fromJson(jsonInstruction.getJson(), Item.class));
                        }
                        if(jsonInstruction.getInstruction().equals("AddItem")){
                            model.addItem(gson.fromJson(jsonInstruction.getJson(), Item.class));
                        }
                        if(jsonInstruction.getInstruction().equals("DeleteItem")){
                            model.deleteItem(jsonInstruction.getJson());
                        }
                        if(jsonInstruction.getInstruction().equals("DeleteUser")){
                            model.deleteUser(jsonInstruction.getJson());
                        }
                        if(jsonInstruction.getInstruction().equals("ChangePassword")){
                            model.changePassword(gson.fromJson(jsonInstruction.getJson(), User.class));
                        }
                        if(jsonInstruction.getInstruction().equals("BorrowItem")){
                            model.borrowItem(gson.fromJson(jsonInstruction.getJson(), Borrowed.class));
                        }
                        if(jsonInstruction.getInstruction().equals("SearchMainId")){
                            model.searchMainId(jsonInstruction.getJson());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchMainTitle")){
                            model.searchMainTitle(jsonInstruction.getJson());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchMainAuthor")){
                            model.searchMainAuthor(jsonInstruction.getJson());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchLendItem")){
                            model.searchLendItem(jsonInstruction.getJson());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchUserList")){
                            model.searchLendItem(jsonInstruction.getJson());
                        }
                        if(jsonInstruction.getInstruction().equals("ReturnedItem")){
                            model.returnItem(gson.fromJson(jsonInstruction.getJson(), Borrowed.class));
                        }
                        if(jsonInstruction.getInstruction().equals("PayFine")){
                            model.returnItem(gson.fromJson(jsonInstruction.getJson(), Borrowed.class));
                        }
                        if(jsonInstruction.getInstruction().equals("DeleteReservation")){
                            model.deleteReservation(gson.fromJson(jsonInstruction.getJson(), Reservation.class));
                        }
                        if(jsonInstruction.getInstruction().equals("MoveToBorrowed")){
                            model.moveToBorrowed(gson.fromJson(jsonInstruction.getJson(), Reservation.class));
                        }
                        if(jsonInstruction.getInstruction().equals("Months")){
                            model.setBorrowedMonths(jsonInstruction.getJson());
                        }




                    }
                }
            } catch (SocketException e)
            {
                try {
                    System.out.println("Client Disconnected");
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void updateMainTable(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject(gson.toJson(new JsonInstruction(gson.toJson(model.getItems()),"UpdateMainTable")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void updateReservationsTable(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject(gson.toJson(new JsonInstruction(gson.toJson(model.getReservations()),"UpdateReservationsTable")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void updateFinesTable(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject(gson.toJson(new JsonInstruction(gson.toJson(model.getFines()),"UpdateFinesTable")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void updateUsersTable(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject(gson.toJson(new JsonInstruction(gson.toJson(model.getUsers()),"UpdateUsersTable")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private void updateBorrowedTable(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject(gson.toJson(new JsonInstruction(gson.toJson(model.getBorrowed()),"UpdateBorrowedTable")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void logInTheUser(PropertyChangeEvent evt){
        try {
            outToClient.writeObject("Log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void noItemsLeft(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject("NoItemsLeft");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void FinePaid(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject("FinePaid");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ItemBorrowed(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject("ItemBorrowed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void failLogInTheUser(PropertyChangeEvent evt){
        try {
            outToClient.writeObject("LogFail");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
