<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Object login = request.getAttribute("login");

    Object catLivreList = request.getAttribute("catLivreList");

%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Book Rating</title>
    <!-- Meta tags obligatoires pour Bootstrap -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Liens vers les fichiers CSS de Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- Liens vers les fichiers JavaScript de Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNSL+2m"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="<c:url value='/resources/CSS/home.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/CSS/connexion.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/CSS/listLivres.css'/>"/>

</head>
<body>

<!-- Header de la page -->
<header class="fixed-top">
    <div class="container-fluid">
        <div class="row navbarZone ">
            <div class="col-2 logo">

                <a href="${pageContext.request.contextPath}/Home"><img src="${pageContext.request.contextPath}/resources/images/logo.png" class="w-100 h-100"></a>
            </div>
            <div class="col-10 navMain">
                <nav class="navbar navbar-expand-lg navbar-dark ">
                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item dropdown active">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                                    <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-livre-50.png"
                                         width="26px"> &nbsp;Livres

                                    <span class="sr-only">(current)</span>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <c:forEach var="c" items="${catLivreList}">
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/Livres/Liste/${c.titre}">${c.titre}</a>
                                    </c:forEach>

                                </div>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="#">Livres lus</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="#">Mes Évaluations</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="#">Les Statistiques</a>
                            </li>

                        </ul>
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="search" placeholder="Recherche..."
                                   aria-label="Search">
                            <div class="input-group-append">
                                <button class="input-group-text search-icon"></button>
                            </div>
                        </form>
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                                    <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-user-64.png"
                                         width="30px">

                                </a>
                                <div class="dropdown-menu ml-auto dropdown-menu-right"
                                     aria-labelledby="navbarDropdown2">
                                    <a class="dropdown-item">${login}</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Profil</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Deconnexion">Se
                                        déconnecter</a>

                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</header>











