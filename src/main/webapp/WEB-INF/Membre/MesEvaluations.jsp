<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp"></jsp:include>

<div class="mb-5 justify-content-center">
    <div class="detailLivre">
        <c:forEach var="avis" items="${avisEvaluationList}">
            <div class="row itemLivre">
                <div class="col-4">
                    <c:forEach items="${livres}" var="livre">
                        <c:if test="${avis.idLivre == livre.id}">
                            <c:if test="${livre.image.contains('/resources/images/')}">
                                <img src="${pageContext.request.contextPath}${livre.image}" width="200" height="250"/>
                            </c:if>
                            <c:if test="${!livre.image.contains('/resources/images/')}">
                                <img src="${livre.image}" width="200" height="250"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="col-8 ">
                    <div>
                        <span class="sDescription ">Date : ${avis.datePost}</span>
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
                    <br>
                    <a class="btn btn-primary sbtnModif" data-toggle="modal" data-target="#modalModif_${avis.id}">
                        <img src="${pageContext.request.contextPath}/resources/images/icones/modifier.png" width="24"
                             height="24"/>Modifier</a>
                        <%--My Modal pour Modifier Avis--%>
                    <div class="modal fade" id="modalModif_${avis.id}" tabindex="-1" role="dialog"
                         aria-labelledby="modalModifLabel"
                         aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title " id="modal1Label">Modifier mon évaluation</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/Evaluation/Alter/${avis.id}" method="post">
                                        <c:forEach items="${catEvaluationList}" var="c" varStatus="loopStatus">
                                            <div class="border d-flex align-items-center">
                                                <span class="itemTitre p-3">${c.description} :</span>
                                                <div class="container">
                                                    <div class="form-check form-check-inline">
                                                        <c:if test="${avis.evaluationList[loopStatus.index].note==1}">
                                                            <input class="form-check-input" type="radio" name="${c.id}"
                                                                   id="${c.id}_1" value="1" checked required>
                                                        </c:if>
                                                        <c:if test="${avis.evaluationList[loopStatus.index].note!=1}">
                                                            <input class="form-check-input" type="radio" name="${c.id}"
                                                                   id="${c.id}_1" value="1" required>
                                                        </c:if>
                                                        <label class="form-check-label" for="${c.id}_1">1</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <c:if test="${avis.evaluationList[loopStatus.index].note==2}">
                                                            <input class="form-check-input" type="radio" name="${c.id}"
                                                                   id="${c.id}_2" value="2" checked required>
                                                        </c:if>
                                                        <c:if test="${avis.evaluationList[loopStatus.index].note!=2}">
                                                            <input class="form-check-input" type="radio" name="${c.id}"
                                                                   id="${c.id}_2" value="2" required>
                                                        </c:if>
                                                        <label class="form-check-label" for="${c.id}_2">2</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <c:if test="${avis.evaluationList[loopStatus.index].note==3}">
                                                            <input class="form-check-input" type="radio" name="${c.id}"
                                                                   id="${c.id}_3" value="3" checked required>
                                                        </c:if>
                                                        <c:if test="${avis.evaluationList[loopStatus.index].note!=3}">
                                                            <input class="form-check-input" type="radio" name="${c.id}"
                                                                   id="${c.id}_3" value="3" required>
                                                        </c:if>
                                                        <label class="form-check-label" for="${c.id}_3">3</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <c:if test="${avis.evaluationList[loopStatus.index].note==4}">
                                                            <input class="form-check-input" type="radio" name="${c.id}"
                                                                   id="${c.id}_4" value="4" checked required>
                                                        </c:if>
                                                        <c:if test="${avis.evaluationList[loopStatus.index].note!=4}">
                                                            <input class="form-check-input" type="radio" name="${c.id}"
                                                                   id="${c.id}_4" value="4" required>
                                                        </c:if>
                                                        <label class="form-check-label" for="${c.id}_4">4</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <c:if test="${avis.evaluationList[loopStatus.index].note==5}">
                                                            <input class="form-check-input" type="radio" name="${c.id}"
                                                                   id="${c.id}_5" value="5" checked required>
                                                        </c:if>
                                                        <c:if test="${avis.evaluationList[loopStatus.index].note!=5}">
                                                            <input class="form-check-input" type="radio" name="${c.id}"
                                                                   id="${c.id}_5" value="5" required>
                                                        </c:if>
                                                        <label class="form-check-label" for="${c.id}_5">5</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <br>
                                        </c:forEach>
                                        <div class="input-group mb-3">

                                            <textarea type="text" class="form-control" id="commentaire"
                                                      name="commentaire"
                                                      placeholder="Votre commentaire"
                                                      rows="6">${avis.commentaire }</textarea>
                                                <%-- <input type="hidden" name="idAvis" value="${avis.id}">--%>
                                        </div>
                                        <input type="submit" value="Enregistrer" class="btn btn-primary">
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/Evaluation/SupprimerAvis/${avis.id}"
                       class="text-danger pointer pl-3"><img
                            src="${pageContext.request.contextPath}/resources/images/icones/supprimer.png" width="24"
                            height="24">&nbsp;Supprimer</a>
                    <span class="itemAuteurDetail sDateAvis">
                            (${avis.nbLikes})
                        <img class="mb-1" src="${pageContext.request.contextPath}/resources/images/icones/like.png"/>
                    </span>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


<jsp:include page="../Footer.jsp"></jsp:include>