package com.example.networking;



import com.example.forSocketsTest.Book;
import com.example.networking.model.ServerModel;

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

    public ServerSocketHandler(Socket socket, ServerModel model) {
        this.socket = socket;
        this.model = model;
        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addListener("SearchBook",this::sendBooksResultToUser);

    }

    @Override
    public void run() {
        while (true){

            try {
                Object obj=inFromClient.readObject();


                if (obj instanceof String)
                {
                    String bookForSearch=(String) obj;
                    model.searchForBook(bookForSearch);
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
}
