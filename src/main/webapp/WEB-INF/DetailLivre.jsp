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

<div class="mb-5 justify-content-center">
    <div class="row detailLivre">
        <div class="col-4">
            <div>
                <div class="itemProduitDetail">
                    <c:if test='${(livreDetail.image).contains("/resources/images/")}'>
                        <img class="itemImgDetail" src="${pageContext.request.contextPath}${livreDetail.image}"/>
                    </c:if>
                    <c:if test='${not (livreDetail.image).contains("/resources/images/")}'>
                        <img class="itemImgDetail" src="${livreDetail.image}"/>
                    </c:if>

                    <c:if test="${role=='membre'}">
                        <a href="" class="btn text-white itemBtnDetail" style="margin-right: 66px;"
                           onclick="addLivreLu('${login}', '${livreDetail.id}')">&nbsp;&nbsp;Livre lu
                            <img src="${pageContext.request.contextPath}/resources/images/icones/livre-lu.png"
                                 width="24">
                        </a>
                        <a href="#" class="btn text-white itemBtnDetail" data-toggle="modal"
                           data-target="#modal1">&nbsp;Évaluer
                            <img src="${pageContext.request.contextPath}/resources/images/icones/review.png"
                                 width="24">
                        </a>
                        <div class="modal fade" id="modal1" tabindex="-1" role="dialog"
                             aria-labelledby="modal1Label"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title " id="modal1Label">Évaluer livre : <span
                                                class="text-danger">${livreDetail.titre}</span></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${pageContext.request.contextPath}/Evaluation/Add" method="post">
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
                                                <input type="hidden" name="idLivre" value="${livreDetail.id}">

                                            </div>
                                            <input type="submit" value="Enregistrer" class="btn btn-primary">
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${role=='auteur'}">
                        <a href="" class="btn text-white btn-success" style="width: 97%;"
                           data-toggle="modal" data-target="#modal3">
                            <img src="${pageContext.request.contextPath}/resources/images/icones/icons8-pencil-24.png"
                                 width="24">&nbsp;Demande gestion
                        </a>
                        <div class="modal fade" id="modal3" tabindex="-1" role="dialog"
                             aria-labelledby="modal3Label"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title " id="modal3Label">Demander la gestion de :
                                            <span class="itemAuteurDetail">&nbsp;${livreDetail.titre}</span></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${pageContext.request.contextPath}/Auteur/DemandeGestion"
                                              method="post">
                                            <div class="input-group mb-3">
                                            <textarea type="text" class="form-control" name="message"
                                                      placeholder="Votre message" rows="6"></textarea>
                                            </div>
                                            <input type="hidden" name="loginAuteur" value="${login}">
                                            <input type="hidden" name="idLivre" value="${livreDetail.id}">
                                            <input type="submit" value="Envoyer" class="btn btn-primary">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <br/>
                    <br/>
                    <table class="ml-2">
                        <tr>
                            <th>
                                <span class="mr-1 text-white bg-danger p-1 rounded">${moyenneNote}</span>
                                <img src="${pageContext.request.contextPath}/resources/images/icones/${moyenneNote.substring(0, 1)}etoile.png"
                                     class="mr-4">
                            </th>
                            <th><span class="ml-4 itemAuteurDetail sUnderline">${nombreAvis} : Avis</span></th>
                        </tr>
                        <tr class="spacer">
                            <td colspan="2"></td>
                        </tr>
                        <tr class="spacer">
                            <td colspan="2"></td>
                        </tr>
                        <tr class="spacer">
                            <td colspan="2"></td>
                        </tr>
                        <tr class="spacer">
                            <td colspan="2"></td>
                        </tr>
                        <tr class="spacer">
                            <td colspan="2"></td>
                        </tr>
                        <tr class="spacer">
                            <td colspan="2"></td>
                        </tr>
                        <c:forEach items="${moyenneEvaluation.entrySet()}" var="moyenneE">
                            <tr class="border-bottom">
                                <td><span class="itemTitreDetail mr-5">${moyenneE.key} : </span></td>
                                <td><span class="text-danger ml-5">${moyenneE.value} </span></td>
                            </tr>
                            <tr class="spacer">
                                <td colspan="2"></td>
                            </tr>
                            <tr class="spacer">
                                <td colspan="2"></td>
                            </tr>
                            <tr class="spacer">
                                <td colspan="2"></td>
                            </tr>
                            <tr class="spacer">
                                <td colspan="2"></td>
                            </tr>
                            <tr class="spacer">
                                <td colspan="2"></td>
                            </tr>
                            <tr class="spacer">
                                <td colspan="2"></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-8 ">
            <div class="itemProduitDetail2">
                <div>
                    <span class="itemTitreDetail">Titre : </span>
                    <span class="itemAuteurDetail">&nbsp;${livreDetail.titre}</span>
                </div>
                <br/>
                <div>
                    <span class="itemTitreDetail">Auteur : </span>
                    <span class="itemAuteurDetail">&nbsp;${livreDetail.nomAuteur}</span>
                </div>
                <br/>
                <div>
                    <p class="itemTitreDetail text-decoration-underline">Description :</p>
                    <p class="sDescription">${livreDetail.description}</p>
                </div>
            </div>
            <c:forEach var="avis" items="${listAvis}">
                <div class="itemProduitDetail2">
                    <div>
                        <span class="itemAuteurDetail">${avis.login}</span>
                        <span class="sDescription sDateAvis">Date : ${avis.datePost}</span>
                    </div>
                    <br>
                    <p class="text-info">${avis.commentaire}</p>
                    <c:if test="${role=='auteur' || role=='membre'}">
                        <a class="btn btn-primary sbtnLikeSignal">Aimer</a>
                        <a class="btn btn-secondary sbtnLikeSignal" data-toggle="modal"
                           data-target="#modal2">Signaler</a>


                        <div class="modal fade" id="modal2" tabindex="-1" role="dialog"
                             aria-labelledby="modal2Label"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title " id="modal2Label">Signaler un commentaire :</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${pageContext.request.contextPath}/Evaluation/Signaler/${avis.idLivre}"
                                              method="post">
                                            <div class="input-group mb-3">
                                            <textarea type="text" class="form-control" name="message"
                                                      placeholder="Votre message" rows="6"></textarea>
                                            </div>
                                            <input type="hidden" name="login" value="${login}">
                                            <input type="hidden" name="idAvis" value="${avis.id}">
                                            <input type="submit" value="Envoyer" class="btn btn-primary">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <span class="itemAuteurDetail sDateAvis">
                            (${avis.nbLikes})
                        <img class="mb-1" src="${pageContext.request.contextPath}/resources/images/icones/like.png">
                    </span>
                    </c:if>
                </div>

            </c:forEach>

        </div>

    </div>

</div>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<jsp:include page="Footer.jsp"></jsp:include>