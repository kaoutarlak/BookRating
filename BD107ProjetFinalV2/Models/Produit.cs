﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace BD107ProjetFinalV2.Models
{
    public class Produit
    {
        [Key]
        public int ProduitId { get; set; }
        public string Titre { get; set; }
        public string Auteur { get; set; }
        public double Prix { get; set; }
        public string Img { get; set; }
        public string Description { get; set; }
        public DateTime DateParution { get; set; }
        public int Quantite { get; set; }

        public int CategorieId { get; set; }
        public int LigneCommandeId { get; set; }
        public int LignePanierId { get; set; }



        public virtual Categorie Categorie { get; set; }
        public virtual ICollection<LigneCommande> LigneCommandes { get; set; }
        public virtual ICollection<LignePanier> LignePaniers { get; set; }

    }
}
