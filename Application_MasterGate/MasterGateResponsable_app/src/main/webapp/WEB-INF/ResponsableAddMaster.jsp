<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="css/RespoAddMaster.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/RespoHeader.jsp" %>
	<div class="heading">
		<span>Nouveau Master </span>

	</div>

	<hr>
   <div class="container">
    <div class="title"> Informations du master : </div>
    <div class="content">
      <form action="ResponsableAddMaster" method="post">
        <div class="user-details">
          <div class="input-box">
            <span class="details">Filière du Master</span>
            <input type="text" placeholder="Enter l'option/Filière du Master" id="specialite" name="specialite" maxlength="90"  required>
          </div>
          <div class="input-box">
            <span class="details">Nom du Coordinateur </span>
            <input type="text" placeholder="Veuillez indiquer le Nom du coordinateur " id="nom_coordinateur" maxlength="40"  name="nom_coordinateur"required>
		</div>	
          <div class="input-box">
            <span class="details">Lieu du concours</span>
            <input type="text" placeholder="Veuillez indiquer le lieu où se déroulera le concours" id="lieu_concours"  name="lieu_concours" maxlength="200" required>
        </div>
        <div class="input-box">
            <span class="details">Information d'annonce master </span>
            <textarea   placeholder="donner donner des information supplémentaire " name="information" id="information"   maxlength="230" ></textarea>
        </div>
        <!-- fake -->
		<div class="input-box">
            <span class="details">document demandés </span>
            <textarea   placeholder="les relevés , les attestation" name="document" id="information" maxlength="150"></textarea>
        </div>
        </div>
		<div class="title" id="date-title"> Dates : </div>
		
		 <div class="erreur"id="dateError"></div> 
		<div class="user-details" style="display: flex;flex-wrap: nowrap;justify-content: space-between; margin: 20px 0 12px 0;">
			<div class="input-box">
			  <span class="details">Début d'inscription</span>
				<input type="date" id="currentDate" value="<%= java.time.LocalDate.now() %>" hidden required>
				<input type="date" name="d_debut_inscription" id="d_debut_inscription" value="<%= java.time.LocalDate.now() %>" >
			</div>
			<div class="input-box">
				<span class="details">fin d'inscription</span>
				<input type="date"  name="d_fin_inscription" id="d_fin_inscription"required>
			</div>
			<div class="input-box">
				<span class="details">Résultats de la présélection </span>
				<input type="date"  name="d_aff_preselection" id="d_aff_preselection"required>
			</div>
			<div class="input-box">
				<span class="details">Concours </span>
				<input type="date"  name="d_concours"  id="d_concours" required>
			</div>
			<div class="input-box">
				<span class="details">Résultats Finale</span>
				<input type="date"  name="d_aff_resultat_concours" id="d_aff_resultat_concours" required>
			</div>
		</div>
		<div class="title"> Conditions d'accès :  </div>
		<div class="erreur"id="conditionsError"></div> 
		<div class="user-details">
			<div class="input-box">
				<span class="details">Note du seuil :  </span>
				<input type="text" placeholder="Entrez la note de seuil "  name="note_seuil" maxlength="5" id="note_seuil"required>
			</div>
			<div class="input-box">
			<span class="details"> Les filière admét : </span>
			<div class="container-select" id="container-select">
                <div class="select-btn">
                    <span class="btn-text">Choisissez les filières admises pour l'inscription au master</span>
                    <span class="arrow-dwn">
                        <img src="img/icons/down_row.svg"class="fa-solid fa-chevron-down"></img>
                    </span>
                </div>
                <ul class="list-items">
                
					<li>
					<button type="button" onclick="toggleSelection()" id="toggleButton">
					   Sélectionner tous
					</button>
					</li>
                    <c:forEach var="filiere" items="${filieres}">
                        <li class="item">
                            <span class="checkbox">
                                <input type="checkbox" name="selectedFiliere" value="${filiere.id_filiere}" hidden >
                                <i class="fa-solid fa-check check-icon"></i>
                            </span>
                            <span class="item-text">-  ${filiere.nom} </span>
                            <span class="item-text-surnom">${filiere.surnom}</span>
                            
                        </li>
                    </c:forEach>
                </ul>
            </div>
            </div>
          	<div class="input-box">
				<span class="details">Note minimale des semestres  </span>
				<input type="text"   placeholder="Note minimale la plus basse du semestre requis " name="note_min_semestre" maxlength="5" id="note_min_semestre"required>
			</div>
			<div class="input-box">
			</div>
			<div class="input-box">
			  <span class="details">Limite d'âge :</span>
			  <input type="text" name="max_age" id="max_age" placeholder="l'age de l'etudiant ne doit pas dépasser .." maxlength="2" required>
			</div>
			<div class="input-box">
			</div>
			<div class="input-box">
				<span class="details">Maximum des année d'étude   </span>
				<input type="text"  name="max_annee_etude" id="max_annee_etude" placeholder="maximum des année d'etude en license ne dépasse pas" maxlength="2" required>
			</div>
		        <!-- fake -->
			<div class="input-box">
	            <span class="details">Autre Conditions</span>
	            <input   placeholder="facultatif" name="condition" ></input>
	        </div>
		</div>
		<div class="acbtn">
		  	<a href="ResponsableHome">
				<button class="btn2">
					<span>Cancel</span>
			  	</button>
		  	</a>
			<button  type="button" class="btn1"  id="addbtn">
				<span >AJOUTÉ</span>
		  	</button>
		</div>
		 <div class="confirmation-prompt" >
		    <div class="prompt-content">
		      <p class="confirmation-head">Confirmation</p>
		      <p>Êtes-vous sûr de publier ce master  ?</p>
		      <div class="button-container">
		        <button class="confirm-button" onclick="handleSubmit()" type="submit">Oui</button>
		        <button class="cancel-button" type="button">Non</button>
		      </div>
		    </div>
  		</div>
		
		
      </form>
    </div>
  </div>
 <c:if test="${not empty isAdded}">
  <div class="wrapper-container">
    <div class="status-card-container">
      <c:choose>
        <c:when test="${isAdded}">
          <div class="status-card success">
            <h3>Succès</h3>
            <p>Les informations du master sont bien enregistrées.</p>
            <div class="buttons">
              <a href="ResponsableAddMaster"><button class="continue-btn" onclick="hide()">Continuer</button></a>
              <a href="ResponsableHome"><button class="home-btn">Accueil</button></a>
            </div>
          </div>
        </c:when>
        <c:when test="${!isAdded}">
          <div class="status-card error">
            <h3>Erreur</h3>
            <p>Une erreur s'est produite lors de l'enregistrement. Veuillez réessayer.</p>
            <div class="buttons">
              <a href="ResponsableAddMaster"><button class="retry-btn" onclick="hide()">Réessayer</button></a>
              <a href="ResponsableHome"><button class="home-btn">Accueil</button></a>
            </div>
          </div>
        </c:when>
      </c:choose>
    </div>
  </div>
</c:if>










<script src="js/RespoAddMaster.js"></script> 


</body>	
</html>


