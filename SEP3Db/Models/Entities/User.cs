using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SEP3Db.Models.Entities
{
    public class User
    {
        [Key]
        public string Username { get; set; }
        public string Password { get; set; }
        public int Type { get; set; }
    }
}
