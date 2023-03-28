<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp"></jsp:include>

<div class="mb-5 justify-content-center divStat">

    <div class="row m-5">
        <form action="${pageContext.request.contextPath}/Statistique/Admin/Categorie" method="post" id="myFormFiltre">
            <div class="form-group d-flex align-items-center">
                <select class="form-control text-lg flex-grow-1" name="categorie" onchange="submitForm()">
                    <option disabled selected>Trier par</option>
                    <c:forEach items="${catLivreList}" var="c">
                        <option value="${c.id}">${c.titre}</option>
                    </c:forEach>
                </select>
                <c:if test="${effaceFiltre}">
                    <a href="${pageContext.request.contextPath}/Statistique/Admin"
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
            <h4 class="text-center txtTotal m-2 ">Statistique Par livre</h4>
        </div>

        <div class="col-12 mx-auto pt-2">
            <table class="table table-bordered text-center">
                <thead>
                <tr class="table-danger">
                    <th scope="col">Id</th>
                    <th scope="col">Image</th>
                    <th scope="col">Catégorie</th>
                    <th scope="col">Titre</th>
                    <th scope="col">Auteur</th>
                    <th scope="col">Nombre Avis</th>
                    <th scope="col">Note moyenne</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${statLivres}" var="element">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/Livres/Detail/${element.id}" class="h6 sUnderline">${element.id}</a></td>
                        <td>
                            <c:if test='${(element.image).contains("/resources/images/")}'>
                                    <img src="${pageContext.request.contextPath}${element.image}"
                                         width="40" height="50"/>
                            </c:if>
                            <c:if test='${not (element.image).contains("/resources/images/")}'>
                                    <img src="${element.image}" width="40" height="50"/>
                            </c:if>
                        </td>
                        <c:forEach items="${catLivreList}" var="c">
                            <c:if test="${c.id==element.ididCategorieLivre}">
                                <td>${c.titre}</td>
                            </c:if>
                        </c:forEach>
                        <td class="itemTitreBest">${element.titre}</td>
                        <td class="itemAuteurBest">${element.nomAuteur}</td>
                        <td class="h6 text-success">${element.nombreEvaluation}</td>
                        <td ><span class="mr-1 text-white bg-danger p-1 rounded">${element.noteMoyenne}</span></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

</div>

<jsp:include page="../Footer.jsp"></jsp:include>

<script>
    function submitForm() {
        document.getElementById("myFormFiltre").submit();
    }
</script>