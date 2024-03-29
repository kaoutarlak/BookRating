<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Visiteur/Header.jsp"></jsp:include>

<!-- Corps de la page -->
<%--<div class="container-fluid mt-5 ">--%>
<%--    <div class="col-md-12 ">--%>
        <div class="divConnexion">
            <div class="newCompteSection" id="newCompteSection">
                <br/>
                <p class="titreFrame">Inscription</p>
                <br>
                <form action="Inscription" method="post" id="frmNewCompte">
                    <div class="input-group mb-3">
                        <span class="input-group-text"><img
                                src="${pageContext.request.contextPath}/resources/images/icones/icons8-info.png"
                                width="25"></span>
                        <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" value="${infoSaisi.nom}" required>
                        &nbsp;&nbsp;
                        <span class="input-group-text"><img
                                src="${pageContext.request.contextPath}/resources/images/icones/icons8-info.png"
                                width="25"></span>
                        <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom" value="${infoSaisi.prenom}" required>
                    </div>
                    <p class="line1"></p>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><img
                                src="${pageContext.request.contextPath}/resources/images/icones/icons8-info.png"
                                width="25"></span>
                        <input type="tel" class="form-control" id="telephone" name="telephone" placeholder="Téléphone"
                               value="${infoSaisi.telephone}" required>
                        &nbsp;&nbsp;
                        <span class="input-group-text"><img
                                src="${pageContext.request.contextPath}/resources/images/icones/icons8-info.png"
                                width="25"></span>
                        <input type="date" class="form-control" id="dateNaissance" name="dateNaissance"
                               placeholder="Date de naissance" value="${infoSaisi.dateNaissance}" required>
                    </div>
                    <p class="line1"></p>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><img
                                src="${pageContext.request.contextPath}/resources/images/icones/icons8-courrier-50.png"
                                width="25"></span>
                        <input type="email" class="form-control" id="adresse" name="adresse"
                               placeholder="Adresse courriel" value="${infoSaisi.adresse}" required>
                    </div>
                    <p class="line1"></p>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><img
                                src="${pageContext.request.contextPath}/resources/images/icones/icons8-utilisateur-60.png"
                                width="25"></span>
                        <input type="text" class="form-control" id="login" name="login" placeholder="Login" value="${infoSaisi.login}" required>
                        &nbsp;&nbsp;
                        <span class="input-group-text" id="basic-addon2"><img
                                src="${pageContext.request.contextPath}/resources/images/icones/icons8-cadenas-50.png"
                                width="25"></span>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Mot de passe" value="${infoSaisi.password}" required>
                    </div>
                    <c:if test="${message!=null}">
                        <p class="text-danger">${message}</p>
                    </c:if>
                    <p class="line1"></p>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="acceptConditions" required>
                        <label class="form-check-label">acceptez les conditions et la politique de
                            confidentialité.</label>
                    </div>
                    <center><input type="submit" value="Créer un compte" class="btn btn-primary "></center>
                    <center><a href="${pageContext.request.contextPath}/Connexion"
                               class="btn btn-link text-decoration-none"><img
                            src="${pageContext.request.contextPath}/resources/images/icones/icons8-flèche-gauche-50.png"
                            alt="Retourner" width="30"></a>
                        </center>
                </form>
            </div>
        </div>
<%--    </div>--%>
<%--</div>--%>

<jsp:include page="Footer.jsp"></jsp:include>
