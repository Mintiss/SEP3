package  com.example.networking;



import com.example.Shared.JsonInstruction;
import com.example.Shared.User;
import com.example.forSocketsTest.Book;
import com.example.networking.model.ServerModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ServerSocketHandler implements Runnable{
    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;
    private Socket socket;
    private ServerModel model;
    private Gson gson;

    public ServerSocketHandler(Socket socket, ServerModel model) {
        this.socket = socket;
        this.model = model;
        this.gson=new Gson();
        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addListener("SearchBook",this::sendBooksResultToUser);
        model.addListener("LogInSuccess",this::logInTheUser);
        model.addListener("LogInFailed",this::failLogInTheUser);

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
                    else {
                        JsonInstruction jsonInstruction = gson.fromJson((String) obj, JsonInstruction.class);

                        if (jsonInstruction.getInstruction().equals("LoginInfo")) {
                            System.out.println(jsonInstruction.getInstruction());
                            User user = gson.fromJson(jsonInstruction.getJson(), User.class);
                            model.checkUserInfoOnLogin(user);
                        }
                    }
                }




            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendBooksResultToUser(PropertyChangeEvent evt){
        List<Book> books= (List<Book>) evt.getNewValue();
        try {
            outToClient.writeObject(books);
        } catch (IOException e) {
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
