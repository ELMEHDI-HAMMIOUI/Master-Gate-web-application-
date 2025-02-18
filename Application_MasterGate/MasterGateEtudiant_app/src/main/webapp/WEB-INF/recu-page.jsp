<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reçu d'inscription</title>
    <!-- css -->
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/master-inscription-style.css">
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">

    
    
    <!-- fonts -->
      <!-- Lato -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
      <!-- Montserra -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    
</head>


    <style>
    body {
        font-family: 'Montserat', 'Lato', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f9f9f9;
        color: #333;
    }
    
    .receipt-container {
        max-width: 800px;
        margin: 1rem auto;
        padding: 1rem;
        background-color: white;
        border: 1px solid #ccc;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    
    header {
        display: flex;
        justify-content: space-between;
        border-bottom: 2px solid #007BFF;
        padding-bottom: .5rem;
        margin-bottom: 1rem;
    }
    
    .university-info, .faculty-info {
        text-align: center;
    }
    
    .logo {
      	width: 145px;
	  	height: 61px;
	  	object-fit: contain;
        margin-bottom: .5rem;
    }
    
    header h1, header h2 {
        margin: 0;
        font-size: 1rem;
        color: #007BFF;
    }
    
    header p {
        margin: 0.5rem 0;
        color: #555;
    }
    
    main h2 {
        text-align: center;
        color: #007BFF;
        margin-bottom: 1rem;
        font-size: 1.3rem;
    }

    .profile-picture {
        width: 120px;
        height: 120px;
        object-fit: cover;
        border-radius: 50%;
        margin-bottom: 1rem;
    }
    
    .student-info, .enrollment-details {
        /* margin-bottom: 1.5rem; */
        /* border: 1px solid #ccc; */
        /* padding: 1rem; */
        border-radius: 8px;
        /* background-color: #f9f9f9; */
    }
    
    .student-info h3, .enrollment-details h3 {
        color: #007BFF;
        margin: 0;
        margin-bottom: .5rem;
        margin-top: 1rem;

        font-size: 1rem;
    }
    .student-info:first-child{
        width: 90%;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: .5rem;
    }

    table, th, td {
        border: 1px solid #ccc;
    }

    th, td {
        padding: 0.5rem;
        text-align: left;
    }

    th {
        /* background-color: #007BFF; */
        /* color: white; */
        width: min-content;
    }

    footer {
        text-align: center;
        padding-top: 1rem;
        border-top: 1px solid #ccc;
        color: #666;
    }

    .student-wrap {
        display: flex;
        align-items: start;
/*         margin-bottom: .5rem; */
        gap: .7em;
    }
    
    .student-details {
        /* margin-left: 1.5rem; */
        flex: 1;
    }
    
    button {
  font-family: 'Lato', sans-serif;
  position: fixed;
  top: 1em;
  right: 2em;
  padding: 1em 2em;
  background: #007BFF;
  font-size: 1rem;
  font-weight: 700;
  color: white;
  border: 3px solid #007BFF;
  border-radius: 8px;
  box-shadow: 0 0 0 #fec1958c;
  transition: all 0.3s ease-in-out;
  cursor: pointer;
}

.faculty-info{
	align-items: center;
  display: flex;
}
    @media print {
        button {
            display: none;
        }
  }
</style>
    <button id="download">Telecharger</button>
    <div class="receipt-container">
        <header>
            <div class="university-info">
                <img src="${master.respo.faculte.uni.logo}" alt="Logo de l'Université" class="logo">
                <h1>${master.respo.faculte.uni.nom}</h1>
            </div>
            <div class="faculty-info">
                <h2>${master.respo.faculte.nom}</h2>
            </div>
        </header>
        <main>
            <h2>Reçu d'Inscription</h2>
            <section class="student-wrap">
                <div class="student-details">
                    <div class="student-info">
                        <h3>Informations Personnelles</h3>
                        <table>

						    <thead>
						        <tr>
						            <th>Nom</th>
						            <th>Prénom</th>
						            <th>CNI</th>
						            <th>Téléphone</th>
						            <th>Date de Naissance</th>
						        </tr>
						    </thead>
						    <tbody>
						        <tr>
						            <td>${ e.nom }</td>
						            <td>${ e.prenom }</td>
						            <td>${ e.cin }</td>
						            <td>${ e.tel}</td>
						            <td>${ e.dateNaissance}</td>

						        </tr>
						    </tbody>
						    
                        </table>
                                                
						<table>

						    <thead>
						        <tr>
						            <th>Massar</th>
						            <th>Sexe</th>
						            <th>Nationalité</th>
						            <th>Email</th>
						        </tr>
						    </thead>
						    <tbody>
						        <tr>
						            <td>${ e.massar}</td>
						            <td>${ e.sexe}</td>
						            <td>${ e.nationalite}</td>
						            <td>${ e.email}</td>
						        </tr>
						    </tbody>
						    
                        </table>
                        
                    </div>
                </div>
                
                <img src="ImgDisplayer?etudiantId=${e.id }" alt="Photo de l'étudiant" class="profile-picture">
            </section>
            <div class="student-info">
                <h3>Informations Académiques</h3>
                <table>

                        <thead>
                            <tr>
                        		<th>Faculté</th>
								<th>Date de Graduation</th>
								<th>Date 1ere Inscription</th>
								<th>Filière License</th>
								<th>Filière Bac</th>
								<th>Moyenne Bac</th>
								<th>Moyenne License</th>

                            </tr>
                        </thead>
                        
                        <tbody>
	                        <td>${ e.faculteNom}</td>
                        	<td>${ e.dateGraduation}</td>
	                        <td>${ e.datePremiereInscription}</td>
	                        <td>${ e.filLicenseNom}</td>
	                        <td>${e.filBac }</td>
	                        <td>${ e.moyBac}</td>
	                        <td>${ e.moyLicense}</td>

                        </tbody>
                       
                </table>
            </div>
            <div class="student-info">
                <h3>Notes</h3>
                <table>
                        <thead>
                            <tr>
                        		<th>Note S1</th>
								<th>Note S2</th>
								<th>Note S3</th>
								<th>Note S4</th>
								<th>Note S5</th>
								<th>Note S6</th>
                            </tr>
                        </thead>
                        
                        <tbody>
	                        <td>${ e.noteS1}</td>
                        	<td>${ e.noteS2}</td>
	                        <td>${ e.noteS3}</td>
	                        <td>${ e.noteS4}</td>
	                        <td>${ e.noteS5}</td>
	                        <td>${ e.noteS6}</td>
                        </tbody>
                        
                </table>
            </div>
            <div class="enrollment-details">
                <h3>Détails de l'Inscription</h3>
                <table>
                
                        <thead>
                            <tr>
                        		<th>Numéro d'Inscription</th>
								<th>Date d'Inscription</th>
<!-- 								<th>Année Académique</th> -->
								<th>Conseiller Pédagogique</th>
								<th>Programme</th>
								<th>Date de Fin</th>
                            </tr>
                        </thead>
                        
                        <tbody>
	                        <td>${in.idInsc }</td>
                        	<td>${in.dateInsc }</td>
<%-- 	                        <td>${anneAcc }</td> --%>
	                        <td>${master.nom_coordinateur }</td>
	                        <td>Master en ${master.specialite }</td>
	                        <td>${master.d_fin_inscription }</td>
                        </tbody>
                        
<!--                     <tr> -->
<!--                         <th>Numéro d'Inscription</th> -->
<!--                         <td>2024-123456</td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <th>Date d'Inscription</th> -->
<!--                         <td>30 Mai 2024</td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <th>Année Académique</th> -->
<!--                         <td>2024-2025</td> -->
<!--                     </tr> -->
                    <!-- <tr>
                        <th>Mode d'Étude</th>
                        <td>Temps plein</td>
                    </tr>
                    <tr>
                        <th>Montant des Frais de Scolarité</th>
                        <td>2500 EUR</td>
                    </tr>
                    <tr>
                        <th>Méthode de Paiement</th>
                        <td>Carte de crédit</td>
                    </tr> -->
                    <!-- <tr>
                        <th>Statut de l'Inscription</th>
                        <td>Confirmé</td>
                    </tr> -->
<!--                     <tr> -->
<!--                         <th>Conseiller Pédagogique</th> -->
<!--                         <td>Dr. Marie Dupont</td> -->
<!--                     </tr> -->
                    <!-- <tr>
                        <th>Lieu de Cours</th>
                        <td>Campus Principal</td>
                    </tr> -->
<!--                     <tr> -->
<!--                         <th>Programme</th> -->
<!--                         <td>Master en Génie</td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <th>Date de Fin</th> -->
<!--                         <td>31 Août 2026</td> -->
<!--                     </tr> -->
                </table>
            </div>
        </main>
        <footer>
            <p>&copy; ${master.respo.faculte.uni.nom}. Tous droits réservés.</p>
        </footer>
        
        
  <script>
  	document.getElementById("download").addEventListener("click",()=>{
  		window.print();
  	})
  </script>

  
  
  
  