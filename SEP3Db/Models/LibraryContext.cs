using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Npgsql.EntityFrameworkCore.PostgreSQL;
using Npgsql;
using Npgsql.EntityFrameworkCore;
using SEP3Db.Models.Entities.Reservation;
using SEP3Db.Models.Entities.Item;
using SEP3Db.Models.Entities.Borrowed;
using SEP3Db.Models.Entities.User;



namespace SEP3Db.Models
{
    public class LibraryContext : DbContext
    {
        public LibraryContext(DbContextOptions<LibraryContext> options)
            : base(options)
        {
        }

        public DbSet<Item> Items { get; set; }
        public DbSet<Reservation> Reservations { get; set; }
        public DbSet<Borrowed> Borrowed { get; set; }
        public DbSet<User> Users { get; set; }

    }
}
