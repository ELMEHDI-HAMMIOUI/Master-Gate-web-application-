<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="master.beans.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<!--     <meta charset="UTF-8"> -->
<!--     <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<!--     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Profile Edit</title>
	<!-- css-->
    <link rel="stylesheet" href="css/profile-style.css">
    	<link rel="stylesheet" href="css/generic-style.css">
    <link rel="stylesheet" href="css/header-style.css">
        <link rel="stylesheet" href="css/master-inscription-style.css">
    
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

		<style>
		</style>
        <style>
            .ok-btn{
                background-color: rgb(161, 242, 81);
            }
            .ok-btn:hover{
                background-color: rgb(148, 255, 41);
            }
            .p-side-photo-div img{
                opacity: .3;
            }
            .upload-div{
                border: 2px dashed gray;            
            }
            .upload-img div{
                box-shadow: 10px 10px 10px gray;
            }
            input:disabled{
                background-color: #e6e6e6;
            }
            input:disabled:hover{
                box-shadow: none;
            }
            .p-side{
            	width: 50%
            }
            .upload-div{
            	padding: 1.3em;
            }
            @media only screen and (max-width: 1060px){
                .control-btns{
                    position: fixed;
                    bottom: 0;
                    width: 100%;
                    background-color: white;
                    padding: 2em 2em;
                    right: 0;
                    z-index: 1000;
                    margin-bottom: 0;
                }
                .p-main{
                    margin-bottom: 180px;
                }
            }
        </style>
        

<form action="update" method="post" class="p-container" enctype="multipart/form-data">

    <section class="p-container">
    <%--
            <section class="p-side">
                <div class="p-side-photo-div">
                    <!-- profile image link -->
                    <img src="ImgDisplayer?etudiantId=${e.id }" alt="profile-pic">
                    <!-- upload your new photo -->
                    <!-- <label class="upload-label"> 
                        <input type="file" name="photo" id="photo" class="input" placeholder="">
                        <div class="upload-div input">
                            <span class="upload-text" id="photo-text"> No file uploaded </span>
                            <div class="upload-subdiv">
                                <span class="upload-file-name" id="photo-name"></span>
                                <span class="upload-file-size" id="photo-size"></span>
                            </div>
                            <img src="/img/icons/upload.svg" alt="upload" class="upload-icon"></div>
                        </div >
                    </label>                 -->
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
                    <button class="edit-btn btn">Modifier Mes Informations</button>
                    <button class="delete-btn btn" id="delete-btn">Supprimer Mon Compte</button>
                </section>
            </section>
            
       --%>
       
			<!--side bar -->
       		            <section class="p-side">
                <div class="p-side-photo-div">
                    <!-- profile image link -->
                    <img src="ImgDisplayer?etudiantId=${e.id }" alt="profile-pic">
                    <!-- upload your new photo -->
                    <label class="upload-label upload-img"> 
                        <input type="file" name="photo" id="photo" class="input" placeholder="">
                        <div class="upload-div input">
                            <span class="upload-text" id="photo-text"> No file uploaded </span>
                            <div class="upload-subdiv">
                                <span class="upload-file-name" id="photo-name"></span>
                                <span class="upload-file-size" id="photo-size"></span>
                            </div>
                            <img src="img/icons/upload.svg" alt="upload" class="upload-icon"></div>
                        </div >
                    </label>                
                </div>
                <!-- error message -->
                <div class="error-div " id="error-front">${empty errorMsg ? '': errorMsg }</div>

                <!-- side bar link options  -->
                <!-- <div class="p-side-options">
                    <a href="" class="p-side-option selected">
                        <img src="user-circle.svg" alt="user-circle">
                        <span>Profile</span>
                    </a>
                    <a href="" class="p-side-option">
                        <img src="list.svg" alt="list">
                        <span>My Masters</span>
                    </a>
                    <a href="" class="p-side-option">
                        <img src="bell.svg" alt="bell">
                        <span>Notifications</span>
                    </a>

                    <button class="p-side-option settings-btn" id="settings-btn">
                        <img src="settings.svg" alt="gear" class="settings-icon">
                        <span>Paramètres</span>
                        <img src="chevron-down.svg" alt="chevron" class="settings-chevron">
                    </button>
                </div> -->
                <!-- control btns -->

                <section class="control-btns " id="control-btns">
                    <button type="submit" href="" class="edit-btn btn ok-btn">Ok</button>
                    <a href="Profile" class="delete-btn btn annuler-btn">Annuler</a>
                </section>
                <!-- backend errors -->
                <div class="error-div hidden" id="error-back"></div>
                
            </section>
       		
       		<!--  -->
            <section class="p-main">
                <!-- info personel -->
                <section class="p-main-section p-main-section-1">
                    <h3 class="p-main-section-title">Info Personels</h3>
                    
					<section class="p-content-sec">

	                <c:if test="${!empty all }">

	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="nom">Nom</label>
	                            <input  type="text" name="nom" id="nom" class="input" value="${ e.nom }" class="tooltip" >
	                        </div>
	                        <div class="p-info">
	                            <label for="prenom">Prenom</label>
	                            <input  type="text" name="prenom" id="prenom" class="input" value="${ e.prenom }" >
	                        </div>
	                        <div class="p-info">
	                            <label for="cni">CNI</label>
	                            <input  type="text" name="cin" id="cni" class="input" value="${ e.cin}" >
	                        </div>

	                    </section>
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="nationalite">Nationalité</label>
	                            <input  type="text" name="nationalite" id="nationalite" class="input" value="${ e.nationalite}" >
	                        </div>
	                        <div class="p-info">
	                            <label for="date_n">Date de Naissance</label>
	                            <input  type="date" name="date_n" id="date_n" class="input" value="${ e.dateNaissance}" >
	                        </div>
	                        <div class="p-info">
	                            <label for="sexe">Sexe</label>
	                            <input  type="text" name="sexe" id="sexe" class="input" value="${ e.sexe}" >
	
	                        </div>
	                    </section>
	               </c:if>
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="telephone">Telephone</label>
	                            <input  type="number" name="telephone" id="telephone" class="input" value="${ e.tel}" >
	                        </div>
	                        
	                        <div class="p-info">
	                            <label for="email">Email</label>
	                            <input  type="email" name="email" id="email" class="input" value="${ e.email}" >
	                        </div>
	                        <div class="p-info">
	                            <label for="password">Password</label>
	                            <input  type="password" name="password" id="password" class="input" value="" >
	                        </div>
	                    </section>
	                 </section>
	                    
                </section>
                
              <c:if test="${!empty all }">
                <!-- info academique -->
                <section class="p-main-section p-main-section-2">
                    <h3 class="p-main-section-title">Info Académiques</h3>
                    
                    
					<section class="p-content-sec">
					
					
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="fac">Faculté</label>
	                            <input  type="text" name="fac" id="fac" class="input" value="${ e.faculteNom}">
	                        </div>
	                        <div class="p-info">
	                            <label for="date_graduation">Date de Graduation</label>
	                            <input  type="date" name="date_graduation" id="date_graduation" class="input" value="${ e.dateGraduation}">
	                        </div>
	                        <div class="p-info">
	                            <label for="date_premiere_inscription">Date Première Inscription</label>
	                            <input  type="date" name="date_premiere_inscription" id="date_premiere_inscription" class="input" value="${ e.datePremiereInscription}">
	                        </div>
	                    </section>
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="fil-license">Filière License</label>
	                            <input  type="text" name="fil-license" id="fil-license" class="input" value="${ e.filLicenseNom}">
	                        </div>
	                        <div class="p-info">
	                            <label for="fil-bac">Filière Bac</label>
	                            <input  type="text" name="fil-bac" id="fil-bac" class="input up-connection-div" value="${e.filBac }" >
	                        </div>
	                        <div class="p-info">
	                            <label for="moyenne_bac">Moyene Bac</label>
	                            <input  type="number" name="moy-bac" id="moy-bac" class="input" value="${ e.moyBac}">    
	                        </div>
	                    </section>
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="moyenne_lic">Moyene License</label>
	                            <input  type="number" name="moy-lic" id="moy-lic" class="input" value="${ e.moyLicense}">    
	                        </div>
	                        <div class="p-info">
	                            <label for="massar">Massar</label>
	                            <input  type="text" name="massar" id="massar" class="input" value="${ e.massar}" >
	                        </div>
	                    </section>
	                    
	                    <!-- notes -->
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="n_s1">Note S1</label>
	                            <input  type="number" name="n_s1" id="n_s1" class="input" value="${ e.noteS1}">
	                        </div>
	                        <div class="p-info">
	                            <label for="n_s2">Note S2</label>
	                            <input  type="number" name="n_s2" id="n_s2" class="input" value="${ e.noteS2}">
	                        </div>
	                        <div class="p-info">
	                            <label for="n_s3">Note S3</label>
	                            <input  type="number" name="n_s3" id="n_s3" class="input" value="${ e.noteS3}">
	                        </div>
	                    </section>
	                    <section class="p-info-wraper">
	                        <div class="p-info">
	                            <label for="n_s4">Note S4</label>
	                            <input  type="number" name="n_s4" id="n_s4" class="input" value="${ e.noteS4}">
	                        </div>
	                        <div class="p-info">
	                            <label for="n_s5">Note S5</label>
	                            <input  type="number" name="n_s5" id="n_s5" class="input" value="${ e.noteS5}">
	                        </div>
	                        <div class="p-info">
	                            <label for="n_s6">Note S6</label>
	                            <input  type="number" name="n_s6" id="n_s6" class="input" value="${ e.noteS6}">
	                        </div>
	                    </section>
		                    <!-- documents -->
<!-- 	                    <label> Documents -->
<%-- 	                        <a href="DocsDisplayer?etudiantId=${e.id }" class="input" style="display: inline-block;margin-top: .5em;">Documents.pdf</a> --%>
<!-- 	                    </label> -->
                    
                    </section>
                    
                </section>
				<!-- controls btns -->
              </c:if>
            </section>
    </section>
        
</form>
    <style>
        .chevron-down{
            transform: rotate(180deg);
        }
        /* tooltip things */
        .p-info{
            position: relative;
        }
        .p-info::before {
            content: 'Pour modifier vos infos, aller au ' url('img/icons/settings-light.svg');
            border-radius: 4px;
            background-color: rgb(44, 43, 43);
            color: white;
            font-family: Lato, sans-serif;
            padding: 5px 10px;
            width: auto;
            position: absolute;
            z-index: 1;
            top: -23%;
            left: 18%;
            opacity: 0;
            visibility: hidden;
            transition: opacity 0.3s, visibility 0.3s;
            font-size: .7rem;
            text-align: center;
            display: flex;
            align-items: center;
            gap: 4px;
        }

    /* show tooltip on hover */
    .p-info:hover::before {
        opacity: 1;
        visibility: visible;
    }
            .profile-div img, .profile-pop-info img{
        	object-fit: cover;
        }
    </style>


    <script>
        // show the settings options
        document.getElementById("settings-btn").addEventListener("click",(e)=>{
            e.preventDefault();
            document.querySelector(".settings-chevron").classList.toggle("chevron-down");
            document.getElementById("control-btns").classList.toggle("hidden");
        })
        document.getElementById("nom").addEventListener("mouseover",(e)=>{
            e.target.classList.add("tooltiped");
        })
        
        //accordion
//         document.querySelector(".p-main-section-title").addEventListener("click", (e)=>{
//         	e.target.parentNode.querySelectorAll(".p-info-wraper").forEach((wrap)=>{
//         		wrap.classList.toggle("hidden");
//         	})
//         })
        
        
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