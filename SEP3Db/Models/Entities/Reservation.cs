using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SEP3Db.Models.Entities
{
    public class Reservation
    {
        [Key]
        public int ReservationId { get; set; }
        [ForeignKey("User")]
        public string Username { get; set; }
        [ForeignKey("Item")]
        public int ItemId { get; set; }
        public DateTime ReservedAt { get; set; }
        public DateTime ReservationExpirationDate { get; set; }

             

    }
}
