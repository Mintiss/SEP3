using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Net.Sockets;
using System.Net;
using BlazorApp1.Data;
using System.Threading;

namespace BlazorApp1.SocketClient
{
    public class SocketClientT
    {
        ClientSocketHandler clientSocketHandler;
        Model model;
        Socket clientSocket;

        public SocketClientT()
        {
            IPEndPoint serverAddress = new IPEndPoint(IPAddress.Parse("127.0.0.1"), 6969);

            clientSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            clientSocket.Connect(serverAddress);


            clientSocketHandler = new ClientSocketHandler(this,model);

            Thread th = new Thread(new ThreadStart(clientSocketHandler.run));
            th.Start();
        }

        public Socket getClientSocket()
        {
            return clientSocket;
        }
    
    }
}
