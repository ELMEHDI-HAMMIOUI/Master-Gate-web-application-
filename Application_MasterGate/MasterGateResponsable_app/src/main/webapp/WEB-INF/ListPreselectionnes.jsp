	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE >
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Liste des Étudiants Admis en Master ${master.specialite} ${master.nom_fac}</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/data_table/dataTables.dataTables.css">
		<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ListCandidats.css">
	</head>
	
	<body>
	<%@ include file="/WEB-INF/RespoHeader.jsp" %>
	<%@ include file="/WEB-INF/RespoSubNav.jsp" %>
	<div class="container">
	<div class="header-page">
		<div class="GrandTitre">
			<span class="titre">Liste des étudiants présélectionnés pour passer le concours </span>
			<span class="sousTitre">Veuillez insérer le score de chaque étudiant (si un étudiant s'est absenté , veuillez le supprimer).</span>	
		</div>	 
	</div>
	<form action="RespoConcours" method="POST" style="display:inline;">
	    <input type="hidden" name="id_master" value="${id_master}">
  	  	<input type="hidden" name="option" value="save">
  	 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	    <table id="example" class="styled-table" style="width:100%">
	        <thead>
	            <tr>
	                <th>CNE</th>
	                <th>CNI</th>
	                <th>NOM PRÉNOM</th>
	                <th>Score</th>
	                <th>Dossier</th>
	                <th ><img alt="delete icone" src="${pageContext.request.contextPath}/img/icons//trash.svg"  style="width: 20px; height: 20px; "></th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="etudiant" items="${etudiantsPreselectionnes}">
	                <tr>
	                    <td>${etudiant.massar}</td>
	                    <td>${etudiant.cin}</td>
	                    <td>${etudiant.nom} ${etudiant.prenom}</td>
	                    
	                    <td class="td-dossier" style="padding: 5px">
	                    	<input type="number"  class="styled-input" name="scores" placeholder="${etudiant.score}" step="0.01" min="0" >       
	                    	<input type="hidden"  name="etudiant_id" value="${etudiant.id }" >                    
	                    	             
	                    </td>
                         <td class="td-dossier">
	    	                   <form action="DossiersEtudiant" method="post" style="display:inline;" >
		                        <input type="hidden" name="id_etudiant" value="${etudiant.id}">
		                        <input type="hidden" name="id_master" value="${id_master}"> 
		                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
								<button type="submit" class="dossier-btn">
								    <img src="${pageContext.request.contextPath}/img/icons/folder.svg" alt="Dossier" class="dossier-icon">
								</button>
		                    </form>	
	                    </td>
	                    <td  class="td-delete">
	  	                    <form action="RespoConcours" method="POST" style="display:inline;">
		                        <input type="hidden" name="id_etudiant" value="${etudiant.id}">
		                        <input type="hidden" name="id_master" value="${id_master}">
		                        <input type="hidden" name="option" value="delete">
		                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		                        <button type="submit" class="delete-btn" >X</button>
		                    </form>
	                    </td>    
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
		    <c:if test="${not empty etudiantsPreselectionnes }">
				<div class="footer">
			              <button type="submit" class="main-btn" style="height: 43px;font-size: large;margin-top: 15px;">Enregistrer</button>
			          
				</div>
			</c:if>
		</form>
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
                { "width": "133.75px" },
                { "width": "50px" },
                { "width": "50px" },
            ]
        });
    });
	</script>
	</body>
</html>
