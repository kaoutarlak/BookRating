<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp">
    <jsp:param name="login" value="${login}"/>
</jsp:include>


<!-- Corps de la page -->
<div class="container-fluid mt-5 ">
    <div class="row ">
        <div class="col-md-12 ">

            <div class="framePrincipale">
                <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#modal1">
                    <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-add-new-24.png">&nbsp;&nbsp;Ajouter</a>
                <div class="modal fade" id="modal1" tabindex="-1" role="dialog" aria-labelledby="modal1Label"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title " id="modal1Label">Ajouter une nouvelle catégorie </h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="Categorie/Add" method="post">
                                    <div class="input-group mb-3">

                                        <input type="text" class="form-control" id="description" name="description"
                                               placeholder="Description" required>
                                        <input type="submit" value="Ajouter" class="btn btn-danger">
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
                <br/>
                <table class="table table-bordered text-center">
                    <thead>
                    <tr class="table-danger">

                        <th scope="col">ID</th>
                        <th scope="col">Description</th>
                        <th scope="col">Modifier</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${categorieEvaluationList}" var="element">
                        <tr>

                            <td class="h6 text-info">${element.id}</td>
                            <td class="itemTitreBest">${element.description}</td>
                            <td><a href="" class="btn" data-toggle="modal" data-target="#modal2">
                                <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-pencil-24.png">
                            </a>
                                <div class="modal fade" id="modal2" tabindex="-1" role="dialog"
                                     aria-labelledby="modal2Label" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="modal2Label">Modifier une catégorie</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="Categorie/Alter" method="post">
                                                    <div class="input-group mb-3">
                                                        <input type="hidden" name="id" value="${element.id}">
                                                        <input type="text" class="form-control"
                                                               name="description"
                                                               value="${element.description}" required>
                                                        <input type="submit" value="Modifier" class="btn btn-danger">
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
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>


        </div>
    </div>
</div>

<jsp:include page="../Footer.jsp"></jsp:include>