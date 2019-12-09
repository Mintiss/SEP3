package com.example.networkingCS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerForCS {

    public static void main(String[] args) throws IOException {
        SocketServerForCS socketServerForCS=new SocketServerForCS();
        socketServerForCS.runServer();
    }

    public void runServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6969, 10);
        while(true) {
            Socket socket = serverSocket.accept();
            SocketServerHandlerCS socketServerHandlerCS=new SocketServerHandlerCS(socket,serverSocket);
            Thread t=new Thread(socketServerHandlerCS);
            t.start();
        }
    }
}