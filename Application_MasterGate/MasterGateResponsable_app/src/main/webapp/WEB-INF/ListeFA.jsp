<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  gerer le taille de liste d'attente et liste finale -->
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Étudiants Admis en Master ${master.specialite} ${master.nom_fac}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ListFA.css">
   	<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
</head>
<body>
<%@ include file="/WEB-INF/RespoHeader.jsp" %>
<%@ include file="/WEB-INF/RespoSubNav.jsp" %>

		
	<div class="container">
 	  <div class="header-page" > 
			<div class="titre-container">
				<span class="titre">Liste Finale et attente </span>
				<span class="sousTitre">Veuillez publier les résultats afin de terminer le traitement du concours.</span>	
			</div>	 
			<div class="buttons-container">
			    <form action="PublierFA" method="post" style="display:inline;">
			       	<input type="hidden" name="id_master" value="${id_master}">
				   	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				   <button type="submit" class="main-btn">Fiche de publication </button>
				 </form>
				 <form action="RespoGestionListeFA" method="post" style="display:inline;">
			       <input type="hidden" name="id_master" value="${id_master}">
			       <input type="hidden" name="option" value="ModifierNumberOf">
				   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				    <button type="submit" class="main-btn" >modifier le nombre des etudiants</button>
				 </form>
			 </div>
		 </div>
	  <div class="tables-container">
	  	<div class="table-container">
	  	<span>Liste Finale</span>
	    <table  class="styled-table">
	        <thead>
	            <tr>
	                <th>CNE</th>
	                <th>CNI</th>
	                <th>NOM PRÉNOM</th>
	                <th>Score</th>
	                <th>Dossier</th>	
	                                
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="etudiant" items="${ListeFinale}">
	                <tr>
	                    <td>${etudiant.massar}</td>
	                    <td>${etudiant.cin}</td>
	                    <td>${etudiant.nom} ${etudiant.prenom}</td>
	                    
	                    <td class="td-dossier" style="padding: 5px">
	                    	<input type="number"  class="styled-input" name="scores" value="${etudiant.score}" step="0.01" min="0" Disabled>       
	                    	<input type="hidden"  name="etudiant_id" value="${etudiant.id }" >                    
	                    </td>
                         <td class="td-dossier">
	    	                   <form action="DossiersEtudiant" method="post" >
		                        <input type="hidden" name="id_etudiant" value="${etudiant.id}">
		                        <input type="hidden" name="id_master" value="${id_master}"> 
		                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
								<button type="submit" class="dossier-btn">
								    <img src="${pageContext.request.contextPath}/img/icons/folder.svg" alt="Dossier" class="dossier-icon">
								</button>
		                    </form>	
	                    </td>
 
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    </div>
	    <div class="table-container">
	    <span>Liste d'attente</span>
	    <table  class="styled-table">
	        <thead>
	            <tr>
	                <th>CNE</th>
	                <th>CNI</th>
	                <th>NOM PRÉNOM</th>
	                <th>Score</th>
	                <th>Dossier</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="etudiant" items="${ListeAttente}">
	                <tr>
	                    <td>${etudiant.massar}</td>
	                    <td>${etudiant.cin}</td>
	                    <td>${etudiant.nom} ${etudiant.prenom}</td>
	                    
	                    <td class="td-dossier" style="padding: 5px">
	                    	<input type="number"  class="styled-input" name="scores" value="${etudiant.score}" step="0.01" min="0" disabled>       
	                    	<input type="hidden"  name="etudiant_id" value="${etudiant.id }" >                    
	                    	             
	                    </td>
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
	    </div>	 
	    <c:if test="${ emptyListe eq 'true' }">   
		  	<div class="wrapper-container">
		    	<div class="status-card-container">
		          <div class="status-card success">
		            <h3>Saisie du nombre d'étudiants</h3>
		            <p>Veuillez entrer le nombre d'étudiants Figurant  la liste d'attente et la liste finale.</p>
		            <div class="buttons">
		            <form method="POST">
		            <div >
						<div class="inputs-span"><span>Nombre d'étudiants de la liste   finale  :</span><input type="number" name="Nfinale" class="input-N" required></div>
			            <div class="inputs-span"><span>Nombre d'étudiants de la liste d'attente :</span><input type="number" name="Nattente" class="input-N" required></div>
		            	<input type="hidden" name="id_master" value="${id_master}">
		                <input type="hidden" name="option" value="numberOf" >
		                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
		            </div>
		            <button type="submit" class="continue-btn" >Continuer</button>
					<button type="button" onclick="history.back()" class="home-btn">Retour</button>
		            </form>	
		            </div>
		          </div>
				</div>
			</div>
		</c:if>
	 </div>
</body>
</html>
