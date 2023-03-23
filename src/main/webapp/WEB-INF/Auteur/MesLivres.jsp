<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp">
    <jsp:param name="login" value="${login}"/>
</jsp:include>


<!-- Corps de la page -->
<div class="framePrincipale">
    <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#modalModif">
        <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-add-new-24.png">&nbsp;&nbsp;Ajouter
    </a>
    <br/>
    <br/>
    <table class="table table-bordered">
        <thead>
        <tr class="table-danger">
            <th scope="col"></th>
            <th scope="col">image</th>
            <%--<th scope="col">ID</th>--%>
            <th scope="col">Catégorie</th>
            <th scope="col">Titre</th>
            <th scope="col">Auteur</th>
            <th scope="col">Date Parution</th>
            <th scope="col">Description</th>
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
                <td><img src="${element.image}" width="30" height="30"></td>
                <c:forEach items="${catLivreList}" var="c">
                    <c:if test="${c.id==element.idCategorieLivre}">
                        <td>${c.titre}</td>
                    </c:if>
                </c:forEach>
                <td>${element.titre}</td>
                <td>${element.nomAuteur}</td>
                <td>${element.dateParution}</td>
                <td>${element.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<%--    <form action="${pageContext.request.contextPath}/Auteur/upload" method="post" >--%>
<%--        <input type="file" name="file" />--%>
<%--        <button type="submit">Upload</button>--%>
<%--    </form>--%>
</div>

<%--Modal Modifier--%>
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
                <form action="${pageContext.request.contextPath}/Auteur/AddLivre" method="POST" >
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Titre :</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="titre" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Catégorie :</label>
                        <div class="col-sm-9">
                            <select class="form-control" name="idCategorieLivre" required>
                                <c:forEach items="${catLivreList}" var="c">
                                    <option value="${c.id}">${c.titre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Date de parution :</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" name="dateParution" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Image :</label>
                        <div class="col-sm-9">
                            <%--<input type="file" class="form-control" name="file" >--%>
                                <input type="text" class="form-control" name="image" required>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label >Description :</label>
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

