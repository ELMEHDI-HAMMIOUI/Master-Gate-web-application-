<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	cette page permet de traiter les etudiant préselctionées dans ke cas d'erreur par exemple accepter la condidature d'un etudiant par erreur 
	le responssable peut le suprimer
 -->
<!DOCTYPE html>
<html lang="fr" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Étudiants préselectionnées</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/data_table/dataTables.dataTables.css">
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ListCandidats.css">
	<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
</head>
<body>
<%@ include file="/WEB-INF/RespoHeader.jsp" %>
<%@ include file="/WEB-INF/RespoSubNav.jsp" %>

<div class="container">
		<div class="header-page">
				<c:if test="${ not empty etudiantsPreselectionnes}">
				<div class="GrandTitre"> 
				<span class="titre">Liste des étudiants présélectionnés pour passer le concours</span>
				<span class="sousTitre">Dès que vous avez terminé l'étude des dossiers, veuillez publier les résultats de la présélection .</span>	
				</div>
				<form action="PublierPreselection" method="post">
			        <input type="hidden" name="id_master" value="${id_master}">
			        <button type="submit" class="main-btn">Fiche de publication </button>
			        <input type="text" id="DatePub" value="${master.d_aff_preselection}" hidden>		        
				</form>		
				</c:if>	
				<c:if test="${  empty etudiantsPreselectionnes}">
					<div class="GrandTitre"> 
					<span class="titre">Veuillez noter que vous n'avez pas encore pré-sélectionné aucun étudiant.</span>
					<span class="sousTitre"> Merci de compléter cette étape pour continuer le traitement des candidatures</span>	
					</div>				
				</c:if>
				
				
		</div>
		<c:if test="${ not empty etudiantsPreselectionnes}">
		
	    <table id="example" class="styled-table" style="width:100%">
	        <thead>
	            <tr>
	                <th>CNE</th>
	                <th>CNI</th>
	                <th>NOM PRÉNOM</th>
	                <th>Dossier</th>
	                <th ><img alt="delete icone" src="${pageContext.request.contextPath}/img/icons//trash.svg"  style="width: 20px; height: 20px; "></th>
	                
	            </tr>
	        </thead>
	    <tbody>
	        <c:forEach var="etudiant" items="${etudiantsPreselectionnes}">
	            <tr>
	                <td class="data">${etudiant.massar}</td>
	                <td class="data">${etudiant.cin}</td>
	                <td class="data">${etudiant.nom} ${etudiant.prenom}</td>
	                <td class="td-dossier">
	                <form action="DossiersEtudiant" method="post" >
		                    <input type="hidden" name="id_etudiant" value="${etudiant.id}">
		                    <input type="hidden" name="id_master" value="${master.id_master}"> 
	
		                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
							<button type="submit" class="dossier-btn">
							    <img src="${pageContext.request.contextPath}/img/icons/folder.svg" alt="Dossier" class="dossier-icon">
							</button>
						</form>
	                </td>
	                <td class="td-delete">
	                    <form action="PreselectionTmp" method="POST" style="display:inline;">
	                        <input type="hidden" name="id_etudiant" value="${etudiant.id}">
	                        <input type="hidden" name="id_master" value="${master.id_master}">
	                        <input type="hidden" name="option" value="delete">
	                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	                        <button type="submit" class="delete-btn">Suprimer</button>
	                    </form>
	                </td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
	</c:if>
	<c:if test="${ empty etudiantsPreselectionnes}">
		<div style="display: flex;justify-content: center;">
			<img style="width:42%;"class="main-img" alt="no data image" src="${pageContext.request.contextPath}/img/icons/noData.svg">
		</div>
	</c:if>
</div>



<script src="${pageContext.request.contextPath}/js/data_table/jquery-3.7.1.js"></script>
<script src="${pageContext.request.contextPath}/js/data_table/dataTables.js"></script>
<script>
	
		document.addEventListener('DOMContentLoaded', function() {
	    const publicationDateText = document.getElementById('DatePub').value.trim();
	    console.log("Publication Date Text: ", publicationDateText);  // Debugging output
	
	    // Convert the publication date from "28 / mai / 2024" to a JavaScript Date object
	    const months = {
	        "janvier": 0, "février": 1, "mars": 2, "avril": 3, "mai": 4, "juin": 5,
	        "juillet": 6, "août": 7, "septembre": 8, "octobre": 9, "novembre": 10, "décembre": 11
	    };
	
	    const dateParts = publicationDateText.split(' / ');
	    if (dateParts.length === 3) {
	        const day = parseInt(dateParts[0], 10);
	        const month = months[dateParts[1].trim().toLowerCase()];
	        const year = parseInt(dateParts[2], 10);
	
	        if (!isNaN(day) && !isNaN(month) && !isNaN(year)) {
	            const publicationDate = new Date(year, month, day);
	            console.log("Publication Date Object:", publicationDate);  // Debugging output
	
	            // Get today's date
	            const today = new Date();
	            today.setHours(0, 0, 0, 0);  // Reset the time part to 00:00:00
	            console.log("Today's Date:", today);  // Debugging output
	
	            // Get the button element
	            const submitButton = document.querySelector('.btn-submit');
	
	            // Disable the button and change its style if today is before the publication date
	            if (today < publicationDate) {
	                submitButton.disabled = true;
	                submitButton.style.backgroundColor = 'gray';
	                submitButton.style.cursor = 'not-allowed';
	            }
	        } else {
	            console.error("Date parts could not be parsed to numbers.");
	        }
	    } else {
	        console.error("Date format is incorrect. Unable to parse the publication date.");
	    }
	});
	    $(document).ready(function() {
            $('#example').DataTable({
                "columns": [
                    null,
                    null,
                    null,
                    { "width": "50px" },
                    { "width": "50px" }
                ]
            });
        });
</script>
</body>
</html>
