using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Tier1User.Shared
{
    public class User
    {
        public String Username;
        public String Password;
        public int type;

        public User(String username,String password)
        {
            this.Username = username;
            this.Password = password;
            this.type = -1;
        }

    }
}
