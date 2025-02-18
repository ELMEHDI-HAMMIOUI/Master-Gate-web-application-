<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MasterGate - Change Password </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ForgotPass.css">
</head>
<body>
<div class="logo-container">
	<img src="${pageContext.request.contextPath}/img/icons/iconeMG.svg"  alt="icon">
	<img src="${pageContext.request.contextPath}/img/icons/MG.svg"  alt="icon">
	
</div>
<div id="password-section" class="container">
    <h2>Change Your Password</h2>
    <form method="post" id="password-form">
    <div class="psContainer">
        <c:if test="${ not empty erreur  }">    
        	<span id="error-message">${erreur}</span>
  		</c:if>
     <br>
		<label for="new-password"><i class="fas fa-lock"></i> Nouveau mot de passe:</label>
        <div class="form-group">
            <input type="password" id="new-password" name="new_password" required>
        </div>
        <br>
        <label for="confirmation-new-password"><i class="fas fa-lock"></i> Confirmer le nouveau mot de passe:</label>
        <div class="form-group">
            
            <input type="password" id="confirmation-new-password" name="confirmation_new_password" required>
        </div>
	</div>
        <button type="submit" class="btn-submit"><i class="fas fa-check"></i> Changer </button>
                <a href="ResponsableLogin"><button  type="button" class="btn-cancel">Annuler</button></a>
        
    </form>
</div>

<div class="steps-section">
    <div class="step">
        <div class="step-number">1</div>
        <p>Étape 1 : Entrez votre adresse e-mail.</p>
    </div>
    <div class="step">
        <div class="step-number">2</div>
        <p>Étape 2 : Recevez un code de vérification dans votre e-mail.</p>
    </div>
    <div class="step">
        <div class="step-number">3</div>
        <p>Étape 3 : Saisissez le code de vérification sur la page suivante.</p>
    </div>
<div class="step" style="color: #fff; font-weight: bold; border: 1px solid #ccc; border-radius: 8px; background-color: #F2F6FC; padding:8px;box-shadow:0 4px 8px rgb(0 0 0 / 92%);">
        <div class="step-number">4</div>
        <p>Étape 4 : Réinitialisez votre mot de passe et connectez-vous.</p>
    </div>
</div>

    
        <script>
        document.getElementById('password-form').addEventListener('input', function(event) {
            const newPassword = document.getElementById('new-password').value;
            const confirmationPassword = document.getElementById('confirmation-new-password').value;
            const btnSubmit = document.getElementById('password-form').querySelector('.btn-submit');

            if (newPassword !== confirmationPassword) {
                document.getElementById('new-password').style.borderColor = '#ee0000';
                document.getElementById('confirmation-new-password').style.borderColor = '#ee0000';
                btnSubmit.style.backgroundColor = "#ccc";
                btnSubmit.disabled = true; 
            } else {
                document.getElementById('new-password').style.borderColor = '#ccc';
                document.getElementById('confirmation-new-password').style.borderColor = '#ccc';
                btnSubmit.style.backgroundColor = "";
                btnSubmit.disabled = false; 
            }
        });

    </script>
</body>
</html>