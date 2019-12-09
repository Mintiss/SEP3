using System;
using System.Linq;
using System.Threading.Tasks;
using BlazorApp1.SocketClient;

namespace BlazorApp1.Data
{
    public class ItemService
    {
        Model model;
        
        public ItemService()
        {
            this.model = new Model();
        }

       /* public void GetItemFromServer()
        {
            model.getItem();
        }*/

        private static readonly string[] Authors = new[]
        {
            "Shakespeare", "Donne", "Tolstoy", "Keats", "Blake", "Howard", "Aristotle", "Hemingway", "Herzl", "Joyce"
        };

        private static readonly string[] Titles = new[]
        {
            "Shakespeare", "Donne", "Tolstoy", "Keats", "Blake", "Howard", "Aristotle", "Hemingway", "Herzl", "Joyce"
        };

        public Task<Item[]> GetItemAsync(DateTime startDate)
        {
            var rng = new Random();
            return Task.FromResult(Enumerable.Range(1, 5).Select(index => new Item
            {
                Date = startDate.AddDays(index),
                Title = Titles[rng.Next(Titles.Length)],
                 Author = Authors[rng.Next(Authors.Length)]
            }).ToArray());
        }
    }
}
