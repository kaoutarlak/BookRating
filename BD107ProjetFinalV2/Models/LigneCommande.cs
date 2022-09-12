using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace BD107ProjetFinalV2.Models
{
    public class LigneCommande
    {
        [Required]
        [Key]

        public int LigneCommandeId { get; set; }
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
