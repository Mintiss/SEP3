using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SEP3Db.Models.Entities
{
    public class Reservation
    {
        [Key]
        public int ReservationId { get; set; }
        public User User { get; set; }
        public Item Item { get; set; }
        public DateTime ReservedAt { get; set; }
        public DateTime ReservationExpirationDate { get; set; }

             

    }
}
