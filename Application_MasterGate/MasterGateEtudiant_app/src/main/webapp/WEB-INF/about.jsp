<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About us</title>
    
        <!-- Lato -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
        <!-- Montserra -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <!-- css -->
    
    <link rel="stylesheet" href="css/header-style.css">
      	<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">

</head>
<body>
<style>
*,*::after,*::before{
    margin:0;
    padding: 0;
    box-sizing: border-box;
}
:root{
    --pr-clr-l: #FFDF8A;
    --scnd-clr-l: #FFEFCA; 
    --black: #121211;
    --black-75: rgba(18,18, 17, 0.75);
    --black-50: rgba(18, 18, 17, 0.50);
    --input-back: #FFF9EB;
    --search-bar-back: white;
    --thrd-clr-l: #272F58;
    --frd-clr-l: #B97030; 
    --grey:#E1E1E1;
    --alert-clr: #f05361;
}
body{
    font-size: 16px;
    font-family: Lato, sans-serif;
}
    .a-container{
        padding: 2em;
        text-align: center;
    }
    .team-container{
        display: flex;
        gap: 9em;
        justify-content: center;
        align-items: center;
        flex-wrap: wrap;
    }
    .title{
        margin-bottom: 1em;
        font-size: 48px;
        font-weight: 700;
    }
    .subtitle{
        margin-bottom: 3em;
        font-size: 24px;
        font-weight: 600;
    }
    .subtitle strong{
        font-size: 17px;
        font-weight: 400;
        width: 20px;

    }
    .member-div{
        display: flex;
        flex-direction: column;
        align-items: center;

    }
    .member-img-div{
        border-radius: 50%;
        object-fit: cover;
        width: 100px;

    }
    .member-img-div img{
        width: 100%;
        border-radius: 50%;
    }
    .member-name{
        margin-bottom: .5em;
        margin-top: 1em;

    }
    .member-role{
        color: var(--black-50);
        margin-bottom: 1em;
    }
    .member-social{
        display: flex;
        gap: .5em;
        align-items: center;
    }
    .social-a{
        text-decoration: none;
        padding: .8em;
        border-radius: 14px;
    }
    .social-a:hover{
        background-color: var(--grey);
    }
    @media screen and (max-width: 600px){
        .team-container{
            gap: 2em;
        }
    }
    
        .profile-div img, .profile-pop-info img{
        	object-fit: cover;
        }
        
</style>

<%@include file="/includes/header.jspf" %>

    <section class="a-container">
        <h1 class="title">ABOUT US</h1>
        <h3 class="subtitle">Alhamdoulillah, qu'Allah bénisse et accorde la paix à notre Prophète Muhammad.<br>
             <strong>Master Gate  est un projet de fin d'étude visant à simplifier l'inscription aux masters dans de nombreuses facultés.
                <br> Découvrez l'équipe de développement derrière ce projet.</strong>
        </h3>
        <section class="team-container">
            <div class="member-div">
                <div class="member-img-div">
                    <img src="https://avatars.githubusercontent.com/u/115116715?v=4" alt="profile-pic">
                </div>
                <h5 class="member-name">Hammadate Taha</h5>
                <p class="member-role">Student</p>
                <div class="member-social">
                    <a href="https://github.com/hammtah" class="social-a">
                        <img src="img/icons/brand-github.svg" alt="brand-github">
                    </a>
                    <a href="#" class="social-a">
                        <img src="img/icons/brand-instagram.svg" alt="brand-instagram">
                    </a>
                    <a href="#" class="social-a">
                        <img src="img/icons/brand-x.svg" alt="brand-x">
                    </a>
                </div>
            </div>
            <div class="member-div">
                <div class="member-img-div">
                    <img src="https://images.rawpixel.com/image_png_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIyLTA4L2pvYjEwMzQtZWxlbWVudC0wNi0zOTcucG5n.png" alt="profile-pic">
                </div>
                <h5 class="member-name">Hammioui Elmehdi</h5>
                <p class="member-role">Student</p>
                <div class="member-social">
                    <a href="" class="social-a">
                        <img src="img/icons/brand-github.svg" alt="brand-github">
                    </a>
                    <a href="" class="social-a">
                        <img src="img/icons/brand-instagram.svg" alt="brand-instagram">
                    </a>
                    <a href="" class="social-a">
                        <img src="img/icons/brand-x.svg" alt="brand-x">
                    </a>
                </div>
            </div>
        </section>
    </section>
</body>
</html>