using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Tier1User.Data
{
    public class Item
    {
        public long Id { get; set; }

        public DateTime Date { get; set; }

        public string Title { get; set; }

        public string Type { get; set; }

        public string Author { get; set; }

        public string InStock { get; set; }

        public string Location { get; set; }
    }
}
