using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Tier1User.Data
{
    public class Model
    {
        public string email = "123";
        public string password = "123";
        public bool isLoggedIn = false;

        public async Task<bool> checkUser(string emailInput, string passwordInput)
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
