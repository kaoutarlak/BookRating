<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp">
    <jsp:param name="login" value="${login}"/>
</jsp:include>


<div class="row mt-5 mr-5">
    <div class="col-2">
        <a href="#" class="btn btn-primary btnAddLivreAdmin" data-toggle="modal" data-target="#modalModif">
            <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-add-new-24.png">&nbsp;&nbsp;Ajouter
        </a>
    </div>
    <form action="${pageContext.request.contextPath}/Admin/ListeLivres/Categorie" method="post" id="myFormFiltre" class="offset-10 col-2 pr-5 mr-5">
        <div class="form-group d-flex align-items-center">
            <select class="form-control text-lg flex-grow-1" name="categorie" onchange="submitForm()">
                <option disabled selected>Trier par</option>
                <c:forEach items="${catLivreList}" var="c">
                    <option value="${c.titre}">${c.titre}</option>
                </c:forEach>
            </select>
            <c:if test="${effaceFiltre!=null}">
                <a href="${pageContext.request.contextPath}/Admin/ListeLivres"
                   class="sUnderline text-warning d-inline w-100 ml-2">Effacer le filtre </a>
            </c:if>
        </div>
    </form>
</div>

<div class="ListeLivre">
    <br>
    <table class="table table-bordered text-center">
        <thead>
        <tr class="table-danger">
            <th scope="col"></th>
            <th scope="col">Id</th>
            <th scope="col">image</th>
            <th scope="col">Catégorie</th>
            <th scope="col">Titre</th>
            <th scope="col">Auteur</th>
            <th scope="col" style="white-space: nowrap;">Date Parution</th>
            <th scope="col">Description</th>
            <th scope="col">Ajouter par</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${livres}" var="element">
            <tr>
                <td>
                    <a href="" class="btn" data-toggle="modal" data-target="#modalModif">
                        <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-pencil-24.png">
                    </a>
                </td>
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
                    <c:if test="${c.id==element.idCategorieLivre}">
                        <td class="h6">${c.titre}</td>
                    </c:if>
                </c:forEach>
                <td class="itemTitreBest">${element.titre}</td>
                <td class="itemAuteurBest">${element.nomAuteur}</td>
                <td class="h6 text-info" width="20">${element.dateParution}</td>
                <td class="h6">${element.description}</td>
                <td class="h6 text-primary">${element.addBy}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br/>
<br/>

<%--Modal ADD Livre--%>
<div class="modal fade" id="modalModif" tabindex="-1" role="dialog"
     aria-labelledby="modalModifLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalModifLabel">Ajouter un livre</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/Admin/AddLivre" method="POST" >
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label itemAuteurBest">Titre :</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="titre" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label itemAuteurBest">Nom d'auteur :</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="nomAuteur" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label itemAuteurBest">Catégorie :</label>
                        <div class="col-sm-9">
                            <select class="form-control" name="idCategorieLivre" required>
                                <c:forEach items="${catLivreList}" var="c">
                                    <option value="${c.id}">${c.titre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label itemAuteurBest">Date de parution :</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" name="dateParution" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label itemAuteurBest">URL image :</label>
                        <div class="col-sm-9">
                            <%--<input type="file" class="form-control" name="file" >--%>
                                <input type="text" class="form-control" name="image" required>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="itemAuteurBest">Description :</label>
                        <textarea class="form-control" name="description" rows="4" required></textarea>
                    </div>
                    <input type="hidden" value="${login}" name="addBy">
                    <input type="submit" value="Enregistrer" class="btn btn-danger">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    Annuler
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../Footer.jsp"></jsp:include>

<script>
    function submitForm() {
        document.getElementById("myFormFiltre").submit();
    }
</script>
