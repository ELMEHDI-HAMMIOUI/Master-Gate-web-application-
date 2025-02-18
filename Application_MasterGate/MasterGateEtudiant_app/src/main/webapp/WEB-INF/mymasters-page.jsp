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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/masters-page-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile-style.css">
    <!-- js -->
    <script src="${pageContext.request.contextPath}/js/profile-script.js" defer></script>
	<!-- datatable -->
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" ></script> 
		<link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css" />
  	<script src="https://cdn.datatables.net/2.0.8/js/dataTables.js" ></script>

</head>
<body>

	<%@ include file="/includes/header.jspf" %>
	
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
                    <a href="Mymasters" class="p-side-option selected">
                        <img src="img/icons/list.svg" alt="list">
                        <span>My Masters</span>
                    </a>
                    <a href="Notifications" class="p-side-option">
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
                    <button class="delete-btn btn" id="delete-btn"> Supprimer Mon Compte</button>
                </section>
            </section>
        <section class="p-main t-main">
	
		<!-- si l'etudiant n'est inscrit à aucun master -->
		<%--
	<c:if test="${empty mastercards }">
		<div class="empty-state-div">
		  <img src="img/empty-state.svg" alt="Empty state illustration">
		  <h4>Vous n'êtes inscrit à aucun master pour le moment</h4>
		</div>
	</c:if>
	

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
		</c:forEach>
    		--%>

   <!-- table style -->
   <style>
   #myTable {
    width: 100%;
    border-collapse: collapse;
    font-family: 'Lato', sans-serif;
    margin-top: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

#myTable thead {
background-color: #ffdf8a;
  color: black;
}

#myTable thead th {
    padding: 12px 15px;
    text-align: left;
    font-weight: 600;
    text-transform: uppercase;
}

#myTable tbody tr {
    border-bottom: 1px solid #ddd;
    transition: background-color 0.3s ease;
}

#myTable tbody tr:hover {
    background-color: #f1f1f1;
}

#myTable tbody td {
    padding: 12px 15px;
    text-align: center;
    color: #333;
}

#myTable tbody td a {
	color: black;
  text-decoration: underline;
  transition: color 0.3s ease;
  font-weight: 600;
}

#myTable tbody td a:hover {
    color: #4b4b4b;
}

#myTable .dataTables_wrapper .dataTables_paginate .paginate_button {
    background-color: #4CAF50;
    color: white !important;
    border: none;
    border-radius: 4px;
    padding: 6px 12px;
    margin: 2px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

#myTable .dataTables_wrapper .dataTables_paginate .paginate_button:hover {
    background-color: #388E3C;
}

#myTable .dataTables_wrapper .dataTables_length select,
#myTable .dataTables_wrapper .dataTables_filter input {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 6px;
    margin: 0 10px;
}

#myTable .dataTables_wrapper .dataTables_info {
    margin: 10px 0;
    font-size: 14px;
    color: #666;
}
   
   </style>
   
   <table id="myTable" class="cell-border compact stripe display" >
       <thead>
	   		<tr>
	   			<th>Master</th>
	   			<th>Date d'inscription</th>
	   			<th>Preselection</th>
	   			<th>Liste Finale</th>
	   			<th>Liste Attente</th>
	   			<th>Action</th>
	   		</tr>
   		</thead>
   		
   		<tbody>
	   		<!-- masters -->
		   <c:forEach items="${mboard }" var="mb">
	
	   		<tr>
	   			<td>${mb.masterName }</td>
	   			<td>${mb.dateInsc }</td>
	   			
	   			<%--preselection --%>
	   			<c:if test="${mb.pres_time_left < 0 }"><%--si la date de preselection est achevé--%>
	   				<td>${mb.ps ? '✅' : '❌' }</td><%--s'il est preselectioné ou pas --%>
	   			</c:if>
	   			<c:if test="${mb.pres_time_left > 0 }"><%--si la date de preselection n'est pas encore achevé--%>
	   				<td>${mb.pres_time_left } jours restants</td><%--temps restant pour la preselection --%>
	   			</c:if>
	   			<c:if test="${mb.pres_time_left == 0 }"><%--si la date de preselection est demain--%>
	   				<td>Demain</td><%--temps restant pour la preselection --%>
	   			</c:if>
	
	   			<%--liste finale --%>
	   			<c:if test="${mb.conc_time_left < 0 }"><%--si la date de concours est achevé--%>
	   				<td>${mb.lf ? '✅' : '❌' }</td><%--s'il est dans la liste finale ou pas --%>
	   			</c:if>
	   			<c:if test="${mb.conc_time_left > 0 }"><%--si la date de concours n'est pas encore achevé--%>
	   				<td>${mb.conc_time_left } jours restants</td><%--temps restant pour l'affiche des listes --%>
	   			</c:if>
	   			<c:if test="${mb.conc_time_left == 0 }"><%--si la date de concours est demain--%>
	   				<td>Demain</td><%--temps restant pour l'affiche des listes --%>
	   			</c:if>
	   			
	   			<%--liste attente --%>
	   			<c:if test="${mb.conc_time_left < 0 }"><%--si la date de concours est achevé--%>
	   				<td>${mb.la ? '✅' : '❌' }</td><%--s'il est dans la liste d'attente ou pas --%>
	   			</c:if>
	   			<c:if test="${mb.conc_time_left > 0 }"><%--si la date de concours n'est pas encore achevé--%>
	   				<td>${mb.conc_time_left } jours restants</td><%--temps restant pour l'affiche des listes --%>
	   			</c:if>
	   			<c:if test="${mb.conc_time_left == 0 }"><%--si la date de concours est demain--%>
	   				<td>Demain</td><%--temps restant pour l'affiche des listes --%>
	   			</c:if>
	   			
	   			<td>
	   				<a href="Inscription?idMaster=${mb.idMaster }">Consulter</a>
	   			</td>
	
	   		</tr>
	   		
		   </c:forEach>
		</tbody>
   </table>

   
         </section>
    
   </section>
  <!--   
   <style>
/*look at generic style*/


/*    .p-main{ */
/*    width: 70%; */
/*    height: fit-content; */
/*    } */
/* .empty-state-div { */
/*   display: flex; */
/*   justify-content: center; /* Center horizontally */ */
/*   align-items: center;     /* Center vertically */ */
/*   height: 70vh;            */
/*   flex-direction: column; */
/*   gap: 1em; */
/*   margin: auto; */

/* } */

/* /* Centering and sizing the SVG image */ */
/* .empty-state-div img { */
/*   width: 200px;   /* Set your desired width */ */
/*   height: 200px;  /* Set your desired height */ */
/*   max-width: 100%; /* Ensure it scales properly */ */
/* } */
   
/* .profile-div img, .profile-pop-info img{ */
/* 	object-fit: cover; */
/* } */
   </style>
   
	<script>
	let table = new DataTable('#myTable', {
	    responsive: true
	});
	</script>
	
	<script>
           // config options...
        
       //look at profile-script
       
       
//         // show the settings options
//         document.getElementById("settings-btn").addEventListener("click",(e)=>{
//             e.preventDefault();
//             document.querySelector(".settings-chevron").classList.toggle("chevron-down");
//             document.getElementById("control-btns").classList.toggle("hidden");
//         })

        
//         //accordion
//         document.querySelector(".p-main-section-title").addEventListener("click", (e)=>{
//         	e.target.parentNode.querySelectorAll(".p-info-wraper").forEach((wrap)=>{
//         		wrap.classList.toggle("hidden");
//         	})
//         })
        
    </script>
   -->
   	<%@ include file="/includes/confirm-delete-modal.jspf" %>
   
</body>
</html> 