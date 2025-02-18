<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="master.beans.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Profile</title>
	<!-- css-->
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile-style.css">
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/master-inscription-style.css">
    
    <!-- js -->
    <script src="${pageContext.request.contextPath}/js/profile-script.js" defer></script>

    
    <!-- Lato -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
    <!-- Montserra -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

</head>

<body>
	<%@ include file="/includes/header.jspf" %>

    <section class="p-container">
            <section class="p-side">
                <div class="p-side-photo-div">
                    <!-- profile image link -->
                    <img src="ImgDisplayer?etudiantId=${e.id }" alt="profile-pic">
                </div>
                
                <!-- error message -->
                <div class="error-div hidden"></div>
                <!-- side bar link options  -->
                <div class="p-side-options">
                    <a href="" class="p-side-option selected">
                        <img src="img/icons/user-circle.svg" alt="user-circle">
                        <span>Profile</span>
                    </a>
                    <a href="Mymasters" class="p-side-option">
                        <img src="img/icons/list.svg" alt="list">
                        <span>My Masters</span>
                    </a>
                    <a href="Notifications" class="p-side-option">
                        <img src="img/icons/bell.svg" alt="bell">
                        <span>Notifications</span>
                    </a>

                    <button class="p-side-option settings-btn" id="settings-btn">
                        <img src="img/icons/settings.svg" alt="gear" class="settings-icon">
                        <span>Paramètres</span>
                        <img src="img/icons/chevron-down.svg" alt="chevron" class="settings-chevron">
                    </button>
                </div>
                <!-- control btns -->

                <section class="control-btns hidden" id="control-btns">
                    <a href="update" class="edit-btn btn">Modifier Mes Informations</a>
                    <button class="delete-btn btn" id="delete-btn">Supprimer Mon Compte</button>
                </section>
            </section>
            <section class="p-main">
                <!-- info personel -->
                <section class="p-main-section p-main-section-1">
                    <h3 class="p-main-section-title">Info Personels</h3>
                    
					<section class="p-content-sec">

	
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="nom">Nom</label>
	                            <input disabled type="text" name="nom" id="nom" class="input" value="${ e.nom }" class="tooltip" >
	                        </div>
	                        <div class="p-info">
	                            <label for="prenom">Prenom</label>
	                            <input disabled type="text" name="prenom" id="prenom" class="input" value="${ e.prenom }" disabled>
	                        </div>
	                        <div class="p-info">
	                            <label for="cni">CNI</label>
	                            <input disabled type="text" name="cni" id="cni" class="input" value="${ e.cin}" disabled>
	                        </div>
	                    </section>
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="telephone">Telephone</label>
	                            <input disabled type="number" name="telephone" id="telephone" class="input" value="${ e.tel}" disabled>
	                        </div>
	                        <div class="p-info">
	                            <label for="date_n">Date de Naissance</label>
	                            <input disabled type="date" name="date_n" id="date_n" class="input" value="${ e.dateNaissance}" disabled>
	                        </div>
	                        <div class="p-info">
	                            <label for="sexe">Sexe</label>
	                            <input disabled type="text" name="sexe" id="sexe" class="input" value="${ e.sexe}" disabled>
	
	                        </div>
	                    </section>
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="nationalite">Nationalité</label>
	                            <input disabled type="text" name="nationalite" id="nationalite" class="input" value="${ e.nationalite}" disabled>
	
	                        </div>
	                        <div class="p-info">
	                            <label for="email">Email</label>
	                            <input disabled type="email" name="email" id="email" class="input" value="${ e.email}" disabled>
	                        </div>

	                    </section>
	                 </section>
	                    
                </section>
                
                
                <!-- info academique -->
                <section class="p-main-section p-main-section-2">
                    <h3 class="p-main-section-title">Info Académiques</h3>
                    
                    
					<section class="p-content-sec">
					
					
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="fac">Faculté</label>
	                            <input disabled type="text" name="fac" id="fac" class="input" value="${ e.faculteNom}">
	                        </div>
	                        <div class="p-info">
	                            <label for="date_graduation">Date de Graduation</label>
	                            <input disabled type="date" name="date_graduation" id="date_graduation" class="input" value="${ e.dateGraduation}">
	                        </div>
	                        <div class="p-info">
	                            <label for="date_premiere_inscription">Date Première Inscription</label>
	                            <input disabled type="date" name="date_premiere_inscription" id="date_premiere_inscription" class="input" value="${ e.datePremiereInscription}">
	                        </div>
	                    </section>
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="fil-license">Filière License</label>
	                            <input disabled type="text" name="fil-license" id="fil-license" class="input" value="${ e.filLicenseNom}">
	                        </div>
	                        <div class="p-info">
	                            <label for="fil-bac">Filière Bac</label>
	                            <input disabled type="text" name="fil-bac" id="fil-bac" class="input up-connection-div" value="${e.filBac }" >
	                        </div>
	                        <div class="p-info">
	                            <label for="moyenne_bac">Moyene Bac</label>
	                            <input disabled type="number" name="moy-bac" id="moy-bac" class="input" value="${ e.moyBac}">    
	                        </div>
	                    </section>
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="moyenne_lic">Moyene License</label>
	                            <input disabled type="number" name="moy-lic" id="moy-lic" class="input" value="${ e.moyLicense}">    
	                        </div>
	                        <div class="p-info">
	                            <label for="massar">Massar</label>
	                            <input disabled type="text" name="massar" id="massar" class="input" value="${ e.massar}" disabled>
	                        </div>
	                    </section>
	                    
	                    <!-- notes -->
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="n_s1">Note S1</label>
	                            <input disabled type="number" name="n_s1" id="n_s1" class="input" value="${ e.noteS1}">
	                        </div>
	                        <div class="p-info">
	                            <label for="n_s2">Note S2</label>
	                            <input disabled type="number" name="n_s2" id="n_s2" class="input" value="${ e.noteS2}">
	                        </div>
	                        <div class="p-info">
	                            <label for="n_s3">Note S3</label>
	                            <input disabled type="number" name="n_s3" id="n_s3" class="input" value="${ e.noteS3}">
	                        </div>
	                    </section>
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="n_s4">Note S4</label>
	                            <input disabled type="number" name="n_s4" id="n_s4" class="input" value="${ e.noteS4}">
	                        </div>
	                        <div class="p-info">
	                            <label for="n_s5">Note S5</label>
	                            <input disabled type="number" name="n_s5" id="n_s5" class="input" value="${ e.noteS5}">
	                        </div>
	                        <div class="p-info">
	                            <label for="n_s6">Note S6</label>
	                            <input disabled type="number" name="n_s6" id="n_s6" class="input" value="${ e.noteS6}">
	                        </div>
	                    </section>
		                    <!-- documents -->
<!-- 	                    <label> Documents -->
<%-- 	                        <a href="DocsDisplayer?etudiantId=${e.id }" class="input" style="display: inline-block;margin-top: .5em;">Documents.pdf</a> --%>
<!-- 	                    </label> -->
                    
                    </section>
                    
                </section>
<!-- controls btns -->
            </section>
    </section>
       


    <script>        
     //accordion 
        document.querySelectorAll(".p-main-section-title").forEach((elm)=>{
        	elm.addEventListener("click", (e)=>{
            	e.target.parentNode.querySelector(".p-content-sec").classList.toggle("hidden");
            	elm.classList.toggle('after-rotate');
            	console.log( getComputedStyle(elm, '::after'))
            })
        })
    </script>




	<%@ include file="/includes/confirm-delete-modal.jspf" %>


</body>

</html>