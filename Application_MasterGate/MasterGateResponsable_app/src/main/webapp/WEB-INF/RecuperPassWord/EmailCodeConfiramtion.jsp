<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MasterGate - Confirmation de l'e-mail</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ForgotPass.css">
</head>
<body>
<div class="logo-container">
	<img src="${pageContext.request.contextPath}/img/icons/iconeMG.svg"  alt="icon">
	<img src="${pageContext.request.contextPath}/img/icons/MG.svg"  alt="icon">
	
</div>
    <div id="email-section" class="container">
        <h2>Entrez le Code de Vérification</h2>
        <p class="description">Veuillez entrer le code de vérification envoyé à votre e-mail.</p>
        <form method="post" id="code-form">
          <c:if test="${ not empty erreur  }">    
        	<span id="error-message">${erreur}</span>
  		</c:if>
            <div class="input-group">
            
                <label for="code">Code :</label>
                <div class="input-icon">
                    <i class="fas fa-envelope"></i>
                    <input type="text" id="code" name="code" required>
                </div>
            </div>
            <button type="submit" class="btn-submit">Vérifier le Code</button>
            <a href="ResponsableLogin"><button  type="button" class="btn-cancel">Annuler</button></a>
            
        </form>
    </div>
    <div class="steps-section">
        <div class="step">
            <div class="step-number">1</div>
            <p>Étape 1 : Entrez votre adresse e-mail.</p>
        </div>
        <div class="step" style="color: #fff; font-weight: bold; border: 1px solid #ccc; border-radius: 8px; background-color: #F2F6FC; padding: 8px;box-shadow:0 4px 8px rgb(0 0 0 / 92%);">
            <div class="step-number">2</div>
            <p>Étape 2 : Recevez un code de vérification dans votre e-mail.</p>
        </div>
        <div class="step" style="color: #fff; font-weight: bold; border: 1px solid #ccc; border-radius: 8px; background-color: #F2F6FC; padding: 8px;box-shadow:0 4px 8px rgb(0 0 0 / 92%);">
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
