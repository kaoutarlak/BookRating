<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp">
    <jsp:param name="login" value="${login}"/>
</jsp:include>

<br><br>
<div class="h4 ml-5 pl-5 text-info sUnderline ">Liste des évaluations :</div>
<div class="framePrincipale">
    <br>
    <table class="table table-bordered text-center">
        <thead>
        <tr class="table-danger">
            <th scope="col">ID</th>
            <th scope="col" style="white-space: nowrap;">Faite par</th>
            <th scope="col" style="white-space: nowrap;">Date Création</th>
            <th scope="col" style="white-space: nowrap;">ID Livre</th>
            <th scope="col">Commentaire</th>
            <th scope="col" style="white-space: nowrap;">Nb likes</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${avisList}" var="element">
            <tr>
                <td class="h6 text-success">${element.id}</td>
                <td class="h6 itemAuteurBest">${element.login}</td>
                <td class="h6 text-info">${element.datePost}</td>
                <td class="h6 text-danger">${element.idLivre}</td>
                <td class="itemTitreBest">${element.commentaire}</td>
                <td class="h6 text-success">${element.nbLikes}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/Admin/Evaluations/Supprimer/${element.id}" class="btn btn-danger">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br/>
<br/>

<%--Modal ADD Livre--%>
<div class="modal fade" id="modalADD" tabindex="-1" role="dialog"
     aria-labelledby="modalADDLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalADDLabel">Ajouter un livre</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/Admin/AddLivre" method="POST">
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
