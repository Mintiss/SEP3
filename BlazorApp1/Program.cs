using System;
using System.Net;
using System.Net.Sockets;
using BlazorApp1.Data;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Hosting;


namespace BlazorApp1
{
    public class Program
    {
        public static void Main(string[] args)
        {
            Model asdf = new Model();

            /*
                        IPEndPoint serverAddress = new IPEndPoint(IPAddress.Parse("127.0.0.1"), 2910);

                        Socket clientSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                        clientSocket.Connect(serverAddress);
            */

            asdf.getItem();
            
            CreateHostBuilder(args).Build().Run();
        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    webBuilder.UseStartup<Startup>();
                });
    }
}
