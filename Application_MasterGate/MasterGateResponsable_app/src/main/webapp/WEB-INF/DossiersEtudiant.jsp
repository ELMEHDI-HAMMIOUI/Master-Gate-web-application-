<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  page d'affichage le dossier d'un etudiant-->
<!DOCTYPE html>
<html lang="fr" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>${e.prenom} ${e.nom} </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/DossierEtudiant.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/icons/folder.svg" type="image/x-icon">
</head>
<body>

<div class="header">
		<div>
		<button onclick="history.back()" class="header-button">Retour</button>
		<c:if test="${ not empty traiter  }">
		    <form action="RespoAdmission" method="POST" style="display:inline;">
	            <input type="hidden" name="admis" value="yes">
	            <input type="hidden" name="id_etudiant" value="${e.id}">
	            <input type="hidden" name="id_master" value="${id_master}">
	            <button type="submit" class="admis-button yes">Accepter</button>
	        </form>
	        <form action="RespoAdmission" method="POST" style="display:inline;">
	            <input type="hidden" name="admis" value="no">
	            <input type="hidden" name="id_etudiant" value="${e.id}">
	            <input type="hidden" name="id_master" value="${id_master}">
	            <button type="submit" class="admis-button no">Rejeter</button>
	        </form>
        </c:if>
        </div>
	<div class="show-conditions">Conditions d'admission</div>
	
    <div class="conditions">
        <h2>Conditions :</h2>
        <ul>
            <li>Age ≤ <strong>${condition.max_age}</strong></li>
            <li>Seuil : <strong>${condition.note_seuil}</strong></li>
            <li>Année d'études ≤ <strong>${condition.max_annee_etude}</strong></li>
            <li>Note semestre ≥ <strong>${condition.note_min_semestre}</strong></li>
        </ul>
    </div>
	
</div>

<div class="container">
        <section class="p-main">
            <section class="p-main-section p-main-section-1">
               
                <div class="p-side-photo-div">
                <h3 class="title">Informations Personnelles</h3>
				<%-- <img src="<%= e.getPhoto() " alt="Photo de profil"> --%>
				<img src="ImgDisplayer?etudiantId=${e.id }" alt="profile-pic">
                </div>
                	 <section class="p-info-wraper">
                    <div class="p-info">
                        <label for="nom">Nom</label>
                        <span id="nom">${e.nom}</span>
                    </div>
                    <div class="p-info">
                        <label for="prenom">Prénom</label>
                        <span id="prenom">${e.prenom}</span>
                    </div>
              
                
                </section>
                  <section class="p-info-wraper">
                    <div class="p-info">
                        <label for="cni">CNE</label>
                        <span id="cni">${e.massar}</span>
                    </div>
                        <div class="p-info">
                        <label for="cni">CNI</label>
                        <span id="cni">${e.cin}</span>
                    </div>
           		 </section>
                   <section class="p-info-wraper">
                    <div class="p-info">
                        <label for="sexe">Sexe</label>
                        <span id="sexe">${e.sexe}</span>
                    </div>
                   <div class="p-info">
                        <label for="nationalite">Nationalité</label>
                        <span id="nationalite">${e.nationalite}</span>
                    </div>
                  
            		</section>
           		 <section class="p-info-wraper">
           		 	<div class="p-info">
                        <label for="date_n">Date de Naissance</label>
                        <span id="date_n">${e.dateNaissance}</span>
                    </div>
                    <div class="p-info">
                        <label for="telephone">Téléphone</label>
                        <span id="telephone">${e.tel}</span>
                    </div>
                    
                </section>
                <section class="p-info-wraper">
                    <div class="p-info">
                        <label for="email">Email</label>
                        <span id="email">${e.email}</span>
                    </div>
                </section>
         </section>
         <section class="p-main-section p-main-section-2">
                <h3 class="title">Informations Académiques</h3>
                <section class="p-info-wraper">
                    <div class="p-info">
                        <label for="fac">Faculté</label>
                        <span id="fac">${e.faculteNom}</span>
                    </div>
                </section>
                <section class="p-info-wraper">
                    <div class="p-info">
                        <label for="fil-license">Filière de Licence</label>
                        <span id="fil-license">${e.filLicenseNom}</span>
                    </div>
                 </section>
                 <section class="p-info-wraper">
                   <div class="p-info">
                        <label for="fil-bac">Filière de Baccalauréat</label>
                        <span id="fil-bac">${e.filBac}</span>
                    </div>
                 </section>
                <section class="p-info-wraper">
                    <div class="p-info">
                        <label for="date_premiere_inscription">Première Inscription </label>
                        <span id="date_premiere_inscription">${e.datePremiereInscription}</span>
                    </div>
                    <div class="p-info">
                        <label for="date_graduation">Date de diplome</label>
                        <span id="date_graduation">${e.dateGraduation}</span>
                    </div>
                
                </section>
                <section class="p-info-wraper">
                    <div class="p-info">
                        <label for="moyenne_bac">Moyenne du Baccalauréat</label>
                        <span id="moyenne_bac">${e.moyBac}</span>
                    </div>
                     <div class="p-info">
                        <label for="moyenne_lic">Moyenne Licence</label>
                        <span id="moyenne_lic">${e.moyLicense}</span>
                    </div>
                </section>
                <section class="p-info-wraper">
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
                            <tr>
                                <td>${e.noteS1}</td>
                                <td>${e.noteS2}</td>
                                <td>${e.noteS3}</td>
                                <td>${e.noteS4}</td>
                                <td>${e.noteS5}</td>
                                <td>${e.noteS6}</td>
                            </tr>
                        </tbody>
                    </table>
                </section>
                
            </section>
            
        </section>
        <a href="DocsDisplayer?idMaster=${id_master }&idEtudiant=${e.id }" class="inscrire-btn" id="docs-depos">Documents déposés</a>
        
        <embed src="DocsDisplayerRespo?idMaster=${id_master}&etudiantId=${e.id}" type="application/pdf" style="border-radius: 18px" width="50%" height="auto" />
              
        
</div>
</body>
</html>
