package  com.example.networking;



import com.example.Shared.Item;
import com.example.Shared.JsonInstruction;
import com.example.Shared.User;
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
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
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

    }



    @Override
    public void run() {
        while (true){

            try {
                Object obj=inFromClient.readObject();

                if (obj instanceof String)
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
                    else {
                        JsonInstruction jsonInstruction = gson.fromJson((String) obj, JsonInstruction.class);

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


    public void failLogInTheUser(PropertyChangeEvent evt){
        try {
            outToClient.writeObject("LogFail");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
