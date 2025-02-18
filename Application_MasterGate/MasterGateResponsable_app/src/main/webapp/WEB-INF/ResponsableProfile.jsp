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
    <title>Profile</title>
	<!-- css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/RespoProfile.css">
    
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
<%@ include file="/WEB-INF/RespoHeader.jsp" %>

    <section class="p-container">
            <section class="p-side">
                  <div class="p-side-photo-div">
                    <!-- profile image link -->
                    <a href="${respo.web_site}">
                    <img src="${respo.logo_uni }" alt="profile-pic">
         			<br>
         			<span>${respo.nom_uni }  </span> 
         			</a>
                </div>
                <!-- error message -->
                <div class="error-div hidden"></div>
                <!-- side bar link options  -->
                <div class="p-side-options">
                    <a href="" class="p-side-option selected">
                        <img src="img/icons/user-circle.svg" alt="user-circle">
                        <span>Mon compte</span>
                    </a>
                    <button class="p-side-option settings-btn" id="settings-btn">
                        <img src="img/icons/settings.svg" alt="gear" class="settings-icon">
                        <span>Paramètres</span>
                        <img src="img/icons/chevron-down.svg" alt="chevron" class="settings-chevron">
                    </button>
                    <c:if test="${ not empty param.erreur}">
                    <span id="erreur">${param.erreur}</span>
                    </c:if>
                </div>
                <!-- control btns -->

                <section class="control-btns hidden" id="control-btns">
                    	<button class="edit-btn btn" id="edit-password-btn">Changer Mon mots de passe</button>
                    
						                    
						<div class="card">
						  <h2>Changer votre mot de passe</h2>
						  <form action="RespoChangePassword" method="post" id="password-form">
						    <div class="form-group">
						      <label for="current-password">Mot de passe courant:</label>
						      <input type="password" id="current-password" name="current_password" required>
						      <label for="new-password">Nouveau mot de passe:</label>
						      <input type="password" id="new-password" name="new_password" required>
						      <label for="confirmationNew-password">Rentrer le nouveau mot de passe:</label>
						      <input type="password" id="confirmationNew-password" name="confirmationNew_password" required>
						    </div>
						    <button type="submit" class="btn-submit" id="btn-submit">Submit</button>
							<input type="text" value="${ respo.email }" name="email" hidden>
					  	</form>
						</div>
                </section>
            </section>
            <section class="p-main">
                <!-- info personel -->
                <section class="p-main-section p-main-section-1">
                    <h3 class="p-main-section-title">Mon compte</h3>
                    <section class="p-info-wraper">
                        <div class="p-info">
                            <label for="nom">Email</label>
                            <input disabled type="text" name="nom" id="nom" class="input" value="${ respo.email }" class="tooltip" >
                        </div>
                        <div class="p-info">
                            <label for="prenom">Password</label>
                            <input disabled type="text" name="prenom" id="prenom" class="input" value="*********" disabled>
                        </div>

                    </section>
                    <section class="p-info-wraper">
                           <div class="p-info">
                            <label for="telephone">Université</label>
                            <input  type="text" name="telephone" id="telephone" class="input"  value="${respo.nom_uni }" disabled>
                        </div>
                        <div class="p-info">
                            <label for="sexe">Faculté</label>
                            <input  type="text" name="sexe" id="sexe" class="input" value="${ respo.nom_fac}" disabled>
                        </div>
                    </section>
                    <section class="p-info-wraper">
                        <div class="p-info">
                            <label for="nationalite">Ville</label>
                            <input  type="text" name="nationalite" id="nationalite" class="input" value="${ respo.ville}" disabled>
                        </div>
                        <div class="p-info">
                            <label for="password">Site Web d'université</label>
                            <input  type="text" name="password" id="password" class="input" value="${ respo.web_site}" disabled >
                        </div>
                    </section>
                </section>
			</section>
    </section>


  


    <script>
   
        // show the settings options
        document.getElementById("settings-btn").addEventListener("click",(e)=>{
            e.preventDefault();
            document.querySelector(".settings-chevron").classList.toggle("chevron-down");
            document.getElementById("control-btns").classList.toggle("hidden");
        })
        document.getElementById("nom").addEventListener("mouseover",(e)=>{
            console.log("hello")
            e.target.classList.add("tooltiped");
        })
        
      const editPasswordBtn = document.getElementById("edit-password-btn");
	  const passwordCard = document.querySelector(".card");
	
	  editPasswordBtn.addEventListener("click", function() {
	    passwordCard.classList.toggle("hidden");
	  });
	
	  document.addEventListener("click", function(event) {
	    if (!passwordCard.contains(event.target) && event.target !== editPasswordBtn) {
	      passwordCard.classList.add("hidden");
	    }
	  });
	  document.getElementById('password-form').addEventListener('input', function(event) {
		  const newPassword = document.getElementById('new-password').value;
		  const confirmationPassword = document.getElementById('confirmationNew-password').value;
		  const btnSubmit = document.getElementById('btn-submit');
		  
		  // Check if the passwords match
		  if (newPassword !== confirmationPassword) {
		    // If passwords don't match, add red border to both input fields
		    document.getElementById('new-password').style.borderColor = '#ee0000';
		    document.getElementById('confirmationNew-password').style.borderColor = '#ee0000';
		    document.getElementById('new-password').style.boxShadow = '0 4px 8px #ff7373';
		    document.getElementById('confirmationNew-password').style.boxShadow = '0 4px 8px #ff7373';
		    btnSubmit.style.backgroundColor = "#ccc";
		    btnSubmit.disabled = true; 
		  } else {
		    // If passwords match, remove red border and enable submit button
		    document.getElementById('new-password').style.borderColor = '#ccc';
		    document.getElementById('confirmationNew-password').style.borderColor = '#ccc';
		    document.getElementById('new-password').style.boxShadow = 'none';
		    document.getElementById('confirmationNew-password').style.boxShadow = 'none';
		    
		    btnSubmit.disabled = false; 
		    btnSubmit.style.backgroundColor = "";
		  }
		});
	  
      

    </script>


</body>

</html>