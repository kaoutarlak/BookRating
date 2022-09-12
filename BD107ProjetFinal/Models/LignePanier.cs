using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace BD107ProjetFinal.Models
{
    public class LignePanier
    {
        [Required]
        [Key]
        public int LignePanierId { get; set; }
        [Required]
        public int ProduitId { get; set; }
        [Required]
        public int Quantite { get; set; }
        [Required]
        public int ClientId { get; set; }

        public virtual Produit Produit { get; set; }
        //public virtual<Client> Client{get;set;}
    }
}
