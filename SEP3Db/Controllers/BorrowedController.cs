using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SEP3Db.Models;
using SEP3Db.Models.Entities;

namespace SEP3Db.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class BorrowedController : ControllerBase
    {
        private readonly LibraryContext _context;

        public BorrowedController(LibraryContext context)
        {
            _context = context;
        }

        // GET: api/Borrowed
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Borrowed>>> GetBorrowed()
        {
            return await _context.Borrowed.ToListAsync();
        }

        // GET: api/Borrowed/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Borrowed>> GetBorrowed(int id)
        {
            var borrowed = await _context.Borrowed.FindAsync(id);

            if (borrowed == null)
            {
                return NotFound();
            }

            return borrowed;
        }


        [HttpGet("User/{username}")]
        public async Task<List<Borrowed>> GetBorrowedBeforeDate(string username)
        {
            var borrowed = _context.Borrowed.Where(Borrowed => Borrowed.Username.Equals(username) && Borrowed.IsReturned==false && Borrowed.ReturnDate>=DateTime.Now).ToList();
            
            if (borrowed == null)
            {
                return null;
            }

           
            return borrowed;
        }


        [HttpGet("User/Fines/{username}")]
        public async Task<List<Borrowed>> GetFines(string username)
        {
            var borrowed = _context.Borrowed.Where(Borrowed => Borrowed.Username.Equals(username) && Borrowed.IsReturned == false && Borrowed.ReturnDate < DateTime.Now).ToList();

            if (borrowed == null)
            {
                return null;
            }


            return borrowed;
        }


        // PUT: api/Borrowed/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for
        // more details see https://aka.ms/RazorPagesCRUD.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutBorrowed(int id, Borrowed borrowed)
        {
            if (id != borrowed.BorrowedId)
            {
                return BadRequest();
            }

            _context.Entry(borrowed).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!BorrowedExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Borrowed
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for
        // more details see https://aka.ms/RazorPagesCRUD.
        [HttpPost]
        public async Task<ActionResult<Borrowed>> PostBorrowed(Borrowed borrowed)
        {
            _context.Borrowed.Add(borrowed);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetBorrowed", new { id = borrowed.BorrowedId }, borrowed);
        }

        // DELETE: api/Borrowed/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Borrowed>> DeleteBorrowed(int id)
        {
            var borrowed = await _context.Borrowed.FindAsync(id);
            if (borrowed == null)
            {
                return NotFound();
            }

            _context.Borrowed.Remove(borrowed);
            await _context.SaveChangesAsync();

            return borrowed;
        }

        private bool BorrowedExists(int id)
        {
            return _context.Borrowed.Any(e => e.BorrowedId == id);
        }
    }
}
