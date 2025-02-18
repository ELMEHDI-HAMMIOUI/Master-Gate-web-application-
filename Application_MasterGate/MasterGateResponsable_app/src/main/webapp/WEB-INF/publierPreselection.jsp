<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- page pour publier les resultats de la preslection ou le responssable peut donner les information du concours -->
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/publierPreselection.css">
   	<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
	<title>Publier resultats de préselection</title>
</head>
<body>
		 <div class="side-bar">
			 <div >
			 	<form method="post">
			 		
			 		<input type="hidden" name="id_master" value="${master.id_master}">
			 		<div style="    margin-top: 17px;">
				 		<span class="lbl">Modifier le titre</span>
				 		<textarea id="titre_input" name="titre" class="input-N"  maxlength="600" required></textarea>
			 		</div>
			 		<div style="    margin-top: 17px;">
				 		<span class="lbl">détail du concours</span>
				 		<textarea id="sous_titre_input" name="sous_titre" class="input-N"  maxlength="600" required></textarea>
			 		</div>
			 		<button type="submit" class="main-btn" >Publier</button>
			 		<input type="hidden" name="option" value="publier">
			 	</form>
		 	</div>
		 	<div>
				<button onclick="printContainer()" style="background-color: #dfe3e8;color:#004895;" class="main-btn">Imprimer</button>
            	<button onclick="downloadDoc()" style="background-color: #dfe3e8;color:#004895;" class="main-btn">Télécharger</button>
			 	<button onclick="history.back()" style="background-color: #dfe3e8;color:#004895;" class="main-btn">Retour</button>
			 </div>
		 </div>
	    
	    <div class="container" id="container">
			<div class="header">
			<div class="entete">
				<img class="logo" src="${respo.logo_uni}">
				
			<div class="uni-fac">
				<span style="font-size: 31px;"> ${respo.nom_uni}</span>
				<span >${respo.nom_fac} </span>
			</div>
			
			<div class="date">
				<span>${sysdate}</span>
				<span>${respo.ville}</span>
			</div>
			</div>
			
			<div class="title">    
    			<span id="titre" >Résultat du préselection au master ${master.specialite }</span>
    		</div>
    			
   			<div class="subtitle">
   				<span id="sousTitre" >Les étudiants présélectionnés sont convoqués pour passer le concours le ${master.d_concours} à ${ master.lieu_concours }</span>
   			</div>
    			
		</div>
	    <table  class="styled-table">
	        <thead>
	            <tr>
	            	<th>N°</th>
	                <th>CNE</th>
	                <th>NOM PRÉNOM</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="etudiant" items="${ListeEtudiants}" varStatus="status">
	            	
	                <tr>
	                	<td>${status.index + 1}</td>
	                    <td>${etudiant.massar}</td>
	                    <td>${etudiant.nom} ${etudiant.prenom}</td>

	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	<div class="footer">
   		<div>
			<span> ${respo.nom_uni}</span>
			<span >${respo.nom_fac} </span>
			</div>
	   		<span>${ sysdate }</span>
  		</div>
  </div>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>
 
	<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Initial load: set the textarea value to the span content
        const titre = document.getElementById("titre");
        const sousTitre = document.getElementById("sousTitre");
        const titreInput = document.getElementById("titre_input");
        const soustitreInput = document.getElementById("sous_titre_input");
        titreInput.value = titre.textContent;
        soustitreInput.value=sousTitre.textContent;
        // Update span content when textarea content changes
        titreInput.addEventListener("input", function() {
        	titre.textContent=titreInput.value;
        });
        soustitreInput.addEventListener("input", function() {
        	sousTitre.textContent=soustitreInput.value;
        });
    });
    function printContainer() {
        var container = document.getElementById('container');

        var originalBody = document.body.innerHTML;

        document.body.innerHTML = container.innerHTML;

        window.print();

        document.body.innerHTML = originalBody;
        const titre = document.getElementById("titre");
        const sousTitre = document.getElementById("sousTitre");
        const titreInput = document.getElementById("titre_input");
        const soustitreInput = document.getElementById("sous_titre_input");
        titreInput.value = titre.textContent;
        soustitreInput.value=sousTitre.textContent;
        // Update span content when textarea content changes
        titreInput.addEventListener("input", function() {
        	titre.textContent=titreInput.value;
        });
        soustitreInput.addEventListener("input", function() {
        	sousTitre.textContent=soustitreInput.value;
        });
    }
    
    async  function downloadDoc() {
   	    // Sélectionnez l'élément contenant que vous souhaitez télécharger
   	    const container = document.getElementById('container');
        container.style.marginLeft = '0';
        container.style.marginRight = '0';
        container.style.marginBottom = '0';
   	    // Options pour html2pdf
   	    const options = {
   	        filename: 'Preselection_${master.specialite}.pdf',
   	        image: { type: 'jpeg', quality: 0.98 },
   	        html2canvas: { scale: 2 },
   	        jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
   	    };

   	    html2pdf().set(options).from(container).save();
   	 	await new Promise(resolve => setTimeout(resolve, 500));
        container.style.marginLeft = '267px';  
        container.style.marginRight = '26px';  
        container.style.marginBottom = '70px';
   	}
</script>

</body>
</html>