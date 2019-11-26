package com.example.networkingC;


import com.example.forSocketsTest.Book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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

                if (obj instanceof List) {
                    List<Book> blist = (List<Book>) obj;
                    client.searchBookResult(blist);
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
}
