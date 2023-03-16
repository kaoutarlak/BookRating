<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${login == ''}">

    <jsp:include page="Visiteur/Header.jsp">
        <jsp:param name="catLivreList" value="${catLivreList}"/>
    </jsp:include>

    <jsp:include page="Visiteur/Header.jsp"></jsp:include>

    <jsp:include page="Visiteur/Header.jsp"></jsp:include>

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
<section class="mainPrincipal">
    <div id="myCarousel" class="carousel slide carousel-fade" data-ride="carousel">

        <!-- Indicators -->
        <div class="carousel-indicators">
            <button type="button" data-target="#myCarousel" data-slide-to="0" class="style-indicateur"></button>
            <button type="button" data-target="#myCarousel" data-slide-to="1" class="style-indicateur"></button>
            <button type="button" data-target="#myCarousel" data-slide-to="2" class="style-indicateur active"></button>
            <button type="button" data-target="#myCarousel" data-slide-to="3" class="style-indicateur"></button>
            <button type="button" data-target="#myCarousel" data-slide-to="4" class="style-indicateur"></button>
            <button type="button" data-target="#myCarousel" data-slide-to="5" class="style-indicateur"></button>
            <button type="button" data-target="#myCarousel" data-slide-to="6" class="style-indicateur"></button>
            <button type="button" data-target="#myCarousel" data-slide-to="7" class="style-indicateur"></button>
            <button type="button" data-target="#myCarousel" data-slide-to="8" class="style-indicateur"></button>
            <button type="button" data-target="#myCarousel" data-slide-to="9" class="style-indicateur"></button>
        </div>

        <!-- The slideshow -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/resources/images/Carousel/Slide-1.png">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/Carousel/Slide-2.png">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/Carousel/Slide-3.png">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/Carousel/Slide-4.png">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/Carousel/Slide-5.png">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/Carousel/Slide-6.png">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/Carousel/Slide-7.png">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/Carousel/Slide-8.png">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/Carousel/Slide-9.png">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/Carousel/Slide-10.png">
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>
</section>

<jsp:include page="Footer.jsp"></jsp:include>
