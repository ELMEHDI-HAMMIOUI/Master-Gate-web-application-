
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>
	<!--css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/auth-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">

    
    
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
    <section class="conatainer " id="signin-container">
        <div class="auth-logo-div">
            <a href="home">
                <img src="img/icons/MG.svg" alt="logo">
            </a>
        </div>
        <section class="wraper">
            <h1 class="title">Sign In</h1>
            
		        
		        
            <form action="Signin" class="" method="post" autocomplete="on" >
<%--             <p class="error-msg" style="color: red;">${errorMsg }</p> --%>

		    <!-- Error Modal -->            
            
		    <c:if test="${!empty errorMsg}">
		        <div id="errorModal" class="modal">
		            <div class="modal-content">
		                <span class="close">&times;</span>
		                <p style="display: flex;align-content: center;gap: 1em;align-items: center;">
							<iframe src="https://lottie.host/embed/837659b8-cd5d-44d7-9d5a-06566e7277d5/HmTt19cfNe.json" style="border: none;width: 9%;height: 30px;align-content: center;/*! text-align: center; *//*! margin: auto; */"></iframe>
		                	${errorMsg }
		                </p>
		            </div>
		        </div>
		    </c:if>
		    
	        <%@include file="/includes/error-msg.jspf" %>
			
			<!-- end of error msg model(include the jspf after the modal -->
	
		    
                <!-- Info personnel -->
                <section class="form-up form-part-1 current">

                    <section class="up-connection">
                        <div class="up-connection-div input-div">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" class="input" placeholder="Votre Email" value="${empty email ? '' : email }">
                        </div>
                        <div class="up-connection-div input-div tooltip" data-tooltip="Le mot de passe doit comporter au moins 8 chars, au moins une lettre minuscule, une majuscule, un chiffre et un caractère spécial (parmi !@#$%^&*).">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password" class="input" value="${empty password ? '' : password }">
					        <span id="toggle-password" class="toggle-password">
					            <img src="img/icons/show-pass.svg" id="toggle-password"></img>
					        </span>
                        </div>
                    </section>
                </section>
                <!--  -->
                <!--  -->
                <section class="form-down">
                    <div class="control-div">
                        <button type="submit" class="btn submit" id="submit">
                            Sign In
                        </button>
                    </div>
                    <p class="footer-text">Don't have an account ? <a href="Signup" class="link signup-link sign-link">Sign Up </a></p>
                </section>
            </form>
        </section>
    </section>
    
    <script>
    	document.getElementById("email").focus();
    	
    	//password toggling
    	document.getElementById('toggle-password').addEventListener('click', function () {
	    const passwordField = document.getElementById('password');
	    const togglePassword = document.getElementById('toggle-password');
	    
	    if (passwordField.type === 'password') {
	        passwordField.type = 'text';
	    } else {
	        passwordField.type = 'password';
	    }
	    
	    togglePassword.classList.toggle('show-pass')

	});
    	

    </script>
</body>
</html>