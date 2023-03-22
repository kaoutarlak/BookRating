<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Visiteur/Header.jsp">
    <jsp:param name="catLivreList" value="${catLivreList}"/>
</jsp:include>

<!-- Corps de la page -->
<%--<div class="container-fluid mt-5 ">--%>
<%--    <div class="col-md-12 ">--%>
<div class="divConnexion">
    <div class="loginSection" id="loginSection">
        <br/>
        <p class="titreFrame">Connexion</p>
        <br>
        <form action="Connexion" method="post" id="frmConnecter">
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1"><img
                        src="${pageContext.request.contextPath}/resources/images/icones/icons8-utilisateur-60.png"
                        width="25"></span>
                <c:if test="${newMembre!=null}">
                    <input type="text" class="form-control" id="login" name="login"
                           placeholder=${newMembre.login} required>
                </c:if>
                <c:if test="${newMembre==null}">
                    <input type="text" class="form-control" id="login" name="login" placeholder="Login" required>
                </c:if>

            </div>
            <p class="line1"></p>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon2"> <img
                        src="${pageContext.request.contextPath}/resources/images/icones/icons8-cadenas-50.png"
                        width="25"></span>
                <c:if test="${newMembre!=null}">
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder=${newMembre.password} required>
                </c:if>
                <c:if test="${newMembre==null}">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe"
                           required>
                </c:if>

            </div>
            <p class="line1"></p>
            <c:if test="${error!=null}">
                <p class="text-danger">${error}</p>
            </c:if>
            <center>
                <button id="lienPassOublie" type="button" class="btn btn-link">Mots de passe oublié</button>
            </center>
            <center><input type="submit" value="Ouvrir une session" class="btn btn-primary"></center>
            <center>
                <div id="newUserTitre">
                    <span class="line2">&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <span>Nouveau utilisateur</span>
                    <span class="line2">&nbsp;&nbsp;&nbsp;&nbsp;</span>
                </div>
            </center>
            <center><a class="btn btn-secondary text-white" href="${pageContext.request.contextPath}/Inscription"
                       data-toggle="modal" data-target="#myModal"> Créer un compte</a></center>

        </form>
    </div>
</div>


<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title itemTitre" id="modal1Label">Voulez-vous faire l'inscription an tant que: </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/InscriptionType" method="post">
                    <div class="container">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="role"
                                   id="auteur" value="auteur" required>
                            <label class="form-check-label" for="auteur">Auteur</label>
                            <br>
                            <br>
                            <input class="form-check-input" type="radio" name="role"
                                   id="membre" value="membre" required>
                            <label class="form-check-label" for="membre">Membre</label>
                        </div>
                    </div>
                    <br>
                    <input type="submit" value="Confirmer" class="btn btn-primary">

                </form>
            </div>
        </div>
    </div>
</div>
<%--    </div>--%>
<%--</div>--%>

<jsp:include page="Footer.jsp"></jsp:include>
