<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQ Page</title>

        <!-- Lato -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
        <!-- Montserra -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <!-- css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/masters-page-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile-style.css">


    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            font-family: Lato;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background: #fff;
            padding: 3em;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .faq-header {
            font-size: 24px;
            margin-bottom: 2em;
            font-family: Montserrat;
        }
        .accordion {
            border-top: 1px solid #ddd;
        }
        .accordion-item {
            border-bottom: 1px solid #ddd;
        }
        .accordion-header {
            padding: 15px;
            cursor: pointer;
            background-color: #f1f1f1;
            transition: background-color 0.3s ease;
        }
        .accordion-header:hover {
            background-color: #e9ecef;
        }
        .accordion-body {
            display: none;
            padding: 15px;
        }
        .accordion-header.active + .accordion-body {
            display: block;
        }
    </style>
</head>
<body>
	<%@ include file="/includes/header.jspf" %>

    <div class="container">
        <h1 class="faq-header">Frequently Asked Questions</h1>
        <div class="accordion">
            <div class="accordion-item">
                <div class="accordion-header" onclick="toggleAccordion(this)">
                    Un etudiant selectionné dans la liste finale s'est absenté, ou n'a pas déposer ses documents dans la date mentionnée?
                </div>
                <div class="accordion-body">
                    Il sera éliminer, et remplacer par le premier etudiant present dans la liste d'attente, ce dernier sera notifier sur l'application et sur email.
                </div>
            </div>
            <div class="accordion-item">
                <div class="accordion-header" onclick="toggleAccordion(this)">
                    J'ai saisie des données erronés dans mon profile.
                </div>
                <div class="accordion-body">
                    Vous pevez à chaque moment supprimer votre compte et s'inscrire à nouveau.
                </div>
            </div>
            <div class="accordion-item">
                <div class="accordion-header" onclick="toggleAccordion(this)">
                    Quoi faire après savoir que je suis préselectionné à passer le concours?
                </div>
                <div class="accordion-body">
                    Vous devez apporter votre reçu d'inscription que vous pouvez le trouver dans la page du master où vous étiez preselectionné, ainsi que toutes autre documents demandés que vous pouvez aussi les trouvez dans la section des remarques du master et se diriger vers le lieu du concours dans la periode mentionnée.

                </div>
            <div class="accordion-item">
                <div class="accordion-header" onclick="toggleAccordion(this)">
                    A quoi sert la photo de profile?
                </div>
                <div class="accordion-body">
                    La photo de profile est très importante, elle sera utilisée dans le reçu que vous allez obtenir lors de l'inscription à un master.
                </div>
            </div>
            <div class="accordion-item">
                <div class="accordion-header" onclick="toggleAccordion(this)">
                    Où je peut trouver le reçu d'inscription d'un master?
                </div>
                <div class="accordion-body">
                    Vous pouvez trouver le reçu d'inscription d'un master, dans la page d'inscription de ce dernier.
                    Tip: Rendez-vous sur la page 'myMasters' où vous trouverez tous les masters auxquels vous vous êtes inscrits.
                </div>
            </div>
            <div class="accordion-item">
                <div class="accordion-header" onclick="toggleAccordion(this)">
                    Comment je peut savoir si je suis selectionné pour passer le concours d'un master?
                </div>
                <div class="accordion-body">
                    Si vous êtes présélectionné pour passer le concours d'un master, une notification vous sera envoyée.
                </div>
            </div>
            <div class="accordion-item">
                <div class="accordion-header" onclick="toggleAccordion(this)">
                    j'ai passé le concours, que  faire maintenant?
                </div>
                <div class="accordion-body">
                    Vous devrez attendre l'affichage des listes principales et d'attente. Une fois publiées, une notification vous sera envoyée. Pensez à consulter les listes pour plus d'informations.                </div>
            </div>
            <div class="accordion-item">
                <div class="accordion-header" onclick="toggleAccordion(this)">
                    Où je peut trouver les listes principaux et d'attentes?
                </div>
                <div class="accordion-body">
                    Une fois la date d'affichage des listes arrivée, les listes seront affichées sur la page d'inscription au master concerné.                </div>
            </div>
            <div class="accordion-item">
                <div class="accordion-header" onclick="toggleAccordion(this)">
                    Les informations concernant le concours?
                </div>
                <div class="accordion-body">
                    Toutes les informations concernant un master sont affichées sur la page d'inscription de ce dernier. En ce qui concerne le concours, plus d'informations seront affichées ultérieurement dans la liste des présélectionnés.                </div>
            </div>
        </div>
    </div>

    <script>
        function toggleAccordion(element) {
            const isActive = element.classList.contains('active');
            const accordionItems = document.querySelectorAll('.accordion-header');
            accordionItems.forEach(item => item.classList.remove('active'));
            if (!isActive) {
                element.classList.add('active');
            }
        }
    </script>
</body>
</html>
