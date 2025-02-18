<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
      <meta charset="UTF-8">
      <!-- css -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/master-inscription-style.css">
      <!-- Lato -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
      <!-- Montserra -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
      <title>Inforamtion du master</title>
	  <link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
</head>
<body>
<%@ include file="/WEB-INF/RespoHeader.jsp" %>
<%@ include file="/WEB-INF/RespoSubNav.jsp" %>

<%@ include file="/WEB-INF/MasterNav.jsp" %>

<section class="m-container" style="margin-bottom: 50px;">
	<div class="header-btn">
	</div>
      <header class="header">
        <section class="subheader header-sub-section">
          <h3 class="header-subtitle">${master.respo.faculte.nom}</h3>
          <h1 class="header-title">Master en ${master.specialite }</h1>
          <p class="header-description">${master.information } </p>
          <div class="header-links-div">
            <a href="" class="header-link">
                <img src="${pageContext.request.contextPath}/img/icons/location-icon.svg" alt="location">
                <strong>${master.respo.faculte.uni.ville }</strong>
            </a>
            <a href="" class="header-link">
                <img src="${pageContext.request.contextPath}/img/icons/calendar-icon.svg" alt="calendar">
                <strong>${master.d_debut_inscription }</strong>
            </a>
            <a href="" class="header-link">
                <img src="${pageContext.request.contextPath}/img/icons/graduate-icon.svg" alt="graduate">
                <strong>${master.nom_coordinateur }</strong>
            </a>
          </div>
        </section>
        <!-- faculty icon -->
        <section class="illustration-div header-sub-section">
        </section>
      </header>
      <main class="main">
        <section class="submain">
            <!-- main sections -->
            <!-- filieres admets -->
            <div class="main-section">
                <h4 class="main-section-title">Filières admis</h4>
                <h5 class="main-section-subtitle">Les candidats peuvent postuler pour l’une des filières suivantes :</h5>
                <ul class="conditions" style="margin: 20px;">
                	<c:forEach var="fil" items="${master.filieresAdmet}">
	                    <li class="condition">${fil.nom}  ${fil.surnom }</li>
                    </c:forEach>
                </ul>
            </div>
            <!-- Dates Importantes -->
            <div class="main-section">
                <h4 class="main-section-title">Dates Importantes</h4>
                <h5 class="main-section-subtitle">Nous vous invitons à faire preuve de vigilance afin de ne pas manquer ces dates importantes.</h5>
                <ul class="conditions"  style="margin: 20px;">
                    <li class="condition">Date de début d’insription: <strong>${master.d_debut_inscription }</strong></li>
                    <li class="condition">Date de Fin d’insription: <strong>${master.d_fin_inscription }</strong></li>
                    <li class="condition">Date d’affichage de resultat de preselection: <strong>${master.d_aff_preselection }</strong></li>
                    <li class="condition">Date de concours: <strong>${master.d_concours }</strong></li>
                    <li class="condition">Date d’affichage de resultat de concours: <strong>${master.d_aff_resultat_concours }</strong></li>
                </ul>
            </div>
            <!-- Conditions -->
            <div class="main-section">
                <h4 class="main-section-title">Conditions</h4>
                <h5 class="main-section-subtitle">Nous vous prions de  noter que pour être ligible d’acceder à ce master, vous devez répondre à certains critères</h5>
                <ul class="conditions"  style="margin: 20px;">
                    <li class="condition">Avoir un age inferieur à  <strong>${master.conditions.max_age }</strong></li>
                    <li class="condition">Votre licence doit avoir été obtenue durant au moins  <strong>${master.conditions.max_age }</strong></li>
                    <li class="condition">Votre note par semestre ne doit pas atteindre  <strong>${master.conditions.note_min_semestre}</strong></li>
                    <li class="condition">Votre moyenne générale ne doit pas atteindre <strong>${master.conditions.note_seuil}</strong></li>
                </ul>
            </div>
            <!-- Conditions -->
            <div class="main-section">
                <h4 class="main-section-title">Concours</h4>
                <h5 class="main-section-subtitle">Priere de noter que lors de la phase de passage de concours, il faut être consient de certains informations</h5>
                <ul class="conditions"  style="margin: 20px;">
                    <li class="condition">Date de concours :   <strong>${master.d_concours}</strong></li>
                    <li class="condition">Date d’affichage de resultat de concours :    <strong>${master.d_aff_resultat_concours}</strong></li>
                    <li class="condition">Le concours sera passer à  :   <strong>  ${master.lieu_concours}</strong></li>
                </ul>
            </div>
            <!-- Remarques -->
            <div class="main-section">
                <h4 class="main-section-title">Remarques</h4>
                <h5 class="main-section-subtitle">Nous vous recommandons de porter une attention particulière à cette section, car elle contient des remarques essentielles.</h5>
                <p class="remarque-text">
                   ${master.information }
                </p>
            </div>
        </section>
        <section class="main-side">
          <section class="illustration-div header-sub-section">
          </section>
          <div class="header-links-div">
            <a href="" class="header-link">
            </a>
          </div>
        </section>
      </main>
    </section>
   

</body>
</html>
