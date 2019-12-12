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

        public List<Borrowed> arrayOfBorrwedItems;

        public List<Borrowed> arrayOfFines;

        public List<Reservation> arrayOfReservedItems;

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

                Debug.WriteLine(this.arrayOfItems.ElementAt(0).ItemId);

                this.arrayOfBorrwedItems = GetBorrowedForUserAsync(emailInput).Result;

                Debug.WriteLine(this.arrayOfBorrwedItems.ElementAt(0).ItemId);

/*                this.arrayOfReservedItems = GetReservedForUserAsync(emailInput).Result;

                Debug.WriteLine(this.arrayOfReservedItems.ElementAt(0).User);
*/
                this.arrayOfFines = GetFinesForUserAsync(emailInput).Result;

                Debug.WriteLine(this.arrayOfFines.ElementAt(0).ReturnDate);

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

        //RETURNS ALL BORROWED NOT RETURNED BUT BEFORE DEADLINE
        public async Task<List<Borrowed>> GetBorrowedForUserAsync(string email)
        {
            var gotFromServer = await service.getClient().GetStringAsync("http://localhost:8543/Borrowed/"+email);

            Newtonsoft.Json.Linq.JArray array = (Newtonsoft.Json.Linq.JArray)Newtonsoft.Json.JsonConvert.DeserializeObject(gotFromServer);

            List<Borrowed> BorrowedByArray = array.ToObject<List<Borrowed>>();

            return BorrowedByArray;
        }

        //RETURNS ALL BORROWED AFTER DEADLINE
        public async Task<List<Borrowed>> GetFinesForUserAsync(string email)
        {
            var gotFromServer = await service.getClient().GetStringAsync("http://localhost:8543/Borrowed/Fines/" + email);

            Newtonsoft.Json.Linq.JArray array = (Newtonsoft.Json.Linq.JArray)Newtonsoft.Json.JsonConvert.DeserializeObject(gotFromServer);

            List<Borrowed> FinesArray = array.ToObject<List<Borrowed>>();

            return FinesArray;
        }

        public async Task<List<Reservation>> GetReservedForUserAsync(string email)
        {
            var gotFromServer = await service.getClient().GetStringAsync("http://localhost:8543/Reservation/"+email);

            Newtonsoft.Json.Linq.JArray array = (Newtonsoft.Json.Linq.JArray)Newtonsoft.Json.JsonConvert.DeserializeObject(gotFromServer);

            List<Reservation> ReservationArray = array.ToObject<List<Reservation>>();

            return ReservationArray;
        }


        public async Task<bool> ReserveItemAsync(int id)
        {

            Debug.WriteLine("resreved"  + id);

            //should make InStock value in stock 
            return true;
        }

        public bool LoggedInCheck()
        {
            return this.LoggedIn;
        }
    }
}
