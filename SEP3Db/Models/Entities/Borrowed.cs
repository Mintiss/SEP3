using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SEP3Db.Models.Entities
{
    public class Borrowed
    {
        [Key]
        public int BorrowedId { get; set; }
        [ForeignKey("User")]
        public string Username { get; set; }
        [ForeignKey("Item")]
        public int ItemId { get; set; }
        public DateTime ReturnDate { get; set; }
        public DateTime BorrowDate { get; set; }

    }
}
