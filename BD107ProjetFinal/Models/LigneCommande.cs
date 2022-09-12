using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace BD107ProjetFinal.Models
{
    public class LigneCommande
    {
        [Required]
        public int Id { get; set; }
        [Required]
        public int Quantite { get; set; }
        [Required]
        public double Prix { get; set; }
        public int ProduitId { get; set; }
        public int CommandeId { get; set; }

        public virtual Commande Commande { get; set; }
        public virtual Produit Produit { get; set; }
    }
}
