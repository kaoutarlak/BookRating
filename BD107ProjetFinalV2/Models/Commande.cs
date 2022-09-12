using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace BD107ProjetFinalV2.Models
{
    public class Commande
    {
        [Required]
        [Key]
        public int CommandeId { get; set; }
        [Required]
        public DateTime DateParution { get; set; }
        [Required]
        public int ClientId { get; set; }
        [Required]
        public int EtatCommandeId { get; set; }
        public int LigneCommandeId { get; set; }
        //[Required]
        //public int ClientId { get; set; }
        //public virtual Client Client { get; set; }

        public virtual EtatCommande EtatCommande { get; set; }
        public virtual ICollection<LigneCommande> LigneCommandes { get; set; }
    }
}
