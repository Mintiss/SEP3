package com.example.networking;


import com.example.demo.DemoApplication;
import com.example.networking.model.ServerModel;
import org.springframework.boot.SpringApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private ServerModel model;

    public SocketServer(ServerModel model) {
        this.model = model;
    }

    public void runServer() throws IOException {
        ServerSocket welcomeSocket= new ServerSocket(2910);
        System.out.println("Server started...");
        while(true) {
            Socket socket = welcomeSocket.accept();
            System.out.println("Client connected");
            ServerSocketHandler ssh = new ServerSocketHandler(socket, model);
            Thread t = new Thread(ssh);
            t.start();
        }
    }

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
        ServerModel m = new ServerModel();
        SocketServer server = new SocketServer(m);
        try {
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
