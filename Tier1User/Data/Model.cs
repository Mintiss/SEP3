using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using Tier1User.Networking;
using Tier1User.Shared;

namespace Tier1User.Data
{
    public class Model
    {
        public Service service;
     

        public Model()
        {
            service = new Service();
        }

        public string email = "123";
        public string password = "123";
        public bool isLoggedIn = false;

        public bool LogInFalse = false;
        public bool LogInConfirmed = false;

        public async Task<bool> checkUser(string emailInput, string passwordInput)
        {
            /*Debug.WriteLine(emailInput, passwordInput);

            User u = new User(emailInput, passwordInput);

            String jsonuser = Newtonsoft.Json.JsonConvert.SerializeObject(u);

            Debug.WriteLine(jsonuser);

            JsonInstruction js = new JsonInstruction(jsonuser, "LoginInfo");

            String jsonall = Newtonsoft.Json.JsonConvert.SerializeObject(js);

            Debug.WriteLine(jsonall);
*/
            
            var gotFromServer = await service.getClient().GetStringAsync("http://localhost:8543/Users/"+emailInput).ConfigureAwait(false);

            var jsonall = Newtonsoft.Json.JsonConvert.DeserializeObject(gotFromServer);

            if (jsonall != null)
            {
                return true;
            }
            return false;
        }

        public void logFail()
        {
            this.LogInFalse = false;
        }

        public void LogIn()
        {
            this.LogInConfirmed = true;
        }

        public async Task<bool> checkUserf(string emailInput, string passwordInput)
        {

            if (Equals(emailInput, email))
            {
                if (Equals(passwordInput, password))
                {
                    isLoggedIn = true;
                    return true;
                }
                else return false;
            }
            else return false;

        }

        public async Task<bool> registerUser(string emailInput, string passwordInput)
        {
            return true;
        }

    }
}
