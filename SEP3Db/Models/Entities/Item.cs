using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SEP3Db.Models.Entities
{
    public class Item
    {
        [Key]
        public int ItemId { get; set; }
        public string Author { get; set; }
        public string Title { get; set; }
        public string Type { get; set; }
        public int Quantity { get; set; }

    }
}
