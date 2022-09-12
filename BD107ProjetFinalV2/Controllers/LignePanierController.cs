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
    public class LignePanierController : Controller
    {
        private readonly ProjetFinalEquip1DbContext _context;

        public LignePanierController(ProjetFinalEquip1DbContext context)
        {
            _context = context;
        }

        // GET: LignePanier
        public async Task<IActionResult> Index()
        {
            var projetFinalEquip1DbContext = _context.LignePaniers.Include(l => l.Produit);
            return View(await projetFinalEquip1DbContext.ToListAsync());
        }

        // GET: LignePanier/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var lignePanier = await _context.LignePaniers
                .Include(l => l.Produit)
                .FirstOrDefaultAsync(m => m.LignePanierId == id);
            if (lignePanier == null)
            {
                return NotFound();
            }

            return View(lignePanier);
        }

        // GET: LignePanier/Create
        public IActionResult Create()
        {
            ViewData["ProduitId"] = new SelectList(_context.Produits, "ProduitId", "ProduitId");
            return View();
        }

        // POST: LignePanier/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("LignePanierId,ProduitId,Quantite,ClientId")] LignePanier lignePanier)
        {
            if (ModelState.IsValid)
            {
                _context.Add(lignePanier);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["ProduitId"] = new SelectList(_context.Produits, "ProduitId", "ProduitId", lignePanier.ProduitId);
            return View(lignePanier);
        }

        // GET: LignePanier/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var lignePanier = await _context.LignePaniers.FindAsync(id);
            if (lignePanier == null)
            {
                return NotFound();
            }
            ViewData["ProduitId"] = new SelectList(_context.Produits, "ProduitId", "ProduitId", lignePanier.ProduitId);
            return View(lignePanier);
        }

        // POST: LignePanier/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("LignePanierId,ProduitId,Quantite,ClientId")] LignePanier lignePanier)
        {
            if (id != lignePanier.LignePanierId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(lignePanier);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!LignePanierExists(lignePanier.LignePanierId))
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
            ViewData["ProduitId"] = new SelectList(_context.Produits, "ProduitId", "ProduitId", lignePanier.ProduitId);
            return View(lignePanier);
        }

        // GET: LignePanier/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var lignePanier = await _context.LignePaniers
                .Include(l => l.Produit)
                .FirstOrDefaultAsync(m => m.LignePanierId == id);
            if (lignePanier == null)
            {
                return NotFound();
            }

            return View(lignePanier);
        }

        // POST: LignePanier/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var lignePanier = await _context.LignePaniers.FindAsync(id);
            _context.LignePaniers.Remove(lignePanier);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool LignePanierExists(int id)
        {
            return _context.LignePaniers.Any(e => e.LignePanierId == id);
        }
    }
}
