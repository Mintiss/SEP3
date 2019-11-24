using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SEP3Db.Models.Entities.Borrowed
{
    public class Borrowed
    {
        [Key]
        public int BorrowedId { get; set; }
        public int UserId { get; set; }
        public int ItemId { get; set; }
        public DateTime ReturnDate { get; set; }
        public DateTime BorrowDate { get; set; }

    }
}
