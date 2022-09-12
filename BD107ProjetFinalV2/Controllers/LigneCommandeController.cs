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
    public class LigneCommandeController : Controller
    {
        private readonly ProjetFinalEquip1DbContext _context;

        public LigneCommandeController(ProjetFinalEquip1DbContext context)
        {
            _context = context;
        }

        // GET: LigneCommande
        public async Task<IActionResult> Index()
        {
            var projetFinalEquip1DbContext = _context.LigneCommandes.Include(l => l.Commande).Include(l => l.Produit);
            return View(await projetFinalEquip1DbContext.ToListAsync());
        }

        // GET: LigneCommande/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var ligneCommande = await _context.LigneCommandes
                .Include(l => l.Commande)
                .Include(l => l.Produit)
                .FirstOrDefaultAsync(m => m.LigneCommandeId == id);
            if (ligneCommande == null)
            {
                return NotFound();
            }

            return View(ligneCommande);
        }

        // GET: LigneCommande/Create
        public IActionResult Create()
        {
            ViewData["CommandeId"] = new SelectList(_context.Commandes, "CommandeId", "CommandeId");
            ViewData["ProduitId"] = new SelectList(_context.Produits, "ProduitId", "ProduitId");
            return View();
        }

        // POST: LigneCommande/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("LigneCommandeId,Quantite,Prix,ProduitId,CommandeId")] LigneCommande ligneCommande)
        {
            if (ModelState.IsValid)
            {
                _context.Add(ligneCommande);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["CommandeId"] = new SelectList(_context.Commandes, "CommandeId", "CommandeId", ligneCommande.CommandeId);
            ViewData["ProduitId"] = new SelectList(_context.Produits, "ProduitId", "ProduitId", ligneCommande.ProduitId);
            return View(ligneCommande);
        }

        // GET: LigneCommande/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var ligneCommande = await _context.LigneCommandes.FindAsync(id);
            if (ligneCommande == null)
            {
                return NotFound();
            }
            ViewData["CommandeId"] = new SelectList(_context.Commandes, "CommandeId", "CommandeId", ligneCommande.CommandeId);
            ViewData["ProduitId"] = new SelectList(_context.Produits, "ProduitId", "ProduitId", ligneCommande.ProduitId);
            return View(ligneCommande);
        }

        // POST: LigneCommande/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("LigneCommandeId,Quantite,Prix,ProduitId,CommandeId")] LigneCommande ligneCommande)
        {
            if (id != ligneCommande.LigneCommandeId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(ligneCommande);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!LigneCommandeExists(ligneCommande.LigneCommandeId))
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
            ViewData["CommandeId"] = new SelectList(_context.Commandes, "CommandeId", "CommandeId", ligneCommande.CommandeId);
            ViewData["ProduitId"] = new SelectList(_context.Produits, "ProduitId", "ProduitId", ligneCommande.ProduitId);
            return View(ligneCommande);
        }

        // GET: LigneCommande/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var ligneCommande = await _context.LigneCommandes
                .Include(l => l.Commande)
                .Include(l => l.Produit)
                .FirstOrDefaultAsync(m => m.LigneCommandeId == id);
            if (ligneCommande == null)
            {
                return NotFound();
            }

            return View(ligneCommande);
        }

        // POST: LigneCommande/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var ligneCommande = await _context.LigneCommandes.FindAsync(id);
            _context.LigneCommandes.Remove(ligneCommande);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool LigneCommandeExists(int id)
        {
            return _context.LigneCommandes.Any(e => e.LigneCommandeId == id);
        }
    }
}
