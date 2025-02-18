<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notifications</title>
    
    
	<!--css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">
    
    <!-- js -->
    <script src="${pageContext.request.contextPath}/js/profile-script.js" defer></script>

    	
      <!-- Lato -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
      <!-- Montserra -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    
    <style>
        :root{
            --pr-clr-l: #FFDF8A;
            --scnd-clr-l: #FFEFCA; 
            --black: #121211;
            --black-75: rgba(18,18, 17, 0.75);
            --black-50: rgba(18, 18, 17, 0.50);
            --input-back: #FFF9EB;
            --search-bar-back: white;
            --thrd-clr-l: #272F58;
            --frd-clr-l: #B97030; 
            --grey:#E1E1E1;
            --alert-clr: #f05361;
        }
        a img{
        	position: relative;
        	z-index: -1;
        }
        a {
        	z-index: 500;
        }
        .profile-pop {
        z-index: 2000;
        }
        .nav{
        	width: auto;
        }
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            padding: 20px;
        }
        .tab {
            display: flex;
            border: 1px solid #f4f4f6;
            overflow-x: auto;
            padding: .3em;
            background-color: #fafafa;
            border-radius: 9px;
        }
        .tab button {
            background: none;
            border: none;
            font-size: 18px;
			padding: 1em 2em;
            cursor: pointer;
            transition: background 0.3s, color 0.3s;
            border-radius: 9px;
            border-radius: 9px;
            color: #828289;
        }
        .tab button.active {
            background-color: white;
            color: var(--frd-clr-l);
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .tab-content {
            display: none;
            padding: 20px 0;
        }
        .tab-content.active {
            display: block;
        }
        .notification {
            display: flex;
            align-items: center;
            padding: 15px;
            border-bottom: 1px solid #eee;
            gap: .2em;
            border-radius: 10px;
            margin-bottom: .5em;
        }
        .readed-n{
        	background-color: #FFC79547;
        }
        .notification:last-child {
            border-bottom: none;
        }
        .notification-icon {
            font-size: 24px;
            margin-right: 15px;
            color: var(--frd-clr-l);
        }
        .notification-content {
            flex-grow: 1;
        }
        .notification-content p {
            margin: 0;
            font-size: 16px;
            margin-bottom: .2em;
        }
        .notification-content span {
            display: block;
            font-size: 12px;
            color: #888;
        }
        .notification-button {
            background-color: var(--frd-clr-l);
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            transition: background 0.3s;
            text-decoration: none;
        }
        .notification-button:hover {
            background-color:#a15b1e;
        }
            .empty-state-div {
			  display: flex;
			  justify-content: center; /* Center horizontally */
			  align-items: center;     /* Center vertically */
/* 			  height: 70vh;            */
			  flex-direction: column;
			  gap: 1em;
			  width: 67%;
			  margin: 1em auto auto auto;
			  text-align: center;
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
			
        /* Media Query for Responsive Layout */
        @media screen and (max-width: 600px) {
            .container {
                padding: 10px;
            }
            .tab button {
                font-size: 16px;
                padding: 8px 12px;
            }
            .notification {
                padding: 10px;
            }
            .notification-icon {
                font-size: 20px;
                margin-right: 10px;
            }
            .notification-content p {
                font-size: 14px;
            }
            .notification-content span {
                font-size: 10px;
            }

        }
        
           .p-main{
   width: 70%;
   height: fit-content;
   }
   
   .container{
   	  margin: 0;
   }
   body{
   	background-color: white;
   }
   
/*    tag-notif-number-badge */
   .tab-btn{
		position: relative;
   }
   
.badge-ntf {
  position: absolute;
  top: .5em;
  right: .5em;
  padding: .3em .7em;
  font-size: 11px;
  border-radius: 6px;
  background-color: #ededed;
  font-weight: 700;
  color: gray;
}
   
   
   
    </style>
</head>
<body>

	<%@ include file="/includes/header.jspf" %>

<%--penser à voir la servlet pour plus d'info sur l'ajout d'un autre tag --%>

	
	    <section class="p-container">
            <section class="p-side">
                <div class="p-side-photo-div">
                    <!-- profile image link -->
                    <img src="ImgDisplayer?etudiantId=${sessionScope.userId }" alt="profile-pic">
 
                </div>
                <!-- error message -->
                <div class="error-div hidden"></div>
                <!-- side bar link options  -->
                <div class="p-side-options">
                    <a href="Profile" class="p-side-option ">
                        <img src="img/icons/user-circle.svg" alt="user-circle">
                        <span>Profile</span>
                    </a>
                    <a href="Mymasters" class="p-side-option ">
                        <img src="img/icons/list.svg" alt="list">
                        <span>My Masters</span>
                    </a>
                    <a href="Notifications" class="p-side-option selected">
                        <img src="img/icons/bell.svg" alt="bell">
                        <span>Notifications</span>
                    </a>

                    <button class="p-side-option settings-btn" id="settings-btn">
                        <img src="img/icons/settings.svg" alt="gear" class="settings-icon">
                        <span>Paramètres</span>
                        <img src="img/icons/chevron-down.svg" alt="chevron" class="settings-chevron">
                    </button>
                </div>
                <!-- control btns -->

                <section class="control-btns hidden" id="control-btns">
                    <button class="edit-btn btn">Modifier Mes Informations</button>
                    <button class="delete-btn btn" id="delete-btn">Supprimer Mon Compte</button>
                </section>
            </section>
        <section class="p-main t-main">




<div class="container">
    <div class="tab">
        <button class="active tab-btn" onclick="openTab(event, 'all')">All
        <%--nombre de notifications total --%>
	    <% 
			String token =(String) session.getAttribute("userId");
			int userId = OraFactory.getUserDao().getEtudiantIdFromToken(token);
			request.setAttribute("notifNum",OraFactory.getNotifDao().getNotReadedNotifNum( userId ) );             
		%>
	                
			<c:if test="${notifNum > 0}">
		    	<span class="badge-ntf">
		        ${notifNum }
		        </span> 
	        </c:if>
        </button>
        
        <button class="tab-btn" onclick="openTab(event, 'inscription')">Inscription
            <%--nombre de notifications pour le tag d'inscription --%>
			<c:if test="${InscriptionNotifsNotReadedNum > 0}">
		    	<span class="badge-ntf">
		        ${InscriptionNotifsNotReadedNum }
		        </span> 
	        </c:if>
        </button>
        
        <button class="tab-btn" onclick="openTab(event, 'preselection')">Preselection
            <%--nombre de notifications pour le tag de preselection --%>
			<c:if test="${PreselectionNotifsNotReadedNum > 0}">
		    	<span class="badge-ntf">
		        ${PreselectionNotifsNotReadedNum }
		        </span> 
	        </c:if>
        </button>
        <button class="tab-btn" onclick="openTab(event, 'concours')">Concours
            <%--nombre de notifications pour le tag de concours --%>
			<c:if test="${ConcoursNotifsNotReadedNum > 0}">
		    	<span class="badge-ntf">
		        ${ConcoursNotifsNotReadedNum }
		        </span> 
	        </c:if>
        </button>
        
    </div>
    
    
    <div id="all" class="tab-content active">
    
    	<!-- si aucune notification n'est presente -->
		<c:if test="${empty allNotifs }">
			<div class="empty-state-div">
			  <img src="img/no-notif-img.svg" alt="Empty state illustration">
			  <h4>Vous n'avez aucune notification pour le moment. Profitez-en pour explorer nos programmes de masters.</h4>
			</div>
		</c:if>
	
        <!-- notification 1 -->
        <c:forEach var="notif" items="${allNotifs }">
	        <div class="notification ${notif.readed == false ? 'readed-n':'' }" >
	            <div class="notification-icon">📢</div>
	            <div class="notification-content">
	                <p >${notif.msg }</p>
	                <span>${notif.notifDate }</span>
	            </div>
	            <c:if test="${!empty notif.idMaster}">
	            	<a href="Inscription?idMaster=${notif.idMaster}" class="notification-button">Détails</a>
	            </c:if>
	        </div>
	        <!-- Add more notifications here -->
        </c:forEach>
    </div>

    <div id="inscription" class="tab-content">
    
    	<!-- si aucune notification n'est presente -->
		<c:if test="${empty InscriptionNotifs }">
			<div class="empty-state-div">
			  <img src="img/no-notif-img.svg" alt="Empty state illustration">
			  <h4>Vous n'avez aucune notification pour le moment. Profitez-en pour explorer nos programmes de masters.</h4>
			</div>
		</c:if>
	
        <!-- notification 1 -->
        <c:forEach var="notif" items="${InscriptionNotifs }">
	        <div class="notification ${notif.readed == false ? 'readed-n':'' }" >
	            <div class="notification-icon">📋</div>
	            <div class="notification-content">
	                <p >${notif.msg }</p>
	                <span>${notif.notifDate }</span>
	            </div>
	            <a href="Inscription?idMaster=${notif.idMaster}" class="notification-button">Détails</a>
	        </div>
	        <!-- Add more notifications here -->
        </c:forEach>
    </div>

    <div id="preselection" class="tab-content">
    
        <!-- si aucune notification n'est presente -->
		<c:if test="${empty PreselectionNotifs }">
			<div class="empty-state-div">
			  <img src="img/no-notif-img.svg" alt="Empty state illustration">
			  <h4>Vous n'avez aucune notification pour le moment. Profitez-en pour explorer nos programmes de masters.</h4>
			</div>
		</c:if>
		
        <!-- notification 1 -->
		<c:forEach var="notif" items="${PreselectionNotifs }">
	        <div class="notification ${notif.readed == false ? 'readed-n':'' }" >
	            <div class="notification-icon">🔍</div>
	            <div class="notification-content">
	                <p>${notif.msg }</p>
	                <span>${notif.notifDate }</span>
	            </div>
	            <a href="Inscription?idMaster=${notif.idMaster}" class="notification-button">Détails</a>
	        </div>
	    </c:forEach>
        <!-- Add more notifications here -->
    </div>

    <div id="concours" class="tab-content">
    
        <!-- si aucune notification n'est presente -->
		<c:if test="${empty ConcoursNotifs }">
			<div class="empty-state-div">
			  <img src="img/no-notif-img.svg" alt="Empty state illustration">
			  <h4>Vous n'avez aucune notification pour le moment. Profitez-en pour explorer nos programmes de masters.</h4>
			</div>
		</c:if>
		
        <!-- notification 1 -->
    	<c:forEach var="notif" items="${ConcoursNotifs }">
	        <div class="notification ${notif.readed == false ? 'readed-n':'' }" >
	            <div class="notification-icon">🏆</div>
	            <div class="notification-content">
	                <p>${notif.msg }</p>
	                <span>${notif.notifDate }</span>
	            </div>
	            <a href="Inscription?idMaster=${notif.idMaster}" class="notification-button">Détails</a>
	        </div>
		</c:forEach>
        <!-- Add more notifications here -->
    </div>
</div>

	</section>
</section>

<script>
    function openTab(event, tabName) {
        const tabcontent = document.getElementsByClassName("tab-content");
        for (let i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        const tablinks = document.getElementsByClassName("tab-btn");
        for (let i = 0; i < tablinks.length; i++) {
            tablinks[i].classList.remove("active")
        }
        document.getElementById(tabName).style.display = "block";
        event.currentTarget.classList.add("active");
    }
</script>


	<%@ include file="/includes/confirm-delete-modal.jspf" %>

</body>
</html>
 