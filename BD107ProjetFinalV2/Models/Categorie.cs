using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace BD107ProjetFinalV2.Models
{
    public class Categorie
    {
        [Key]
        [Required]
        public int CategorieId { get; set; }

        [Required]
        public string Nom { get; set; }

        public virtual ICollection<Produit> Produits { get; set; }
    }
}
