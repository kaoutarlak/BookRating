<%--<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BookRating</title>
    <link rel="stylesheet" href="../base.css">
    <link rel="stylesheet" href="../home.css">
    <link rel="stylesheet" href="../base.css">
    <link rel="stylesheet" href="../listLivres.css">

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA_mr8xeV1ot3zDKK5uCp7622y4U7SkMcQ"></script>
</head>
<body>
<main class="basePage">
    <div class="containerHome" id="containerHome">
        <header class="header">

            <div class="numTelephone">
                <img src="icon/telephone.png" id="ico-telephone" width="22px">
                <span>Service client : +1 (514) 000-0000</span>
            </div>
            <div class="userWeb">
                <a href=""><img src="icon/icons8-user-64.png" id="ico-userWeb" width="28px"></a>

            </div>

            <div class="userMobile">
                <a href=""><img src="icon/icons8-user-64.png" id="ico-userMobile" width="28px"></a>
            </div>
            <div class="logoMobile">
                <a href="#" onclick="affichageAccueilMobile();">
                    <center><img src="img/Logo/logoMobile.png" id="ico-logoMobile" width="59"></center>
                </a>
            </div>
            <div class="panierMobile">
                <a href="#"><img src="icon/panier.png" id="ico-panierMobile" width="28px"
                                 onclick="chargerPanierZone();"></a>
                <span id="nbProduitPanier" class="badge rounded-pill badge-notification bg-danger "></span>
            </div>
        </header>
        <nav class="navbarZone">
            <div id="navWeb">
                <div class="logo">
                    <a href="#" onclick="affichageAccueilWeb();"><img src="img/Logo/logoFinal.png"
                                                                      id="logoHomeClick"></a>
                </div>
                <div class="menuWeb">
                    <nav class="navbar navbar-expand-sm  navbar-dark">
                        <div class="container-fluid">
                            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false"
                                    aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarScroll">
                                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll">

                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop"
                                           data-toggle="dropdown">
                                            <img src="icon/icons8-livre-50.png" width="26px"> Livres
                                        </a>
                                        <div class="dropdown-menu">
                                            <a class="dropdown-item" href="#" onclick="chargerLivresInfoZone(1,10);">Informatique</a>
                                            <a class="dropdown-item" href="#"
                                               onclick="chargerLivresLitteratureZone(21,30);">Littérature</a>
                                            <a class="dropdown-item" href="#"
                                               onclick="affichageZoneEncCours();">Géographie</a>
                                        </div>
                                    </li>

                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop2"
                                           data-toggle="dropdown">
                                            <img src="icon/icons8-film-50.png" width="26px"> Films
                                        </a>
                                        <div class="dropdown-menu">
                                            <a class="dropdown-item" href="#" onclick="affichageZoneEncCours();">Sciences
                                                Fictions</a>
                                            <a class="dropdown-item" href="#"
                                               onclick="affichageZoneEncCours();">Comédie</a>
                                            <a class="dropdown-item" href="#" onclick="affichageZoneEncCours();">Drame
                                                et
                                                action</a>
                                        </div>
                                    </li>

                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop3"
                                           data-toggle="dropdown">
                                            <img src="icon/icons8-nintendo-switch-pro-controller-50.png" width="26px">
                                            Jeux
                                        </a>
                                        <div class="dropdown-menu">
                                            <a class="dropdown-item" href="#"
                                               onclick="affichageZoneEncCours();">Playstation</a>
                                            <a class="dropdown-item" href="#"
                                               onclick="affichageZoneEncCours();">Xbox</a>
                                            <a class="dropdown-item" href="#" onclick="affichageZoneEncCours();">Nintendo
                                                Wii U</a>
                                        </div>
                                    </li>

                                    <li class="nav-item">
                                        <a class="nav-link" href="#" onclick="chargerContactZone();">Contact</a>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </nav>

                </div>
                <div class="panierZone text-center">
                    <a href="#"><img src="icon/panier.png" class="img-fluid " onclick="chargerPanierZone();"></a>
                    <span id="nbProduitPanier2" class="badge rounded-pill badge-notification bg-danger "></span>
                </div>
            </div>
        </nav>
