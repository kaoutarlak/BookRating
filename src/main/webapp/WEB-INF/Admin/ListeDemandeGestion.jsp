<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp">
    <jsp:param name="login" value="${login}"/>
</jsp:include>

<br><br>
<div class="h4 ml-5 pl-5 text-info sUnderline ">Liste des demandes des gestions :</div>
<div class="framePrincipale">
    <br>
    <table class="table table-bordered text-center">
        <thead>
        <tr class="table-danger">
            <th scope="col">ID</th>
            <th scope="col" style="white-space: nowrap;">Faite par</th>
            <th scope="col">Message</th>
            <th scope="col" style="white-space: nowrap;">ID Livre</th>
            <th scope="col" style="white-space: nowrap;">Répondre par</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${demandeList}" var="element">
            <tr>
                <td class="h6 text-success">${element.id}</td>
                <td class="h6 itemAuteurBest">${element.loginAuteur}</td>
                <td class="itemTitreBest">${element.message}</td>
                <td class="h6 text-primary">${element.idLivre}</td>
                <td class="h6 text-info">${element.loginAdmin}</td>
                <td>
                    <c:if test="${element.etatDemande=='En cours'}">
                        <a href="${pageContext.request.contextPath}/Admin/DemandeGestion/${element.id}/Accepter/${login}"
                           class="btn btn-info">Accepter</a>
                        &nbsp;
                        <a href="${pageContext.request.contextPath}/Admin/DemandeGestion/${element.id}/Refuser/${login}"
                           class="btn btn-danger">Refuser</a>
                    </c:if>
                    <c:if test="${element.etatDemande!='En cours'}">
                        <span class="h6 text-success">${element.etatDemande}</span>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br/>
<br/>


<jsp:include page="../Footer.jsp"></jsp:include>


