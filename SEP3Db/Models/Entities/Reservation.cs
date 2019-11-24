using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SEP3Db.Models.Entities.Reservation
{
    public class Reservation
    {
        [Key]
        public int ReservationId { get; set; }
        public int PersonId { get; set; }
        public int ItemId { get; set; }
        public DateTime ReservedAt { get; set; }
        public DateTime ReservationExpirationDate { get; set; }

             

    }
}
