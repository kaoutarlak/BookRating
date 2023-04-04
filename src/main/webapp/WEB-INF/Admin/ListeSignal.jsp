<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp">
    <jsp:param name="login" value="${login}"/>
</jsp:include>

<br><br>
<div class="h4 ml-5 pl-5 text-info sUnderline ">Liste des signalements :</div>
<div class="framePrincipale">
    <br>
    <table class="table table-bordered text-center">
        <thead>
        <tr class="table-danger">
            <th scope="col">Verifier</th>
            <th scope="col">ID Signale</th>
            <th scope="col">ID Évaluation</th>
            <th scope="col">message</th>
            <th scope="col">login</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${signalementList}" var="element">
            <tr>
                <td>
                    <c:if test="${element.verifier==0}">
                        <img src="${pageContext.request.contextPath}/resources/images/icones/non-valide.png" width="30"
                             height="30">
                    </c:if>
                    <c:if test="${element.verifier==1}">
                        <img src="${pageContext.request.contextPath}/resources/images/icones/valide.png" width="30"
                             height="30">
                    </c:if>
                </td>
                <td class="h6 text-success">${element.id}</td>
                <td class="h6 text-info">${element.idAvis}</td>
                <td class="itemTitreBest">${element.message}</td>
                <td class="itemAuteurBest">${element.login}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/Admin/Regler/Signalement" method="post">
                        <input type="hidden" value="${element.id}" name="idSignal">
                        <c:if test="${element.verifier==0}">
                            <input type="submit" class="btn btn-info" value="Régler">
                        </c:if>
                        <c:if test="${element.verifier==1}">
                            <input type="submit" class="btn btn-info" value="Régler" disabled>
                        </c:if>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br/>
<br/>

<jsp:include page="../Footer.jsp"></jsp:include>

