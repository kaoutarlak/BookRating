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


<%

%>
<!-- Corps de la page -->
<%--<div class="container-fluid mt-5 ">--%>
<%--    <div class="row ">--%>
<%--        <div class="col-md-12 ">--%>
<div class="zoneListProduit justify-content-center" id="zoneListProduit">
    <div class="row tete-ZoneLivre">
        <div class="col-2 zIndex">
            <div class="totalLivre">
                <span class="itemTitre">Total livres :  </span>
                <c:if test="${livres.size() > 10}">
                    <span class="itemAuteur">${idLivreEnd+1} / ${livres.size()}</span>
                </c:if>
                <c:if test="${livres.size() <= 10}">
                    <span class="itemAuteur">${livres.size()}</span>
                </c:if>
            </div>
        </div>
        <div class="col-8 text-center">
            <div id="numPageProduit">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage!=1 && nbPageLivre>1}">
                            <li class="page-item">
                                <a class="page-link border-0"
                                   href="${pageContext.request.contextPath}/Livres/Liste/${categorie}/1"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${nbPageLivre}" step="${1}">
                            <c:if test="${i==currentPage}">
                                <li class="page-item"><a class="page-link activePage"
                                                         href="${pageContext.request.contextPath}/Livres/Liste/${categorie}/${i}">${i}</a>
                                </li>
                            </c:if>
                            <c:if test="${i!=currentPage}">
                                <li class="page-item"><a class="page-link "
                                                         href="${pageContext.request.contextPath}/Livres/Liste/${categorie}/${i}">${i}</a>
                                </li>
                            </c:if>

                        </c:forEach>
                        <c:if test="${currentPage!=nbPageLivre && nbPageLivre>1}">
                            <li class="page-item">
                                <a class="page-link border-0"
                                   href="${pageContext.request.contextPath}/Livres/Liste/${categorie}/${nbPageLivre}"
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
        <c:forEach items="${livres}" var="element" varStatus="loopStatus" begin="${idLivreBegin}"
                   end="${idLivreEnd}">
            <div class="itemProduit">
                <a href="${pageContext.request.contextPath}/Livres/Detail/${element.id}"><img class="itemImg" src="${pageContext.request.contextPath}${element.image}"></a>
                <div class="itemTitre">${element.titre}</div>
                <div class="itemAuteur">${element.nomAuteur}</div>
                <c:if test="${role=='auteur' || role=='membre'}">
                    <a href="" class="btn text-white itemBtn"
                       onclick="addLivreLu('${login}', '${element.id}')">&nbsp;Livre lu
                        <img src="${pageContext.request.contextPath}/resources/images/icones/livre-lu.png"
                             width="24">
                    </a>

                    <a href="#" class="btn text-white itemBtn" data-toggle="modal"
                       data-target="#modal_${loopStatus.index}">&nbsp;Évaluer
                        <img src="${pageContext.request.contextPath}/resources/images/icones/review.png"
                             width="24">
                    </a>
                    <div class="modal fade" id="modal_${loopStatus.index}" tabindex="-1" role="dialog"
                         aria-labelledby="modal1Label"
                         aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title " id="modal1Label">Évaluer livre : <span
                                            class="text-danger">${element.titre}</span></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/Livres/AddAvis" method="post">
                                        <c:forEach items="${catEvaluationList}" var="c">
                                            <div class="border d-flex align-items-center">
                                                <span class="itemTitre p-3">${c.description} :</span>
                                                <div class="container">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="${c.id}"
                                                               id="${c.id}_1" value="1" required>
                                                        <label class="form-check-label" for="${c.id}_1">1</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="${c.id}"
                                                               id="${c.id}_2" value="2" required>
                                                        <label class="form-check-label" for="${c.id}_2">2</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="${c.id}"
                                                               id="${c.id}_3" value="3" required>
                                                        <label class="form-check-label" for="${c.id}_3">3</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="${c.id}"
                                                               id="${c.id}_4" value="4" required>
                                                        <label class="form-check-label" for="${c.id}_4">4</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="${c.id}"
                                                               id="${c.id}_5" value="5" required>
                                                        <label class="form-check-label" for="${c.id}_5">5</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <br>
                                        </c:forEach>
                                        <div class="input-group mb-3">

                                            <textarea type="text" class="form-control" id="commentaire"
                                                      name="commentaire"
                                                      placeholder="Votre commentaire" rows="6"></textarea>
                                            <input type="hidden" name="login" value="${login}">
                                            <input type="hidden" name="idLivre" value="${element.id}">

                                        </div>
                                        <input type="submit" value="Enregistrer" class="btn btn-primary">
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                </c:if>

            </div>
        </c:forEach>

    </div>
</div>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<jsp:include page="Footer.jsp"></jsp:include>

