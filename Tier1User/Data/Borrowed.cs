using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Tier1User.Data
{
    public class Borrowed
    {
        public int BorrowedId { get; set; }

        public string Username { get; set; }

        public int ItemId { get; set; }

        public string ReturnDate { get; set; }

        public string BorrowDate { get; set; }

        public bool IsReturned { get; set; }

        public long Fine { get; set; }
    }
}
