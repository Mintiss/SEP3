package com.example.networkingCS;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerHandlerCS implements Runnable{

    private Socket socket;
    private ServerSocket serverSocket;
    private InputStream is;
    private OutputStream os;


    public SocketServerHandlerCS(Socket socket, ServerSocket serverSocket) {
        this.socket=socket;
        this.serverSocket=serverSocket;
        try {
            is=socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os=socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            byte[] lenBytes = new byte[4];
            is.read(lenBytes, 0, 4);
            int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
                    ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
            byte[] receivedBytes = new byte[len];
            is.read(receivedBytes, 0, len);
            String received = new String(receivedBytes, 0, len);

            System.out.println("Server received: " + received);

            // Sending
            String toSend = "Echo: " + received;
            byte[] toSendBytes = toSend.getBytes();
            int toSendLen = toSendBytes.length;
            byte[] toSendLenBytes = new byte[4];
            toSendLenBytes[0] = (byte) (toSendLen & 0xff);
            toSendLenBytes[1] = (byte) ((toSendLen >> 8) & 0xff);
            toSendLenBytes[2] = (byte) ((toSendLen >> 16) & 0xff);
            toSendLenBytes[3] = (byte) ((toSendLen >> 24) & 0xff);
            os.write(toSendLenBytes);
            os.write(toSendBytes);

            //socket.close();
            //serverSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
