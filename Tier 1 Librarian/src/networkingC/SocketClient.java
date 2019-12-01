package networkingC;



import Model.Model;
import Shared.Item;
import Shared.User;
import Model.IModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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

    public void logIn(){
        model.logInConfirmed();
    }

    public void logInFailed(){
        model.logInFailed();
    }

    @Override
    public void searchBook(String bookName) {
        socketHandler.searchForBook(bookName);
    }

    @Override
    public void sendInfo(String jsonInstruction){
        socketHandler.sendInfo(jsonInstruction);
    }
}
