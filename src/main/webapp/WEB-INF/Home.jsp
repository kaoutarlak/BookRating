<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${login == ''}">
    <jsp:include page="Visiteur/Header.jsp">
        <jsp:param name="catLivreList" value="${catLivreList}"/>
    </jsp:include>
</c:if>
<c:if test="${login != ''}">
    <c:if test="${role=='membre'}">
        <jsp:include page="Membre/Header.jsp">
            <jsp:param name="login" value="${login}"/>
            <jsp:param name="catLivreList" value="${catLivreList}"/>
        </jsp:include>
    </c:if>

    <c:if test="${role=='admin'}">
        <jsp:include page="Admin/Header.jsp">
            <jsp:param name="login" value="${login}"/>
            <jsp:param name="catLivreList" value="${catLivreList}"/>
        </jsp:include>
    </c:if>

    <c:if test="${role=='auteur'}">
        <jsp:include page="Auteur/Header.jsp">
            <jsp:param name="login" value="${login}"/>
            <jsp:param name="catLivreList" value="${catLivreList}"/>
        </jsp:include>
    </c:if>
</c:if>


<jsp:include page="Footer.jsp"></jsp:include>
