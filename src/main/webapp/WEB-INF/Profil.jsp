<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${role=='membre'}">
    <jsp:include page="Membre/Header.jsp">
        <jsp:param name="login" value="${login}"/>
        <jsp:param name="membre" value="${membre}"/>
        <jsp:param name="catLivreList" value="${catLivreList}"/>

    </jsp:include>
</c:if>

<c:if test="${role=='admin'}">
    <jsp:include page="Admin/Header.jsp">
        <jsp:param name="login" value="${login}"/>
        <jsp:param name="catLivreList" value="${catLivreList}"/>
        <jsp:param name="membre" value="${membre}"/>
    </jsp:include>
</c:if>

<c:if test="${role=='auteur'}">
    <jsp:include page="Auteur/Header.jsp">
        <jsp:param name="login" value="${login}"/>
        <jsp:param name="membre" value="${membre}"/>
        <jsp:param name="catLivreList" value="${catLivreList}"/>
    </jsp:include>
</c:if>

<div class="divConnexion row">
    <div class="col-12 border-bottom">
        <h4 class="txtTotal m-2 ">Information mon profil :</h4>
    </div>
    <div class="col-12 mx-auto newCompteSection" id="newCompteSection">
        <br>
        <form action="ModifierProfil" method="post">

            <div class="input-group mb-3 row mx-auto">
                <label class="col-sm-2 col-form-label itemAuteurBest">Photo de profil :</label>
                <img src="${membre.photo}" width="80" height="80" id="myImageProfil">
                <a class="btn text-danger sUnderline mt-5 pl-2" data-toggle="modal" data-target="#modal2">changer</a>
                <div class="modal fade" id="modal2" tabindex="-1" role="dialog"
                     aria-labelledby="modal2Label"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title " id="modal2Label">Choisir une photo de profil :</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div>
                                    <label class="ml-3">
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil01.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil01.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil02.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil02.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil03.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil03.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil04.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil04.png"
                                             width="80" height="80">
                                    </label>
                                </div>
                                <div>
                                    <label class="ml-3">
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil05.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil05.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil06.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil06.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil07.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil07.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil08.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil08.png"
                                             width="80" height="80">
                                    </label>
                                </div>
                                <div>
                                    <label class="ml-3">
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil09.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil09.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil10.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil10.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil11.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil11.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil12.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil12.png"
                                             width="80" height="80">
                                    </label>
                                </div>
                                <div>
                                    <label class="ml-3">
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil13.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil13.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil14.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil14.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil15.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil15.png"
                                             width="80" height="80">
                                    </label>
                                    <label>
                                        <input type="radio" name="image"
                                               value="${pageContext.request.contextPath}/resources/images/Profil/Profil16.png"
                                               onclick="changeProfil()">
                                        <img src="${pageContext.request.contextPath}/resources/images/Profil/Profil16.png"
                                             width="80" height="80">
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <p class="line1"></p>
            <div class="input-group mb-3 row mx-auto">
                <label for="login" class="col-sm-2 col-form-label itemAuteurBest">Login :</label>
                <input type="text" class="col-sm-4 form-control" id="login" name="login" placeholder="Login"
                       value="${membre.login}" disabled>
                &nbsp;&nbsp;
                <label for="password" class="col-sm-2 col-form-label itemAuteurBest">Mot de passe :</label>
                <input type="password" class="col-sm-3 form-control" id="password" name="password"
                       placeholder="Mot de passe" value="${membre.password}" required>
                <button type="button" class="col-sm-1 btn-info" onclick="togglePassword()">Afficher</button>
            </div>
            <p class="line1"></p>
            <div class="input-group mb-3 row mx-auto">
                <label for="nom" class="col-sm-2 col-form-label itemAuteurBest">Nom :</label>
                <input type="text" class="col-sm-4 form-control" id="nom" name="nom" placeholder="Nom"
                       value="${membre.nom}"
                       required>
                &nbsp;&nbsp;
                <label for="prenom" class="col-sm-2 col-form-label itemAuteurBest">Prénom :</label>
                <input type="text" class="col-sm-4 form-control" id="prenom" name="prenom" placeholder="Prénom"
                       value="${membre.prenom}" required>
            </div>
            <p class="line1"></p>
            <div class="input-group mb-3 row mx-auto">
                <label for="telephone" class="col-sm-2 col-form-label itemAuteurBest">Téléphone :</label>
                <input type="tel" class="col-sm-4 form-control" id="telephone" name="telephone" placeholder="Téléphone"
                       value="${membre.telephone}" required>
                &nbsp;&nbsp;
                <label for="telephone" class="col-sm-2 col-form-label itemAuteurBest">Date de naissance :</label>
                <input type="date" class="col-sm-4 form-control" id="dateNaissance" name="dateNaissance"
                       placeholder="Date de naissance" value="${membre.dateNaissance}" required>
            </div>
            <p class="line1"></p>
            <div class="input-group mb-3 row mx-auto">
                <label for="adresse" class="col-sm-2 col-form-label itemAuteurBest">Adresse mail :</label>
                <input type="email" class="col-sm-4 form-control" id="adresse" name="adresse"
                       placeholder="Adresse courriel" value="${membre.adresse}" disabled>

                <label for="dateInsscription" class="col-sm-2 col-form-label itemAuteurBest">Date inscription :</label>
                <input type="date" class="col-sm-4 form-control" id="dateInsscription" name="dateInsscription"
                       placeholder="Adresse courriel" value="${membre.dateInsscription}" disabled>
            </div>

            <div class="input-group row mt-5">
                <div class="col-sm-10 ">
                    <input type="hidden" value="${membre.photo}" name="photo" id="photo">
                    <input type="hidden" value="${membre.login}" name="login">
                </div>
            </div>

            <div class="input-group row mt-5">
                <div class="col-sm-10">
                    <input type="submit" value="Enregistrer" class="btn btn-primary ">
                </div>
                <div class="col-sm-2">
                    <a href="${pageContext.request.contextPath}/DesactiveCompte/${membre.login}" class="btn btn-danger ">Désactiver mon compte</a>
                </div>
            </div>

        </form>
    </div>
</div>

<jsp:include page="Footer.jsp"></jsp:include>


<script>
    function togglePassword() {
        var passwordInput = document.getElementById("password");
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
        } else {
            passwordInput.type = "password";
        }
    }

    function changeProfil() {
        const radioBtns = document.querySelectorAll('input[type=radio][name=image]');
        const image = document.getElementById('myImageProfil');
        const input = document.getElementById('photo');

        radioBtns.forEach(function (radioBtn) {
            radioBtn.addEventListener('change', function () {
                image.src = this.value;
                input.value = this.value;
            });
        });
    }
</script>