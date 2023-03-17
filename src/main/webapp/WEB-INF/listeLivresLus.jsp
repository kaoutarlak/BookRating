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


<!-- Corps de la page -->
<%--<div class="container-fluid mt-5 ">--%>
<%--    <div class="row ">--%>
<%--        <div class="col-md-12 ">--%>
<div class="zoneListProduit justify-content-center" id="zoneListProduit">
    <div class="row tete-ZoneLivre">
        <div class="col-2 zIndex">
            <div class="totalLivre">
                <span class="itemTitre">Total livres lus : </span><span class="itemAuteur">${livres.size()}</span>
            </div>
        </div>
        <div class="col-8 text-center">
            <div id="numPageProduit">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage!=1 && nbPageLivre>1}">
                            <li class="page-item">
                                <a class="page-link border-0"
                                   href="${pageContext.request.contextPath}/Livres/LivresLus/1"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${nbPageLivre}" step="${1}">
                            <li class="page-item"><a class="page-link"
                                                     href="${pageContext.request.contextPath}/Livres/LivresLus/${i}">${i}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage!=nbPageLivre && nbPageLivre>1}">
                            <li class="page-item">
                                <a class="page-link border-0"
                                   href="${pageContext.request.contextPath}/Livres/LivresLus/${nbPageLivre}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>

                    </ul>
                </nav>
            </div>
        </div>
        <div class="col-2"></div>
    </div>

    <div id="livreInfo">
        <c:forEach items="${livres}" var="element" varStatus="loopStatus" begin="${idLivreBegin}" end="${idLivreEnd}">
            <div class="itemProduit">
                <img class="itemImg" src="${pageContext.request.contextPath}${element.image}">
                <div class="itemTitre">${element.titre}</div>
                <div class="itemAuteur">${element.nomAuteur}</div>
                <c:if test="${role=='auteur' || role=='membre'}">

                    <a class="btn text-white itemBtn">&nbsp;Évaluer
                        <img src="${pageContext.request.contextPath}/resources/images/icones/review.png"
                             width="24">
                    </a>
                </c:if>

            </div>
        </c:forEach>

    </div>
</div>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<jsp:include page="Footer.jsp"></jsp:include>