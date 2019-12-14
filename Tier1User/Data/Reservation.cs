using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Tier1User.Data
{
    public class Reservation
    {
        public int ReservationId { get; set; }

        public string User { get; set; }

        public int ItemId { get; set; }

        public string ReservedAt { get; set; }

        public string ReservationExpirationDate { get; set; }
    }
}
