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
        model.addListener("CannotDeleteUser", this::CannotDeleteUser);
        model.addListener("CannotDeleteItem", this::CannotDeleteItem);



    }



    @Override
    public void run() {
        while (true){

            try {
                Object obj=inFromClient.readObject();

                System.out.println(obj);
                JsonInstruction jsonInstruction = gson.fromJson((java.lang.String) obj, JsonInstruction.class);

                if (jsonInstruction.getJson()==null)
                {
                    System.out.println(obj);
                    if ((jsonInstruction.getInstruction().equals("UpdateMainTable")))
                    {
                        model.UpdateMainTable(jsonInstruction.getUsername());
                    }
                    else if((jsonInstruction.getInstruction().equals("UpdateBorrowedTable")))
                    {
                        model.UpdateBorrowedTable(jsonInstruction.getUsername());
                    }
                    else if((jsonInstruction.getInstruction().equals("UpdateUsersTable")))
                    {
                        model.UpdateUsersTable(jsonInstruction.getUsername());
                    }
                    else if((jsonInstruction.getInstruction().equals("UpdateFinesTable")))
                    {
                        model.UpdateFinesTable(jsonInstruction.getUsername());
                    }
                    else if((jsonInstruction.getInstruction().equals("UpdateReservationTable")))
                    {
                        model.UpdateReservationTable(jsonInstruction.getUsername());
                    }
                }
                    else {

                        if (jsonInstruction.getInstruction().equals("LoginInfo")) {
                            System.out.println(jsonInstruction.getInstruction());
                            User user = gson.fromJson(jsonInstruction.getJson(), User.class);
                            model.checkUserInfoOnLogin(user,jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("EditItem")){
                            model.editItem(gson.fromJson(jsonInstruction.getJson(), Item.class),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("AddItem")){
                            model.addItem(gson.fromJson(jsonInstruction.getJson(), Item.class),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("DeleteItem")){
                            model.deleteItem(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("DeleteUser")){
                            model.deleteUser(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("ChangePassword")){
                            model.changePassword(gson.fromJson(jsonInstruction.getJson(), User.class),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("BorrowItem")){
                            model.borrowItem(gson.fromJson(jsonInstruction.getJson(), Borrowed.class),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchMainId")){
                            model.searchMainId(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchMainTitle")){
                            model.searchMainTitle(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchMainAuthor")){
                            model.searchMainAuthor(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchReservedId")){
                            model.searchReservedId(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchReservedItemId")){
                            model.searchReservedItemId(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchReservedUsername")){
                            model.searchReservedUsername(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchBorrowedId")){
                            model.searchBorrowedId(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchBorrowedItemId")){
                            model.searchBorrowedItemId(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchBorrowedUsername")){
                            model.searchBorrowedUsername(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchFinesId")){
                            model.searchFinesId(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchFinesItemId")){
                            model.searchFinesItemId(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchFinesUsername")){
                            model.searchFinesUsername(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchLendItem")){
                            model.searchLendItem(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("SearchUserList")){
                            model.searchLendItem(jsonInstruction.getJson(),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("ReturnedItem")){
                            model.returnItem(gson.fromJson(jsonInstruction.getJson(), Borrowed.class),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("PayFine")){
                            model.returnItem(gson.fromJson(jsonInstruction.getJson(), Borrowed.class),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("DeleteReservation")){
                            model.deleteReservation(gson.fromJson(jsonInstruction.getJson(), Reservation.class),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("MoveToBorrowed")){
                            model.moveToBorrowed(gson.fromJson(jsonInstruction.getJson(), Reservation.class),jsonInstruction.getUsername());
                        }
                        if(jsonInstruction.getInstruction().equals("Months")){
                            model.setBorrowedMonths(jsonInstruction.getJson());
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
            String user=(String)propertyChangeEvent.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(gson.toJson(model.getItems()),user,"UpdateMainTable")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void updateReservationsTable(PropertyChangeEvent propertyChangeEvent) {
        try {
            String user=(String)propertyChangeEvent.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(gson.toJson(model.getReservations()),user,"UpdateReservationsTable")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void updateFinesTable(PropertyChangeEvent propertyChangeEvent) {
        try {
            String user=(String)propertyChangeEvent.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(gson.toJson(model.getFines()),user,"UpdateFinesTable")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void updateUsersTable(PropertyChangeEvent propertyChangeEvent) {
        try {
            String user=(String)propertyChangeEvent.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(gson.toJson(model.getUsers()),user,"UpdateUsersTable")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private void updateBorrowedTable(PropertyChangeEvent propertyChangeEvent) {
        try {
            String user=(String)propertyChangeEvent.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(gson.toJson(model.getBorrowed()),user,"UpdateBorrowedTable")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void logInTheUser(PropertyChangeEvent evt){
        try {
            String user=(String)evt.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(null,user,"Log")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void noItemsLeft(PropertyChangeEvent propertyChangeEvent) {
        try {
            String user=(String)propertyChangeEvent.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(null,user,"NoItemsLeft")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void CannotDeleteUser(PropertyChangeEvent propertyChangeEvent) {
        try {
            String user=(String)propertyChangeEvent.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(null,user,"CannotDeleteUser")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void CannotDeleteItem(PropertyChangeEvent propertyChangeEvent) {
        try {
            String user=(String)propertyChangeEvent.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(null,user,"CannotDeleteItem")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void FinePaid(PropertyChangeEvent propertyChangeEvent) {
        try {
            String user=(String)propertyChangeEvent.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(null,user,"FinePaid")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ItemBorrowed(PropertyChangeEvent propertyChangeEvent) {
        try {
            String user=(String)propertyChangeEvent.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(null,user,"ItemBorrowed")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void failLogInTheUser(PropertyChangeEvent evt){
        try {
            String user=(String)evt.getNewValue();
            outToClient.writeObject(gson.toJson(new JsonInstruction(null,user,"LogFail")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
