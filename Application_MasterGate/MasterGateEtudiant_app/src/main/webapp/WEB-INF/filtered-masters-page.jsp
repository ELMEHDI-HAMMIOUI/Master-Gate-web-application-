<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ page import="master.beans.*" %>
 <%@page import= "java.time.LocalDate"%>
 <%@page import="java.time.Period" %>
 <%@page import= "java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Masters</title>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagination-style.css">
	<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">

<%
	if( request.getSession().getAttribute("tag") == null ){
    	//storing the tag 
		  request.getSession().setAttribute("tag", "All");
	}

	if( request.getSession().getAttribute("tag") == null ){
		//storing the filter
		  request.getSession().setAttribute("filter", "default");
	}

%>
    
</head>
<body>

	
	<style>
	        .filter-sort {
            display: flex;
            justify-content: space-between;
            gap: 10px;
            align-items: center;
        }
        .filter-div, .sort-div{
            position: relative;
            display: flex;
        }
        .filter-div select , .sort-div select{
            padding: .5em;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            z-index: 10;

            background-color: white;
        }
        .filter-div select{
            left: 0;
        }
        .filter-sort-icon{
            display: block;
            padding: 8px 16px;
            border-radius: 35px;
        }
        .filter-sort-icon:hover{
            background-color: #f4f4f4;
            cursor: pointer;
        }
        
        .masters-subheader{
        	margin-top: 1em;
        	margin-bottom: 1em;
        	display: flex;
        	align-items: center;
        	gap: 3em;
        	width: 50%;
  			margin: 1em	 auto;
        }
        .nav-btn{
		padding: 0 16px;
		border-radius: 9999999px;
  		color: #0d0c22;
	  	font-size: 14px;
  		border: none;
  		padding: .5em 1em;
  		font-weight: 600;
  		background: none;
  		font-family: Lato;
        }
        .nav-btn:hover{
        	cursor: pointer;
        }
 		.selected{
        	background: #f8f7f4;
 		}
 		.masters-nav{
 			display: flex;
 			align-items: center;
 			justify-content: space-between;
 			gap: 1em;
 		}
	</style>
	
	
	<%@ include file="/includes/header.jspf" %>
<!-- 	subheader -->
		<form method="GET" action="filterMasterRedirect"  class="masters-subheader" id="filter-form">
		   <!--filter options, showed if user is logged in -->
			  <div class="filter-div">
                  <select name="filter" id="filter" class="">

                        <option id="default-option" value="default" 
                        <%--if the searched filter is masters, then it should be selected by default --%>   
		                   <c:if test="${sessionScope.filter == 'default' }">
		                     <c:out value="selected"></c:out>
		                   </c:if>                 
                        >Filter</option>
                        
                        <%--if the user is logged in --%>
           				<c:if test="${!empty sessionScope.userId }">
	                        <option id="noninscrit-option" value="noninscrit"
	                        <%--if the searched filter is facs, then it should be selected by default --%>
			                   <c:if test="${sessionScope.filter == 'noninscrit' }">
			                     <c:out value="selected"></c:out>
			                   </c:if>  
			                  
	                        >Non Inscrit</option>
             			</c:if>

                    </select>
                </div>

<!--                 navigations -->

			<section class="masters-nav">
			
                <input type="submit" name="tag" value="All" class="nav-btn 
					<%--if the selected tag is All then it's class will be selected --%>
                	<c:if test="${sessionScope.tag == 'All' }">
                		<c:out value="selected"></c:out>
                	</c:if>
                " >
                <input type="submit" name="tag" value="Not Expired" class="nav-btn
					<%--if the selected tag is 'Not Expired' then it's class will be selected --%>
                	<c:if test="${sessionScope.tag == 'Not Expired' }">
                		<c:out value="selected"></c:out>
                	</c:if>
                " >
                
                <input type="submit" name="tag" value="New" class="nav-btn
                	<c:if test="${sessionScope.tag == 'New' }">
                		<c:out value="selected"></c:out>
                	</c:if>
                " >
                
                <input type="submit" name="tag" value="Expired" class="nav-btn
                	<c:if test="${sessionScope.tag == 'Expired' }">
                		<c:out value="selected"></c:out>
                	</c:if>
                " >

<!--                 <input type="submit" value="new" class="nav-btn" >New</button> -->
<!--                 <input type="submit" value="expired" class="nav-btn" >Expired</button> -->
			</section>
                
		</form>

<!-- content -->
	<section class="t-container">
        <section class="t-main">
		<c:forEach var="mastercard" items="${mastercards}">
            <section  class="master-card">
                <!-- master card head -->
                <div class="master-card-head">
                    <span  class="specialite">${ mastercard.specialite }</span>
                    <div  class="fac">
                        <a href="${ mastercard.web_site_uni }" class="fac-logo link">
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
                
             
			<!--end of mastercard    -->
		</c:forEach>
    		
	  <!--end of t-main	 -->
      </section>
      
    
   
   </section>
   
   
   <script>
   		//when a select option (the filter) is selected then submit the form
   		document.getElementById("filter").addEventListener("change",()=>{
   			document.getElementById("filter-form").submit();
   		})
   </script>
    
    
</body>
</html> 