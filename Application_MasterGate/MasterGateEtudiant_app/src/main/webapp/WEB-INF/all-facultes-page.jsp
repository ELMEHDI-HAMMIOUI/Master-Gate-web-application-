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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagination-style.css">

   	  <!-- fonts -->
      <!-- Lato -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
      <!-- Montserra -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    	<link rel="icon" href="img/icons/iconeMG.svg" type="image/x-icon">
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

</style>


<body>




	<!-- 	add a pop up error msg -->
	<%@ include file="/includes/header.jspf" %>

    <main >
        <div class="faculty-list" id="faculty-list">
        	<c:forEach items="${facs }" var="fac">
	            <a class="faculty-card" href="faculte-masters?fac=${fac.id }">
	                <img src="${fac.logo}" alt="${fac.surnom } logo" >
	                <h2>${fac.surnom }</h2>
	                <span>${fac.nom }</span>
	                <p>${fac.uniName }</p>
	                
	            </a>
			</c:forEach>
			
        </div>
    </main>
    
    
     <form method="post" action="facultes" class="page-container">
     
        <section class="pagination">
	    
	    <!-- previous button -->
        <c:if test="${( currentPage - 1 ) > 0}">	    
		    <a href="facultes?page=${currentPage - 1}" class="page-link prev-page-link" title="Next page" type="submit" name="page" ${currentPage <= 1 ? 'disabled' : ''}>
			    <svg xmlns="http://www.w3.org/2000/svg" width="8.233" height="13.861" viewBox="0 -4.861 8.233 13.861" class="page-svg prev-svg">
				    <path d="M8.233 2.018L1.251 9 0 7.645l5.627-5.627L0-3.61l1.251-1.25 6.982 6.878z">
				    </path>
				</svg>
				Previous
			</a>
		</c:if>
		
		<input type="text" maxlength="${totalPages}" class="input-pagenum" autocomplete="off" name="page"  value="${currentPage}" > of 
	    <span class="total-pages-span">${totalPages}</span>
	
		<!-- next button -->
        <c:if test="${( currentPage + 1 ) <= totalPages}">	    
		    <a href="facultes?page=${currentPage + 1}" class="next-page-link page-link" title="Previous page" type="submit" name="page" ${currentPage >= totalPages ? 'disabled' : ''} id="test">
				NEXT
			    <svg xmlns="http://www.w3.org/2000/svg" width="8.233" height="13.861" viewBox="0 -4.861 8.233 13.861" class="page-svg next-svg">
				    <path d="M8.233 2.018L1.251 9 0 7.645l5.627-5.627L0-3.61l1.251-1.25 6.982 6.878z">
				    </path>
				</svg>
			</a>
		</c:if>
		
		</section>
	</form>

    
</body>
</html>