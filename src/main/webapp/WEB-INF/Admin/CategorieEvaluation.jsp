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
                <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                    <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-add-new-24.png">&nbsp;&nbsp;Ajouter</a>
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title " id="myModalLabel">Ajouter une nouvelle
                                    catégorie </h4>
                            </div>
                            <div class="modal-body">
                                <form action="Categorie/Add" method="post" >
                                    <div class="input-group mb-3">

                                        <input type="text" class="form-control" id="description" name="description"
                                               placeholder="Description" required>
                                        <input type="submit" value="Ajouter" class="btn btn-danger">
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-info" data-dismiss="modal">Fermer</button>
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

                            <td>${element.id}</td>
                            <td>${element.description}</td>
                            <td><a href="${pageContext.request.contextPath}" data-toggle="modal"
                                   data-target="#myModalAlter">
                                <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-pencil-24.png">
                            </a>

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