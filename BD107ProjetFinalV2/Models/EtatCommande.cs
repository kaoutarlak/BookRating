using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace BD107ProjetFinalV2.Models
{
    public class EtatCommande
    {
        [Required]
        [Key]
        public int EtatCommandeId { get; set; }

        [Required]
        public string Etat { get; set; }

        public virtual ICollection<Commande> Commandes { get; set; }
    }
}
