using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net.Sockets;
using System.Threading;
using System.Threading.Tasks;
using Tier1User.Data;

namespace Tier1User.Networking
{
    public class Service 
    
    {
        HttpClient client;

        public Service()
        {
            client = new HttpClient();

            client.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public HttpClient getClient()
        {
            return this.client;
        }
    }
}
