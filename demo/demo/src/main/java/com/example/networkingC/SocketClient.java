package com.example.networkingC;



import com.example.demo.DemoApplication;
import com.example.forSocketsTest.Book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SocketClient implements Client{

    DemoApplication demoApplication;

    ClientSocketHandler socketHandler;

    public SocketClient(DemoApplication model){

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

    public void searchBookResult(List<Book> books) {
        ;
    }

    @Override
    public void searchBook(String bookName) {
        socketHandler.searchForBook(bookName);
    }
}
