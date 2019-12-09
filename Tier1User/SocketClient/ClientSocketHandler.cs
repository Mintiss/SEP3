using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Threading.Tasks;
using BlazorApp1.Data;

namespace BlazorApp1.SocketClient
{
    public class ClientSocketHandler 
    {

        SocketClientT socketClient;
        Model model;


        Socket clientSocket;

        public ClientSocketHandler(SocketClientT socketClient,Model model)
        {
            this.socketClient = socketClient;
            this.model = model;
            clientSocket = socketClient.getClientSocket();
        }

        public void run()
        {
            while (true)
            {
                byte[] rcvLenBytes = new byte[4];
                clientSocket.Receive(rcvLenBytes);
                int rcvLen = System.BitConverter.ToInt32(rcvLenBytes, 0);
                byte[] rcvBytes = new byte[rcvLen];
                clientSocket.Receive(rcvBytes);
                String rcv = System.Text.Encoding.ASCII.GetString(rcvBytes);

                System.Diagnostics.Debug.WriteLine("Client received: " + rcv);
            }
        }

        public void getItem()
        {
            String toSends = "Ask for Item";

            var toSend = Newtonsoft.Json.JsonConvert.SerializeObject(toSends);

            int toSendLen = System.Text.Encoding.ASCII.GetByteCount(toSend);
            byte[] toSendBytes = System.Text.Encoding.ASCII.GetBytes(toSend);
            byte[] toSendLenBytes = System.BitConverter.GetBytes(toSendLen);
            clientSocket.Send(toSendLenBytes);
            clientSocket.Send(toSendBytes);
        }
    }
}
