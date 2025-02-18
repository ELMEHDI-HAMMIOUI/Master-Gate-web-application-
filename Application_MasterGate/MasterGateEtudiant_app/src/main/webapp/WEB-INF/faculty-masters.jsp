<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${fac.surnom } Masters</title>
    <!-- css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/master-inscription-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/masters-page-style.css">
    
    	
    <!-- fonts -->
      <!-- Lato -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
      <!-- Montserra -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
      <link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
    
</head>

<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f9f9f9;
    color: #333;
}

header {
	background-color: #e8e8e8;
    color: var(--black-75);
    text-align: center;
    padding: 1rem 1rem;
    display: flex;
    flex-direction: column;
    align-items: center;
}

header img {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin-bottom: 1rem;
    object-fit: contain;
}
header h1{
	font-size: 3rem;
	margin-bottom: .2em;
}
main {
    padding: 2rem;
}

.faculty-list {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
    justify-content: center;
}

.faculty-card {
    background-color: white;
    border: 1px solid #ddd;
    border-radius: 8px;
    width: 250px;
    padding: 1rem;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    text-align: center;
    text-decoration: none;
    color: inherit;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.faculty-card img {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin-bottom: 1rem;
    object-fit: cover;
}

.faculty-card h2 {
    font-size: 1.5rem;
    margin: 0.5rem 0;
}

.faculty-card p {
    margin: 0.5rem 0;
    color: #666;
}

footer {
    text-align: center;
    padding: 1rem 0;
    background-color: #4CAF50;
    color: white;
}


.empty-state-div {
  display: flex;
  justify-content: center; /* Center horizontally */
  align-items: center;     /* Center vertically */
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


<body>


    	
	<%@ include file="/includes/header.jspf" %>
	<!-- 	add a pop up error msg -->
	
    <header>
        <img src="${fac.logo }" alt="${fac.surnom }logo">
        <h1>${fac.nom }</h1>
        <p>${fac.uniName }</p>
    </header>
    <main>
        <div class="faculty-details">
            <!-- Additional faculty details can be added here -->
            
    <c:if test="${empty mastercards }">
		<div class="empty-state-div">
		  <img src="img/empty-state.svg" alt="Empty state illustration">
		  <h4>Cette faculté n'a publié aucun master juste à present</h4>
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
                            <img src="${mastercard.logo_uni}" alt="logo" >
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

	                    <a href="Inscription?idMaster=${ mastercard.id_master }" class="consulter-master link">
	                       Consulter
	                    </a>
	                    
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
                
				<!-- new badge -->
                <c:if test="${mastercard.newMaster}">
                	<span class="new-badge badge">New</span>
                </c:if>
                

            </section>
		</c:forEach>
    		
      </section>
    
   </section>
            
        </div>
    </main>
    
       
    
</body>
</html>