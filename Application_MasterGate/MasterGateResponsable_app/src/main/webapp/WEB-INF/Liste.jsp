<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- cette page pur afficher les différentes  type de liste (inscription,preselection,fianle et attente -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/data_table/dataTables.dataTables.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Liste.css">
	<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
	<title>${ListeType}</title>
</head>
<body>
<%@ include file="/WEB-INF/RespoHeader.jsp" %>
<%@ include file="/WEB-INF/RespoSubNav.jsp" %>
<%@ include file="/WEB-INF/MasterNav.jsp" %>
	     <c:if test="${ not empty ListeEtudiants}">
		 <div class="container">
	    <div class="table-container">
	    <div class="titre-container" style="margin-bottom: 33px;">
	    <span class="titre" >
            <c:choose>
                <c:when test="${ListeType eq 'inscription'}">Liste des étudiants  inscrits</c:when>
                <c:when test="${ListeType eq 'preselection'}">Liste des étudiants présélectionnés</c:when>
				<c:when test="${ListeType eq 'tmp_preselection'}">Résultats du concours</c:when>
                <c:when test="${ListeType eq 'finale'}"> Liste Finale  </c:when>
                <c:when test="${ListeType eq 'convocationR'}">liste des étudiants convoqués de la liste d'attente qui ont déposé leur dossier </c:when>
				<c:when test="${ListeType eq 'convocationF'}">liste des étudiants convoqués de la liste d'attente qui n'ont pas déposé leur dossier </c:when>
                <c:when test="${ListeType eq 'rejected'}">liste des condidatures rejetéss</c:when>
                
                                <c:when test="${ListeType eq 'tmp_finale'}">Liste Finale des étudiants inscris au Master .</c:when>
                
                <c:otherwise> - Type inconnu</c:otherwise>
            </c:choose>
	    </span>
	    </div>
		<table id="example" class="styled-table" style="width:100%">
	    
	        <thead>
	            <tr>
	                <th>CNE</th>
	                <th>CNI</th>
	                <th>NOM PRÉNOM</th>
	                <c:if test="${ ListeType eq 'tmp_preselection' or ListeType eq 'finale' }"><th>Score</th></c:if>
	                <th>Dossier</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="etudiant" items="${ListeEtudiants}">
	                <tr>
	                    <td>${etudiant.massar}</td>
	                    <td>${etudiant.cin}</td>
	                    <td>${etudiant.nom} ${etudiant.prenom}</td>
	                    <c:if test="${ ListeType eq 'tmp_preselection' or ListeType eq 'finale' }">
	                    <td class="td-dossier" style="padding: 5px">
	                    	<input type="number"  class="styled-input" name="scores" value="${etudiant.score}" step="0.01" min="0" disabled>       
	                    	<input type="hidden"  name="etudiant_id" value="${etudiant.id }" >                    
	                    </td>
	                    </c:if>
                         <td class="td-dossier">
	    	                   <form action="DossiersEtudiant" method="post" >
		                        <input type="hidden" name="id_etudiant" value="${etudiant.id}">
		                        <input type="hidden" name="id_master" value="${id_master}"> 
		                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
								<button type="submit" class="dossier-btn"><img src="${pageContext.request.contextPath}/img/icons/folder.svg" alt="Dossier" class="dossier-icon"></button>
		                    </form>	
	                    </td>

	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    </div>
		    <c:if test="${ListeType eq 'finale'}"> 
		    	 <div class="table-container">
	    		<hr style="margin:15px"> 
	   			 <span  class="titre">Liste d'attente</span>
		    	<table id="example" class="styled-table" style="width:100%">
		    
		        <thead>
		            <tr>
		                <th>CNE</th>
		                <th>CNI</th>
		                <th>NOM PRÉNOM</th>
		                <c:if test="${ ListeType eq 'tmp_preselection' or ListeType eq 'finale' }"><th>Score</th></c:if>
		                <th>Dossier</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:forEach var="etudiant" items="${ListeAttente}">
		                <tr>
		                    <td>${etudiant.massar}</td>
		                    <td>${etudiant.cin}</td>
		                    <td>${etudiant.nom} ${etudiant.prenom}</td>
		                    <c:if test="${ ListeType eq 'tmp_preselection' or ListeType eq 'finale' }">
		                    <td class="td-dossier" style="padding: 5px">
		                    	<input type="number"  class="styled-input" name="scores" value="${etudiant.score}" step="0.01" min="0" disabled>       
		                    	<input type="hidden"  name="etudiant_id" value="${etudiant.id }" >                    
		                    </td>
		                    </c:if>
	                         <td class="td-dossier">
		    	                   <form action="DossiersEtudiant" method="post" >
			                        <input type="hidden" name="id_etudiant" value="${etudiant.id}">
			                        <input type="hidden" name="id_master" value="${id_master}"> 
			                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
									<button type="submit" class="dossier-btn"><img src="${pageContext.request.contextPath}/img/icons/folder.svg" alt="Dossier" class="dossier-icon"></button>
			                    </form>	
		                    </td>
	
		                </tr>
		            </c:forEach>
		        </tbody>
		    </table>
		    </div>
	      </c:if>
	      </div>
	    </c:if>

	    <c:if test="${ empty ListeEtudiants}">
			<div  class="error-image">
			    <c:if test="${ListeType eq 'inscription'}">
			    	<span >Aucun étudiant n'est encore inscrit</span>
			    	<img class="main-img" alt="no data image" src="${pageContext.request.contextPath}/img/icons/noData.svg">
			    	
		    	</c:if>
		    	<c:if test="${ListeType eq 'rejected'}">
			    	<span >Aucun étudiant n'est encore rejeter</span>
			    	<img class="main-img" alt="no data image" src="${pageContext.request.contextPath}/img/icons/confused.svg">	
		    	</c:if>
		    	<c:if test="${ListeType eq 'preselection'}">
			    	<span >Veuillez publier les résultats de la présélection afin que vous puissiez consulter cette liste</span>
			    	<img class="main-img" alt="no data image" src="${pageContext.request.contextPath}/img/icons/noData.svg">
			    	
		    	</c:if>
		    	
		    	<c:if test="${ListeType eq 'convocationR'}">
			    	<span >liste des étudiants convoqués de la liste d'attente qui ont déposé leur dossier est vide</span>
			    	<img class="main-img" alt="no data image" src="${pageContext.request.contextPath}/img/icons/noData.svg">
			    	
		    	</c:if>
		    		<c:if test="${ListeType eq 'convocationF'}">
			    	<span >liste des étudiants convoqués de la liste d'attente qui n'ont pas déposé leur dossier est vide</span>
			    	<img class="main-img" alt="no data image" src="${pageContext.request.contextPath}/img/icons/noData.svg">
			    	
		    	</c:if>
				
				
			</div>
		</c:if>
	<script src="${pageContext.request.contextPath}/js/data_table/jquery-3.7.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/data_table/dataTables.js"></script>
	<script>
	    $(document).ready(function() {
            $('#example').DataTable({
               
            });
        });
	</script>
</body>
</html>
