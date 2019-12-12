using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Tier1User.Networking;
using Tier1User.Shared;



namespace Tier1User.Data
{
    public class Model
    {
        public Service service;

        public bool LoggedIn;

        public List<Item> arrayOfItems;

        public Model()
        {
            service = new Service();
        }

        public async Task<bool> CheckUser(string emailInput, string passwordInput)
        {            
            var gotFromServer = await service.getClient().GetStringAsync("http://localhost:8543/Users/"+emailInput).ConfigureAwait(false);

            var jsonall = Newtonsoft.Json.JsonConvert.DeserializeObject(gotFromServer);

            if (jsonall != null)
            {
                this.arrayOfItems = GetItemsAsync().Result;

                this.LoggedIn = true;
                return true;
            }
            return false;
        }

        public async Task<bool> RegisterUser(string emailInput, string passwordInput)
        {
            Debug.WriteLine(emailInput, passwordInput);

            var jsonall = Newtonsoft.Json.JsonConvert.SerializeObject(emailInput+","+passwordInput);

            var stringContent = new StringContent(jsonall, UnicodeEncoding.UTF8, "application/json");

            Debug.WriteLine(stringContent);

            await service.getClient().PostAsync("http://localhost:8543/Users/",stringContent).ConfigureAwait(false);

            return true;
        
        }

        public async Task<List<Item>> GetItemsAsync()
        {
            var gotFromServer = await service.getClient().GetStringAsync("http://localhost:8543/Items");

            Newtonsoft.Json.Linq.JArray array = (Newtonsoft.Json.Linq.JArray)Newtonsoft.Json.JsonConvert.DeserializeObject(gotFromServer);

            List<Item> itemArray = array.ToObject < List < Item >> ();

            return itemArray;
        }

        public async Task<bool> ReserveItemAsync(long Id)
        {
            
            return true;
        }

        public bool LoggedInCheck()
        {
            return this.LoggedIn;
        }
    }
}
