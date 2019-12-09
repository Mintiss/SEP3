using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using BlazorApp1.SocketClient;

namespace BlazorApp1.Data
{
    public class Model
    {
        SocketClientT socketC;
        ClientSocketHandler socketHandler;

        public Model()
        {
            socketC = new SocketClientT();
            socketHandler = new ClientSocketHandler(socketC, this);
        }    
    
        public void getItem()
        {
            socketHandler.getItem();
        }

    }
}
