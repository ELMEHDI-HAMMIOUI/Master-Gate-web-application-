<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  cette page est didié pour etude des dossiers des etudiant  -->
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Étude de dossier</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/data_table/dataTables.dataTables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ListCandidats.css">
   	<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
</head>
<body>
<%@ include file="/WEB-INF/RespoHeader.jsp" %>
<%@ include file="/WEB-INF/RespoSubNav.jsp" %>
	<div class="container">
		<div class="header-page">
					<c:if test="${ empty etudiantsInscrits}">
						<div class="GrandTitre"> 
					     	<span class="titre">Il n'y a actuellement aucun dossier en attente de votre traitement.</span>
					     	<c:if test="${finEtudeDossier eq 'true' }">
					     		<span class="sousTitre">Vous avez terminé le traitement  des dossiers veuillez publier les resultats de la présélection.</span>	
					     		<span class="sousTitre">Veuillez respécter la date de publication : ${master.d_aff_preselection}</span>
					     	</c:if>
					     	<c:if test="${finEtudeDossier eq 'false' }">
					     		<span class="sousTitre">Aucun étudiant n'est inscrit Actuellement dans ce master. </span>	
					     	</c:if>
				     	</div>
				     	<c:if test="${finEtudeDossier eq 'true' }">	  
			     		<form action="PublierPreselection" method="post">
					        <input type="hidden" name="id_master" value="${id_master}">
					        <button type="submit" class="main-btn">Publier </button>
					        <input type="text" id="DatePub" value="${master.d_aff_preselection}" hidden>		        
						</form>	
						</c:if>
					</c:if>
					<c:if test="${ not empty etudiantsInscrits}">
						<div class="GrandTitre"> 
							<span class="titre">Veuillez traiter les dossiers des étudiants a </span>
							<span class="sousTitre">Veuillez consulter les dossiers des étudiants et accepter ceux qui répondent à vos conditions d'admission</span>	
						</div>
						<form action="EtudeDossier" method="post">
					        <input type="hidden" name="id_master" value="${id_master}">
					        <input type="hidden" name="option" value="accept_all">
					        <button type="submit" class="main-btn">Accepter tous<span></span></button>
					        <input type="text" id="DatePub" value="${master.d_aff_preselection}" hidden>		        
						</form>		
				    </c:if>
		</div>	
        <c:if test="${ not empty etudiantsInscrits}">
	             
    		<table id="example" class="styled-table" style="width:100%">
		        <thead>
		            <tr>
		                <th>CNE</th>
		                <th>CNI</th>
		                <th>NOM PRÉNOM</th>
		                <th>Dossier</th>
		                
		            </tr>
		        </thead>
		        <tbody>
	        <c:forEach var="etudiant" items="${etudiantsInscrits}">
	        	
	               <tr <c:if test="${id_etudiant == etudiant.id }"> style="background-color: #F3CF5B;"</c:if> >
	                   <td class="data">${etudiant.massar}</td>
	                   <td class="data">${etudiant.cin}</td>
	                   <td class="data">${etudiant.nom} ${etudiant.prenom}</td>
	                   <td class="td-dossier">
	                       <form action="DossiersEtudiant" method="post" >
		                        <input type="hidden" name="id_etudiant" value="${etudiant.id}">
		                        <input type="hidden" name="id_master" value="${id_master}"> 
		                         <input type="hidden" name="traiter" value="traiter">
		                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
								<button type="submit" class="dossier-btn">
								    <img src="${pageContext.request.contextPath}/img/icons/folder.svg" alt="Dossier" class="dossier-icon">
								    Voir le dossier
								</button>
	                    	</form>	
	                    </td>
	                </tr>
		            </c:forEach>
		        </tbody>
		    </table>
		</c:if>
		<c:if test="${ empty etudiantsInscrits}">
			<div style="display: flex;justify-content: center;">
				<c:if test="${finEtudeDossier eq 'false' }">
					<img style="width:26%;"class="main-img" alt="no data image" src="${pageContext.request.contextPath}/img/icons/etude_dossier.svg">
				</c:if>
				<c:if test="${finEtudeDossier eq 'true' }">
					<img style="width:42%;"class="main-img" alt="no data image" src="${pageContext.request.contextPath}/img/icons/share.svg">
				</c:if>
			</div>
			
		</c:if>
	</div>

	
	<script src="${pageContext.request.contextPath}/js/data_table/jquery-3.7.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/data_table/dataTables.js"></script>
	<script>

	    $(document).ready(function() {
            $('#example').DataTable({
                "columns": [
                    null,
                    null,
                    null,
                    { "width": "210px" },
                ]
            });
        });
	</script>
	</body>
	</html>
