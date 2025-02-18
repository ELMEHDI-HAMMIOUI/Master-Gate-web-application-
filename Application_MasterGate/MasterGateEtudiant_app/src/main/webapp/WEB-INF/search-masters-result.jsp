<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>My Masters</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
    <!-- Montserra -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <!-- css -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/masters-page-style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">
    
</head>
<body>

	<%@ include file="/includes/header.jspf" %>
	<%@ include file="/includes/search-bar.jspf" %>

	<h1 class="search-query">${param.search }</h1>
	
	
	<c:if test="${empty mastercards }">
		<div class="empty-state-div">
		  <img src="img/empty-state.svg" alt="Empty state illustration">
		  <h4>Votre mot de recherche ne correspond à aucun master</h4>
		</div>
	</c:if>
	
	<section class="t-container">
        <section class="t-main">
		<c:forEach var="mastercard" items="${mastercards}">
            <section  class="master-card">
                <!-- master card head -->
                <div class="master-card-head">
                    <span  class="specialite">${ mastercard.specialite }</span>
                    <div  class="fac">
                        <a href="${ mastercard.web_site_uni }" class="fac-logo">
                            <img src="${mastercard.logo_uni}" alt="logo">
                        </a>
                        <span  class="fac-nom"></span>
                    </div>
                </div>
                <!-- master card body -->
                <div class="master-card-body">
                    <h5 class="master-card-body-title">Master
                        <span class="master-card-body-title_facSurnom">${mastercard.surnom_fac }</span>
                        <span class="master-card-body-title_ville">${mastercard.ville }</span>
                        <span class="master-card-body-title_date"></span>
                    </h5>
                </div>
                <!-- master card footer -->
                <div class="master-card-footer">
<%--                 	<a href="ResponsableMasterInformation?id_master=${ mastercard.id_master }"> --%>
<!-- 	                    <button class="consulter-master"> -->
<!-- 	                        <span class="consulter-master_txt">Consulter</span> -->
<!-- 	                    </button> -->
	                    <a href="Inscription?idMaster=${ mastercard.id_master }" class="consulter-master">
	                        <span class="consulter-master_txt">Consulter</span>
	                    </a>
<!--                     </a> -->
                    <section class="master-card-footer-meta">
                        <div class="master-card-footer-ville-div">
                            <img src="img/icons/location-icon.svg" alt="ville" class="master-card-footer-ville_icon">
                            <span  class="master-card-footer-ville_txt">${ mastercard.ville}</span>
                        </div>
                        <div class="master-card-footer-date-div">
                            <img src="img/icons/date-icon.svg" alt="date" class="master-card-footer-date_icon">
                            <span class="master-card-footer-date_txt">Termine le ${ mastercard.d_fin_inscription }</span>
                        </div>
                    </section>
                </div>
                
				<!-- expiration badge -->
                <c:if test="${mastercard.expired}">
                	<span class="expire-badge badge">Expired</span>
                </c:if>
                
            </section>
		</c:forEach>
    		
      </section>
    
   </section>
   <style>
   
.empty-state-div {
  display: flex;
  justify-content: center; /* Center horizontally */
  align-items: center;     /* Center vertically */
  height: 70vh;           
  flex-direction: column;
  gap: 1em;

}

/* Centering and sizing the SVG image */
.empty-state-div img {
  width: 200px;   /* Set your desired width */
  height: 200px;  /* Set your desired height */
  max-width: 100%; /* Ensure it scales properly */
}

.profile-div img, .profile-pop-info img{
	object-fit: cover;
}
   
   </style>
   
   
   
   	<%@ include file="/includes/loading.jspf" %>
   
</body>
</html> 