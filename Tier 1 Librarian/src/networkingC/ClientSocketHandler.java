package networkingC;


import Shared.Borrowed;
import Shared.Item;
import Shared.JsonInstruction;
import Shared.User;
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

                if (obj instanceof String){
                    if ("Log".equals(obj)){
                        client.logIn();
                    }
                    else if ("LogFail".equals(obj)){
                        client.logInFailed();
                    }
                    else if("NoItemsLeft".equals(obj))
                        client.noItemsLeft();
                    else if("ItemBorrowed".equals(obj))
                        client.itemBorrowed();
                    else {
                        JsonInstruction jsonInstruction = gson.fromJson((String) obj, JsonInstruction.class);

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
                    }
                }
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
