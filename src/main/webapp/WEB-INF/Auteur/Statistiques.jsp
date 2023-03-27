<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp"></jsp:include>

<div class="mb-5 justify-content-center divStat">

    <div class="row m-5">
        <form action="${pageContext.request.contextPath}/Statistique/MesLivres/Categorie" method="post" id="myFormFiltre">
            <div class="form-group d-flex align-items-center">
                <select class="form-control text-lg flex-grow-1" name="categorie" onchange="submitForm()">
                    <option disabled selected>Trier par</option>
                    <c:forEach items="${catLivreList}" var="c">
                        <option value="${c.id}">${c.titre}</option>
                    </c:forEach>
                </select>
                <c:if test="${effaceFiltre}">
                    <a href="${pageContext.request.contextPath}/Statistique/MesLivres"
                       class="sUnderline text-warning d-inline w-100 ml-2">Effacer le filtre </a>
                </c:if>
            </div>
        </form>
    </div>

    <div class="row">
        <div class="col-3 zoneStatTotal">
            <p class="m-3 text-center txtTotal">Total Livres :</p>
            <p class="m-3 text-center text-danger h3">${statMap.get("nb_livre")}</p>
        </div>
        <div class="col-3 zoneStatTotal">
            <p class="m-3 text-center txtTotal">Total Lectures :</p>
            <p class="m-3 text-center text-warning h3">${statMap.get("nb_livreLu")}</p>
        </div>
        <div class="col-3 zoneStatTotal">
            <p class="m-3 text-center txtTotal">Total Évaluations :</p>
            <p class="m-3 text-center text-success h3">${statMap.get("nb_livreEvalue")}</p>
        </div>

    </div>
    <br>
    <section class="row m-5 border rounded">
        <div class="col-12 bg-light border-bottom">
            <h4 class="text-center txtTotal m-2 ">Les livres les plus évalués</h4>
        </div>
        <div class="row mx-auto">
            <c:forEach items="${livrePlusNote}" var="livre">
                <article class="col-3 bestLivre">
                    <figure class="m-3">
                        <c:if test='${(livre.image).contains("/resources/images/")}'>
                            <a href="${pageContext.request.contextPath}/Livres/Detail/${livre.id}"><img
                                    class="itemImgBest" src="${pageContext.request.contextPath}${livre.image}"></a>
                        </c:if>
                        <c:if test='${not (livre.image).contains("/resources/images/")}'>
                            <a href="${pageContext.request.contextPath}/Livres/Detail/${livre.id}"><img
                                    class="itemImgBest" src="${livre.image}"></a>
                        </c:if>
                        <div class="itemTitreBest">${livre.titre}</div>
                        <div class="itemAuteurBest">${livre.nomAuteur}</div>
                        <div style="width: 150%;">
                            <span class="mr-1 text-white bg-danger p-1 rounded">${livre.noteMoyenne}</span>
                            <img src="${pageContext.request.contextPath}/resources/images/icones/${String(livre.noteMoyenne).substring(0, 1)}etoile.png"
                                 class="mr-4">
                            <span class="itemAuteurDetail sUnderline">${livre.nombreEvaluation} : Avis</span>
                        </div>
                    </figure>
                </article>
            </c:forEach>
        </div>
    </section>

    <section class="row m-5 border rounded">
        <div class="col-12 bg-light border-bottom">
            <h4 class="text-center txtTotal m-2 ">Les livres les plus lus</h4>
        </div>
        <div class="row mx-auto">
            <c:forEach items="${livrePlusLu}" var="livre">
                <article class="col-3 bestLivre">
                    <figure class="m-3">
                        <c:if test='${(livre.image).contains("/resources/images/")}'>
                            <a href="${pageContext.request.contextPath}/Livres/Detail/${livre.id}"><img
                                    class="itemImgBest" src="${pageContext.request.contextPath}${livre.image}"></a>
                        </c:if>
                        <c:if test='${not (livre.image).contains("/resources/images/")}'>
                            <a href="${pageContext.request.contextPath}/Livres/Detail/${livre.id}"><img
                                    class="itemImgBest" src="${livre.image}"></a>
                        </c:if>
                        <div class="itemTitreBest">${livre.titre}</div>
                        <div class="itemAuteurBest">${livre.nomAuteur}</div>
                        <div class="text-danger sUnderline">${livre.idCategorieLivre} : Lecture</div>
                    </figure>
                </article>
            </c:forEach>
        </div>
    </section>

</div>

<jsp:include page="../Footer.jsp"></jsp:include>

<script>
    function submitForm() {
        document.getElementById("myFormFiltre").submit();
    }
</script>