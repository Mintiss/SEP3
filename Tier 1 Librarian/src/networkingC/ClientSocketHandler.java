package networkingC;


import Shared.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientSocketHandler implements Runnable {

    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;
    private SocketClient client;
    private Gson gson;

    public ClientSocketHandler(ObjectOutputStream outToServer, ObjectInputStream inFromServer, SocketClient _client) {
        client = _client;
        this.inFromServer = inFromServer;
        this.outToServer = outToServer;
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
    }


    @Override
    public void run() {
        try {
            while (true) {
                Object obj=inFromServer.readObject();
                JsonInstruction jsonInstruction = gson.fromJson((String) obj, JsonInstruction.class);

                if (jsonInstruction.getJson()==null){
                        if (jsonInstruction.getUsername().equals(client.getLoggedIn()))
                        {
                            if (jsonInstruction.getInstruction().equals("Log")){
                                client.logIn();
                            }
                            else if (jsonInstruction.getInstruction().equals("LogFail"))
                                client.logInFailed();
                            else if(jsonInstruction.getInstruction().equals("NoItemsLeft"))
                                client.noItemsLeft();
                            else if(jsonInstruction.getInstruction().equals("ItemBorrowed"))
                                client.itemBorrowed();
                            else if(jsonInstruction.getInstruction().equals("CannotDeleteUser"))
                                client.cannotDeleteUser();
                            else if(jsonInstruction.getInstruction().equals("CannotDeleteItem"))
                                client.cannotDeleteItem();
                        }

                    }
                    else {

                        if (jsonInstruction.getUsername().equals(client.getLoggedIn())){

                            if (jsonInstruction.getInstruction().equals("UpdateMainTable")) {
                            System.out.println(jsonInstruction);
                            client.updateMainTable(gson.fromJson(jsonInstruction.getJson(),new TypeToken<ArrayList<Item>>(){}.getType()));
                        }
                        if (jsonInstruction.getInstruction().equals("UpdateBorrowedTable")) {
                            System.out.println(jsonInstruction);
                            client.updateBorrowedTable(gson.fromJson(jsonInstruction.getJson(),new TypeToken<ArrayList<Borrowed>>(){}.getType()));
                        }
                        if (jsonInstruction.getInstruction().equals("UpdateUsersTable")) {
                            System.out.println(jsonInstruction);
                            client.updateUsersTable(gson.fromJson(jsonInstruction.getJson(),new TypeToken<ArrayList<User>>(){}.getType()));
                        }
                        if (jsonInstruction.getInstruction().equals("UpdateFinesTable")) {
                            System.out.println(jsonInstruction);
                            client.UpdateFinesTable(gson.fromJson(jsonInstruction.getJson(),new TypeToken<ArrayList<Borrowed>>(){}.getType()));
                        }
                        if (jsonInstruction.getInstruction().equals("UpdateReservationsTable")) {
                            System.out.println(jsonInstruction);
                            client.UpdateReservationsTable(gson.fromJson(jsonInstruction.getJson(),new TypeToken<ArrayList<Reservation>>(){}.getType()));
                        }
                    } }
                }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchForBook(String search){
        try {
            outToServer.writeObject(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendInfo(String jsonInstruction){
        try {
            outToServer.writeObject(jsonInstruction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
