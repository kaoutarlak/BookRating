
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Visiteur/Header.jsp"></jsp:include>

<!-- Corps de la page -->
<div class="container-fluid mt-5 ">
    <div class="row divConnexion">
        <div class="col-md-12 ">
            <div class="loginSection" id="loginSection">
                <br/>
                <p class="titreFrame">Connexion</p>
                <br>
                <form action="Connexion" method="post" id="frmConnecter" >
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1"><img src="${pageContext.request.contextPath}/resources/images/icones/icons8-utilisateur-60.png" width="25" ></span>
                        <c:if test="${newMembre!=null}">
                            <input type="text" class="form-control" id="login" name="login" placeholder=${newMembre.login} required>
                        </c:if>
                        <c:if test="${newMembre==null}">
                            <input type="text" class="form-control" id="login" name="login" placeholder="Login" required>
                        </c:if>

                    </div>
                    <p class="line1"></p>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon2"> <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-cadenas-50.png" width="25" ></span>
                        <c:if test="${newMembre!=null}">
                            <input type="password" class="form-control" id="password" name="password" placeholder=${newMembre.password} required>
                        </c:if>
                        <c:if test="${newMembre==null}">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" required>
                        </c:if>

                    </div>
                    <p class="line1"></p>
                    <c:if test="${error!=null}">
                        <p class="text-danger">${error}</p>
                    </c:if></center>
                    <center><button id="lienPassOublie" type="button" class="btn btn-link" >Mots de passe oublié</button></center>
                    <center><input type="submit" value="Ouvrir une session" class="btn btn-primary" ></center>
                    <center>
                        <div id="newUserTitre">
                            <span class="line2">&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            <span>Nouveau utilisateur</span>
                            <span class="line2">&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </div>
                    </center>
                    <center><a class="btn btn-secondary text-white" href="${pageContext.request.contextPath}/Inscription"> Créer un compte</a></center>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="Footer.jsp"></jsp:include>
