<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
    <!-- Montserra -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">

	<link rel="stylesheet" href="css/RespoHome.css">
	<style>
		.hd-new-MSTR{
			margin-left: 22px;
		}
		.add-master-btn {
		    background-color: #FFDF8A;
		    color: #121D30;
		    padding: 15px;
		    border: none;
		    border-radius: 4px;
		    cursor: pointer;
		    font-size: 18px;
		    transition: background-color 0.3s, transform 0.3s;
		    margin-top: 10px;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    flex-direction: row;
		    min-width: 243px;
		    gap: 0.7em;
		    font-family: system-ui;
		}
		.add-master-btn:hover {
		    background-color: #efc34e;
		    transform: translateY(-2px);
		}
		.welcom-container{
			      display: flex;
	    flex-direction: column;
	    justify-content: center;
	    align-items: center;
	    width: 90%;
	    margin: 0 auto;
	    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
		}
		.welcom-container span{
		    font-size: x-large;
		    color: #3e5c91;
		    font-family: lato;
		    align-content: center;
		    
		}
	</style>
</head>
<body>
<%@ include file="/WEB-INF/RespoHeader.jsp" %>
	<div class="hd-new-MSTR">
	   <a href="ResponsableAddMaster" ><button class="add-master-btn" >Nouveaux Master<img src="${pageContext.request.contextPath}/img/icons/add-icon.svg" style="height:20px;"></button></a>
		
	</div>
	<section class="t-container">

        <section class="t-main">
       
		<c:forEach var="mastercard" items="${mastercards}">
            <section  class="master-card">
                <!-- master card head -->
                <div class="master-card-head">
                    <span  class="specialite">${ mastercard.nom_coordinateur }</span>
                    <div  class="fac">
                        <a href="${ mastercard.web_site_uni }" class="fac-logo">
                            <img src="${mastercard.logo_uni}" alt="logo">
                        </a>
                        <span  class="fac-nom"></span>
                    </div>
                </div>
                <!-- master card body -->
                <div class="master-card-body">
                    <h5 class="master-card-body-title">${ mastercard.specialite }
                        <span class="master-card-body-title_facSurnom"></span>
                        <span class="master-card-body-title_ville"></span>
                        <span class="master-card-body-title_date"></span>
                    </h5>
                </div>
                <!-- master card footer -->
                <div class="master-card-footer">
                	<form action="ResponsableMasterInformation" method="post">
            			<input type="hidden" name="id_master" value="${ mastercard.id_master }">
			    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	                    <button class="consulter-master" type="submit">
	                        <span class="consulter-master_txt">Consulter</span>
	                    </button>
                    </form>
                    <section class="master-card-footer-meta">
                        <div class="master-card-footer-ville-div">
                            <img src="img/icons/location-icon.svg" alt="ville" class="master-card-footer-ville_icon">
                            <span  class="master-card-footer-ville_txt">${ mastercard.ville}</span>
                        </div>
                        <div class="master-card-footer-date-div">
                            <img src="img/icons/date-icon.svg" alt="date" class="master-card-footer-date_icon">
                            <span class="master-card-footer-date_txt">publier le ${ mastercard.d_debut_inscription }</span>
                        </div>
                    </section>
                </div>
            </section>
		</c:forEach>
      </section>
   </section>
    <c:if test="${ empty mastercards }">
        <div class="welcom-container">
        	<span>Bienvenue sur notre plateforme. Souhaitez-vous publier votre premier programme de master ?</span>
        	<img style="width: 100%;height: 450px;"src="${pageContext.request.contextPath}/img/icons/welcome_page.svg">
       	</div>
        </c:if>
</body>
</html> 