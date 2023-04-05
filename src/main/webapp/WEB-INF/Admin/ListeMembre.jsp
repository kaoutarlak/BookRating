<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp">
    <jsp:param name="login" value="${login}"/>
</jsp:include>

<br><br>
<div class="h4 ml-5 pl-5 text-info sUnderline ">Liste des membres :</div>
<div class="framePrincipale">
    <br>
    <table class="table table-bordered text-center">
        <thead>
        <tr class="table-danger">
            <th scope="col">Login</th>
            <th scope="col">Image</th>
            <th scope="col" style="white-space: nowrap;">Mot de passe</th>
            <th scope="col">Nom</th>
            <th scope="col">Prénom</th>
            <th scope="col">Téléphone</th>
            <th scope="col">E-mail</th>
            <th scope="col" style="white-space: nowrap;">Date de naissance</th>
            <th scope="col" style="white-space: nowrap;">Date d'inscription</th>
            <th scope="col">Activé</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${membreList}" var="element">
            <tr>
                <td class="h6 text-success">${element.login}</td>
                <td><img src="${element.photo}" width="30" height="30"></td>
                <td class="h6 itemAuteurBest">${element.password}</td>
                <td class="itemTitreBest">${element.nom}</td>
                <td class="itemTitreBest">${element.prenom}</td>
                <td class="itemAuteurBest">${element.telephone}</td>
                <td class="h6 text-primary">${element.adresse}</td>
                <td class="h6 text-info">${element.dateNaissance}</td>
                <td class="h6 text-info">${element.dateInsscription}</td>
                <td class="h6 text-success">${element.active}</td>
                <td>
                    <c:if test="${element.active==1}">
                        <a href="${pageContext.request.contextPath}/Admin/Membre/Desactiver/${element.login}"
                           class="btn btn-info">Désactiver</a>
                    </c:if>
                    <c:if test="${element.active==0}">
                        <a href="${pageContext.request.contextPath}/Admin/Membre/Activer/${element.login}"
                           class="btn btn-success">Activer</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/Admin/Membre/Supprimer/${element.login}"
                       class="btn btn-danger">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br/>
<br/>


<jsp:include page="../Footer.jsp"></jsp:include>


