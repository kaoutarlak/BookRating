<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="Visiteur/Header.jsp"></jsp:include>

<!-- Corps de la page -->
<div class="container-fluid mt-5 ">
    <div class="row divConnexion">
        <div class="col-md-12 ">
            <div class="text-center">
                <br/>
                <img src="${pageContext.request.contextPath}/resources/images/welcome.png" width="" >
                <p class="titreFrame">Inscription bien confirmé, bienvenue dans votre site BookRating</p>

            </div>
        </div>
    </div>
</div>

<jsp:include page="Footer.jsp"></jsp:include>
