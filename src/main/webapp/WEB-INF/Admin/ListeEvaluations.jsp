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

<jsp:include page="../Footer.jsp"></jsp:include>

