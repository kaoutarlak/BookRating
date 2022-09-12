using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using BD107ProjetFinalV2.Models;

namespace BD107ProjetFinalV2.Controllers
{
    public class EtatCommandeController : Controller
    {
        private readonly ProjetFinalEquip1DbContext _context;

        public EtatCommandeController(ProjetFinalEquip1DbContext context)
        {
            _context = context;
        }

        // GET: EtatCommande
        public async Task<IActionResult> Index()
        {
            return View(await _context.EtatCommandes.ToListAsync());
        }

        // GET: EtatCommande/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var etatCommande = await _context.EtatCommandes
                .FirstOrDefaultAsync(m => m.EtatCommandeId == id);
            if (etatCommande == null)
            {
                return NotFound();
            }

            return View(etatCommande);
        }

        // GET: EtatCommande/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: EtatCommande/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("EtatCommandeId,Etat")] EtatCommande etatCommande)
        {
            if (ModelState.IsValid)
            {
                _context.Add(etatCommande);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(etatCommande);
        }

        // GET: EtatCommande/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var etatCommande = await _context.EtatCommandes.FindAsync(id);
            if (etatCommande == null)
            {
                return NotFound();
            }
            return View(etatCommande);
        }

        // POST: EtatCommande/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("EtatCommandeId,Etat")] EtatCommande etatCommande)
        {
            if (id != etatCommande.EtatCommandeId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(etatCommande);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!EtatCommandeExists(etatCommande.EtatCommandeId))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(etatCommande);
        }

        // GET: EtatCommande/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var etatCommande = await _context.EtatCommandes
                .FirstOrDefaultAsync(m => m.EtatCommandeId == id);
            if (etatCommande == null)
            {
                return NotFound();
            }

            return View(etatCommande);
        }

        // POST: EtatCommande/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var etatCommande = await _context.EtatCommandes.FindAsync(id);
            _context.EtatCommandes.Remove(etatCommande);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool EtatCommandeExists(int id)
        {
            return _context.EtatCommandes.Any(e => e.EtatCommandeId == id);
        }
    }
}
