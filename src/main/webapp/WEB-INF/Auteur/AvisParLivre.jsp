<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp"></jsp:include>

<div class="mb-5 justify-content-center">
    <div class="row detailLivre">
        <div class="col-4">
            <div>
                <div class="itemProduitDetail">
                    <c:if test='${(element.image).contains("/resources/images/")}'>
                        <img class="itemImgDetail" src="http://localhost:8080/BookRating_war_exploded${livreDetail.image}">
                    </c:if>
                    <c:if test='${not (element.image).contains("/resources/images/")}'>
                        <img class="itemImgDetail" src="${livreDetail.image}">
                    </c:if>
                    <p class="itemTitreDetail">&nbsp;${livreDetail.titre}</p>
                    <p class="itemAuteurDetail">&nbsp;${livreDetail.nomAuteur}</p>
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
            <c:forEach var="avis" items="${listAvis}">
                <div class="itemProduitDetail2">
                    <div>
                        <span class="itemAuteurDetail">${avis.login}</span>
                        <span class="sDescription sDateAvis">Date : ${avis.datePost}</span>
                    </div>
                    <br>
                    <div class="row text-center">
                        <c:forEach var="evaluation" items="${avis.evaluationList}">
                            <c:forEach var="c" items="${catEvaluationList}">
                                <c:if test="${c.id==evaluation.idCategorieEvaluation}">
                                    <div class="col-md-3">
                                        <span class="itemTitreDetail">${c.description} :</span>
                                        <span class="text-danger ml-2">${evaluation.note}/5</span>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </div>
                    <br>
                    <p class="text-info">${avis.commentaire}</p>
                    <a class="btn btn-primary sbtnLikeSignal">Aimer</a>
                    <a class="btn btn-secondary sbtnLikeSignal" data-toggle="modal" data-target="#modal2">Signaler</a>
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
                </div>

            </c:forEach>

        </div>

    </div>

</div>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<jsp:include page="../Footer.jsp"></jsp:include>