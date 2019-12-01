package networkingC;


import Shared.Item;
import Shared.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ClientSocketHandler implements Runnable {

    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;
    private SocketClient client;

    public ClientSocketHandler(ObjectOutputStream outToServer, ObjectInputStream inFromServer, SocketClient _client) {
        client = _client;
        this.inFromServer = inFromServer;
        this.outToServer = outToServer;
    }


    @Override
    public void run() {
        try {

            while (true) {
                Object obj=inFromServer.readObject();

                if (obj instanceof  String){
                    if ("Log".equals(obj)){
                        client.logIn();
                    }
                    if ("LogFail".equals(obj)){
                        client.logInFailed();
                    }
                }


                if (obj instanceof List) {
                    List<Item> ilist = (List<Item>) obj;
                    client.searchBookResult(ilist);
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
