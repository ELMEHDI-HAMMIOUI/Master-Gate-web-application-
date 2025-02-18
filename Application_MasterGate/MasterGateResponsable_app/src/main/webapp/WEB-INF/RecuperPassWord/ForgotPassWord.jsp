<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mot de passe oublier</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ForgotPass.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
   	<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
</head>
<body>
<div class="logo-container">
	<img src="${pageContext.request.contextPath}/img/icons/iconeMG.svg"  alt="icon">
	<img src="${pageContext.request.contextPath}/img/icons/MG.svg"  alt="icon">
	
</div>
   <div id="email-section" class="container">
    <h2>Entrez votre adresse e-mail</h2>
   
    <p class="description">Pour réinitialiser votre mot de passe, veuillez saisir votre adresse e-mail. Nous vous enverrons un code de vérification.</p>
    <form method="post" id="email-form">
        <c:if test="${ not empty erreur  }">    
        	<span id="error-message">${erreur}</span>
   		</c:if>
        <div class="input-group">
		    <label for="email">Email :</label>
		   <div class="input-icon">
                <i class="fas fa-envelope"></i>
                <input type="email" id="email" name="email" required>
            </div>
		</div>
        
        <button type="submit" class="btn-submit">Envoyer le code de vérification</button>
        <a href="ResponsableLogin"><button  type="button" class="btn-cancel">Annuler</button></a>
    </form>
</div>

<div class="steps-section">
<div class="step" style="color: #fff; font-weight: bold; border: 1px solid #ccc; border-radius: 8px; background-color: #F2F6FC; padding:8px;box-shadow:0 4px 8px rgb(0 0 0 / 92%);">
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
    <div class="step">
        <div class="step-number">4</div>
        <p>Étape 4 : Réinitialisez votre mot de passe et connectez-vous.</p>
    </div>
</div>

</body>
</html>
