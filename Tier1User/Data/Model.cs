using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;



namespace Tier1User.Data
{
    public class Model
    {
        private static readonly string[] Authors = new[]
       {
            "Shakespeare", "Donne", "Tolstoy", "Keats", "Blake", "Howard", "Aristotle", "Hemingway", "Herzl", "Joyce"
        };

        private static readonly string[] Titles = new[]
        {
            "Shakespeare", "Donne", "Tolstoy", "Keats", "Blake", "Howard", "Aristotle", "Hemingway", "Herzl", "Joyce"
        };
        private static readonly string[] Types = new[]
        {
            "Book", "Article", "Magazine", "Journal", "CD", "Game"
        };
        private static readonly string[] InStocks = new[]
        {
            "In Stock", "Not in stock"
        };

        public string email = "123";
        public string password = "123";
        

        public bool LoggedIn = false;

        public async Task<bool> CheckUser(string emailInput, string passwordInput)
        {

            if (Equals(emailInput, email))
            {
                if (Equals(passwordInput, password))
                {
                    LoggedIn = true;
                    return true;
                }
                else return false;
            }
            else return false;

        }

        public async Task<bool> RegisterUser(string emailInput, string passwordInput)
        {
            return true;
        }
        public Task<Item[]> GetItemAsync(DateTime startDate)
        {
            var rng = new Random();
            return Task.FromResult(Enumerable.Range(1, 5).Select(index => new Item
            {
                Date = startDate.AddDays(index),
                Title = Titles[rng.Next(Titles.Length)],
                Author = Authors[rng.Next(Authors.Length)],
                Type = Types[rng.Next(Types.Length)],
                InStock = InStocks[rng.Next(InStocks.Length)],
            }).ToArray()) ;
        }

        public async Task<bool> ReserveItemAsync()
        {
            //should make InStock value in stock 
            return true;
        }
    }
}
