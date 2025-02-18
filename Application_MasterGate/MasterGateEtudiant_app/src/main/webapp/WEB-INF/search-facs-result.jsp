<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Facultes</title>
    <!-- css -->
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/master-inscription-style.css">
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">
    
    <!-- fonts -->
      <!-- Lato -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
      <!-- Montserra -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    
</head>



<style>
*{
	margin: 0;
}
    body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f9f9f9;
    color: #333;
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
/*     margin-bottom: 1rem; */
    object-fit: contain;
}

.faculty-card h2 {
    font-size: 1.5rem;
/*     margin: 0.5rem 0; */
}

.faculty-card p {
/*     margin: 0.5rem 0; */
    color: #666;
}

.faculty-card span {
    margin-bottom: 0.5rem ;
    font-size: .7rem;
    color: #666;
}


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

<body>


    


	<!-- 	add a pop up error msg -->
		<%@ include file="/includes/header.jspf" %>
		<%@ include file="/includes/search-bar.jspf" %>
		
	<h1 class="search-query">${param.search }</h1>
		
	<c:if test="${empty facs }">
		<div class="empty-state-div">
		  <img src="img/empty-state.svg" alt="Empty state illustration">
		  <h4>Aucune faculté ne correspond à votre recherche</h4>
		</div>
	</c:if>
	
    <main >
        <div class="faculty-list" id="faculty-list">
        	<c:forEach items="${facs }" var="fac">
	            <a class="faculty-card" href="faculte-masters?fac=${fac.id }">
	                <img src="${fac.logo }" alt="${fac.surnom } logo" >
	                <h2>${fac.surnom }</h2>
	                <span>${fac.nom }</span>
	                <p>${fac.uniName }</p>
	                
	            </a>
			</c:forEach>
			
        </div>
    </main>
    

    
</body>
</html>