using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BD107ProjetFinal.Models
{
    public class ProjetFinalEquip1DbContext : IdentityDbContext
    {
        public ProjetFinalEquip1DbContext(DbContextOptions<ProjetFinalEquip1DbContext> option) : base()
        {
        }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {

            optionsBuilder.UseSqlServer("Server =(localdb)\\mssqllocaldb; Database=BDWPojetFinalEquipe1;Trusted_Connection=True;");
            //optionsBuilder.UseSqlServer("Server =tcp:424sql.cgodin.qc.ca,5433;Database=BDWPojetFinalEquipe1;Trusted_Connection=True;");
            //optionsBuilder.UseSqlServer("Server=tcp:424sql.cgodin.qc.ca,5433;Database=BDWPojetFinalEquipe1;User Id=E22equipe1;Password=Secret16113");
            //"Equipe1Connection": "Server=tcp:424sql.cgodin.qc.ca,5433;Database=BDWPojetFinalEquipe1;User Id=E22equipe1;Password=Secret16113"
            //optionsBuilder.UseSqlServer("Data Source=tcp:424sql.cgodin.qc.ca,5433;Initial Catalog=BDWPojetFinalEquipe1; User ID=E22equipe1; Password =Secret16113");
        }

        public DbSet<Categorie> Categories { get; set; }
        public DbSet<Commande> Commandes { get; set; }
        public DbSet<EtatCommande> EtatCommandes { get; set; }
        public DbSet<LigneCommande> LigneCommandes { get; set; }
        public DbSet<LignePanier> LignePaniers { get; set; }
        public DbSet<Produit> Produits { get; set; }
    }
}
