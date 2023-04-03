<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp">
    <jsp:param name="login" value="${login}"/>
</jsp:include>


<!-- Corps de la page -->
<div class="framePrincipale">
    <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#modalADD">
        <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-add-new-24.png">&nbsp;&nbsp;Ajouter
    </a>
    <br/>
    <br/>
    <table class="table table-bordered">
        <thead>
        <tr class="table-danger">
            <th scope="col"></th>
            <th scope="col">image</th>
            <th scope="col">Catégorie</th>
            <th scope="col">Titre</th>
            <th scope="col">Auteur</th>
            <th scope="col" style="white-space: nowrap;">Date Parution</th>
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
                        <%--Modal Modifier--%>
                    <div class="modal fade" id="modalModif" tabindex="-1" role="dialog"
                         aria-labelledby="modalModifLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalModifLabel">Modifier un livre</h5>
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/Auteur/AlterLivre" method="POST">
                                        <div class="form-group row">
                                            <label class="col-sm-3 col-form-label itemAuteurBest">Titre :</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="titre"
                                                       value="${element.titre}" required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 col-form-label itemAuteurBest">Catégorie :</label>
                                            <div class="col-sm-9">
                                                <select class="form-control" name="idCategorieLivre" required>
                                                    <c:forEach items="${catLivreList}" var="c">
                                                        <c:if test="${element.idCategorieLivre==c.id}">
                                                            <option value="${c.id}" selected>${c.titre}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:forEach items="${catLivreList}" var="c">
                                                        <option value="${c.id}">${c.titre}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 col-form-label itemAuteurBest">Date de parution :</label>
                                            <div class="col-sm-9">
                                                <input type="date" class="form-control" name="dateParution"
                                                       value="${element.dateParution}" required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 col-form-label itemAuteurBest">URL image :</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="image"
                                                       value="${element.image}" required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 col-form-label itemAuteurBest">Description :</label>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-12">
                                                <textarea class="form-control" name="description" rows="4"
                                                          required>${element.description}</textarea>
                                            </div>
                                        </div>
                                        <input type="hidden" value="${login}" name="addBy">
                                        <input type="hidden" value="${element.id}" name="id">
                                        <input type="hidden" value="${element.nomAuteur}" name="nomAuteur">
                                        <div class="form-group row">
                                            <div class="col-sm-2">
                                                <input type="submit" value="Enregistrer" class="btn btn-danger">
                                            </div>
                                        </div>
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
                </td>
                <td><a href="${pageContext.request.contextPath}/Auteur/Livre/${element.id}/Avis"><img
                        src="${element.image}" width="40" height="50"></a></td>
                <c:forEach items="${catLivreList}" var="c">
                    <c:if test="${c.id==element.idCategorieLivre}">
                        <td>${c.titre}</td>
                    </c:if>
                </c:forEach>
                <td class="itemTitreBest">${element.titre}</td>
                <td class="itemAuteurBest">${element.nomAuteur}</td>
                <td class="h6 text-info">${element.dateParution}</td>
                <td class="h6">${element.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<%--Modal Ajouter--%>
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
                <form action="${pageContext.request.contextPath}/Auteur/AddLivre" method="POST">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label itemAuteurBest">Titre :</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="titre" required>
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

