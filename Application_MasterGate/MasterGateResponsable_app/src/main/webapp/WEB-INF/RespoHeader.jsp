<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/RespoHeader.css">
    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@100;300;400;700;900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;900&display=swap" rel="stylesheet">
</head>
<body>
<div class="header-nav">
    <nav class="nav">
        <section class="nav-l">
            <div class="icon-div">
                <a href="" class="icon-link">
                    <img src="${pageContext.request.contextPath}/img/icons/MG.svg" alt="icon">
                </a>
            </div>
            <ul class="links">
                <li><a href="ResponsableHome" class="link">Home</a></li>
                <li><a href="ResponsableAddMaster" class="link">Nouveaux Master</a></li>
				<li><a href="${pageContext.request.contextPath}/Guide.jsp" class="link">Guide d'utilisation</a></li>
                <li><a href="mailto:mastergateapp@gmail.com" class="link">Contactez Nous</a></li>
            </ul>
        </section>
        <section class="nav-r">

            <section class="profile-wraper">
                <a href="ResponsableProfile" class="profile-div">
                    <img src="${respo.logo_uni}" alt="profile">
                </a>
                <div class="profile-pop pop">
                    <div class="profile-pop-info">
                        <a href="ResponsableProfile"><img src="${respo.logo_uni}" alt="profile"></a>
                        <span class="profile-pop-name">${respo.surnom_uni} ${respo.surnom_fac} </span>
                        <span class="profile-pop-email">${respo.email}</span>
                    </div>
                    <div class="profile-pop-options">
                        <a href="ResponsableProfile">Mon compte</a>
                    </div>
                    <a href="ResponsableLogout" class="profile-pop-signout">Sign out</a>
                </div>
            </section>
        </section>
        
    </nav>
</div>
    <script>
        document.querySelector(".profile-div").addEventListener("mouseleave", () => {
            document.querySelector(".profile-pop").classList.toggle("pop");
        });
        document.querySelector(".profile-div").addEventListener("mouseover", () => {
            document.querySelector(".profile-pop").classList.toggle("pop");
        });
        document.querySelector(".profile-pop").addEventListener("mouseover", () => {
            document.querySelector(".profile-pop").classList.remove("pop");
        });
        document.querySelector(".profile-pop").addEventListener("mouseleave", () => {
            document.querySelector(".profile-pop").classList.add("pop");
        });
    </script>
</body>
</html>
