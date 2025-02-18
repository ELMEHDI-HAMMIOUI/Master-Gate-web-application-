<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mot de passe changé</title>
	<style>
		body {
		    font-family: Arial, sans-serif;
		    background-color: #f2f2f2;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    height: 100vh;
		    margin: 0;
		}
		
		.container {
		    background-color: #ffffff;
		    border-radius: 8px;
		    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		    padding: 20px;
		    text-align: center;
		    width: 400px;
		}
		
		h1 {
		    color: #4CAF50;
		    margin-bottom: 20px;
		}
		
		p {
		    color: #333333;
		    font-size: 16px;
		    margin-bottom: 20px;
		}
		
		.btn-login {
		    background-color: #4CAF50;
		    border: none;
		    border-radius: 4px;
		    color: white;
		    cursor: pointer;
		    display: inline-block;
		    font-size: 16px;
		    padding: 10px 20px;
		    text-decoration: none;
		    transition: background-color 0.3s ease;
		}
		
		.btn-login:hover {
		    background-color: #45a049;
		}
	
	</style>
</head>
<body>
    <div class="container">
        <h1>Mot de passe changé avec succès</h1>
        <p>Votre mot de passe a été changé avec succès. Vous pouvez maintenant vous connecter avec votre nouveau mot de passe.</p>
        <a href="${pageContext.request.contextPath}/ResponsableLogin" class="btn-login">Se connecter</a>
    </div>
</body>
</html>