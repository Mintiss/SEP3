package com.example.networking;

import com.example.networking.model.ServerModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RunSocketServer implements CommandLineRunner {

    public static void main(String[] args) {

        ServerModel m = new ServerModel();
        SocketServer server = new SocketServer(m);
        try {
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        main(args);
    }
}
