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
<div class="container-fluid mt-5 ">
  <div class="row ">
    <div class="col-md-12 ">
      <div class="zoneListProduit justify-content-center" id="zoneListProduit">
        <div id="numPageProduit">
<%--          <c:forEach var="i" begin="1" end="${nbPageLivre}" step="${1}">--%>
<%--            <a href="#" class="numPage">${i} </a>&nbsp; &nbsp;--%>
<%--          </c:forEach>--%>

        </div>
        <div id="livreInfo">
          <c:forEach items="${livres}" var="element" varStatus="loopStatus" begin="0" end="9">
            <div class="itemProduit">
              <img class="itemImg" src="${pageContext.request.contextPath}${element.image}">
              <div class="itemTitre">${element.titre}</div>
              <div class="itemAuteur">${element.nomAuteur}</div>
              <c:if test="${role=='auteur' || role=='membre'}">
                <a href="" class="btn text-white itemBtn" onclick="addLivreLu('${login}', '${element.id}')">&nbsp;Livre lu
                  <img src="${pageContext.request.contextPath}/resources/images/icones/livre-lu.png"
                       width="24">
                </a>

                <a class="btn text-white itemBtn" >&nbsp;Évaluer
                  <img src="${pageContext.request.contextPath}/resources/images/icones/review.png"
                       width="24">
                </a>
              </c:if>

            </div>
          </c:forEach>

        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="Footer.jsp"></jsp:include>