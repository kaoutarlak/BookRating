<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Header.jsp"></jsp:include>

<div class="mb-5 justify-content-center divStat">

    <br>
    <section class="row m-5 border rounded">
        <div class="col-12 bg-light border-bottom">
            <h4 class="txtTotal m-2 ">Envoyer un e-mail aux membres</h4>
        </div>

        <div class="col-12 mx-auto pt-2">
            <form action="${pageContext.request.contextPath}/Admin/sendEmail" method="post">
                <div class="form-group row align-items-center">
                    <label for="membre" class="col-sm-2 col-form-label itemAuteurBest">À</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="membre" id="membre">
                            <option value="All">Tout les membres</option>
                            <c:forEach items="${membreMail}" var="mail">
                                <option value="${mail}">${mail}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="sujet" class="col-sm-2 col-form-label itemAuteurBest">Sujet</label>
                    <div class="col-sm-10">
                        <input type="text" name="sujet" id="sujet" class="form-control">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="message" class="col-sm-2 col-form-label itemAuteurBest">Message</label>
                    <div class="col-sm-10">
                        <textarea name="message" rows="6" class="form-control" id="message"></textarea>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-sm-10 offset-sm-2">
                        <button type="submit" class="btn btn-primary">Envoyer</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>


<jsp:include page="../Footer.jsp"></jsp:include>

