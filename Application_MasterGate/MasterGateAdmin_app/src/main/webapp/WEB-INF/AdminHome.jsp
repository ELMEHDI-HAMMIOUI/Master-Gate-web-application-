<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Mastergate</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

    <style>
        body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}

nav {
    width: 100%;
    background-color: #333;
}

nav ul {
    list-style-type: none;
    padding: 0;
    display: flex;
    justify-content: center;
    margin: 0;
}

nav li {
    margin: 0 15px;
}

nav a {
    color: white;
    text-decoration: none;
    padding: 14px 20px;
    display: block;
}

nav a:hover {
    background-color: #575757;
}

main {
    width: 80%;
    max-width: 1200px;
    margin-top: 20px;
}

section {
    margin-bottom: 40px;
}

h1 {
    color: #333;
}

form {
    display: flex;
    flex-direction: column;
}

form label {
    margin-top: 10px;
}

form input, form select, form textarea, form button {
    margin-top: 5px;
}

form button {
    margin-top: 20px;
    padding: 10px;
    background-color: #333;
    color: white;
    border: none;
    cursor: pointer;
}

form button:hover {
    background-color: #575757;
}

table {
    width: 100%;
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 10px;
    text-align: left;
}

th {
    background-color: #f2f2f2;
}

#message {
    margin-top: 10px;
    color: green;
}

    </style>

    <nav>
        <ul>
            <li><a href="#dashboard">Menu</a></li>
            <li><a href="#ajouter-responsable">Ajouter Responsable</a></li>
            <li><a href="#consulter-responsables">Consulter Responsables</a></li>
            <li><a href="#contacter-responsable">Responsables Supprimés</a></li>
            <li><a href="LogOut">Déconnexion</a></li>
        </ul>
    </nav>

    <main>
        <!-- Dashboard Section -->
        <section id="dashboard">
            <h1>Menu</h1>
            <!-- Contenu du Dashboard -->
        </section>

        <!-- Ajouter Responsable Section -->
        <section id="ajouter-responsable">
            <h1>Ajouter Responsable</h1>
		            <form id="addResponsableForm" action="AdminHome" >
		    <label for="email">Email :</label>
		    <input type="email" id="email" name="email" required>
		    
		    <label for="password">Password :</label>
		    <input type="password" id="password" name="password" required>
		    
		    <label for="faculte">Faculté :</label>
		    <select id="faculte" name="id_fac" required>
		        <option value="">Select Faculté</option>
		        <!-- Iterate over facultes and populate options -->
		        <c:forEach var="faculte" items="${facultes}">
		            <option value="${faculte.id}">${faculte.nom}</option>
		        </c:forEach>
		    </select>
		    
		    <input type="hidden" name="option" value="add">
		    <button type="submit">Ajouter</button>
		</form>

            <p id="message"></p>
        </section>

        <!-- Consulter Responsables Section -->
        <section id="consulter-responsables">
            <h1>Consulter Responsables</h1>
            <table id="responsablesTable">
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>Université</th>
                        <th>Faculté</th> 
                        <th>Option</th>                       
                    </tr>
                </thead>
                <tbody>
				<c:forEach var="responsable" items="${responsables}">
                    <tr>
                        <td>${responsable.email}</td>
                        <td>${responsable.nom_fac}</td>
                        <td>${responsable.nom_uni}</td>
                        <td>
                         <a href="AdminRespoProfile?id_respo=${ responsable.id_respo}">Modifier</a> | <a href="AdminHome?option=deleteRespo&id_respo=${ responsable.id_respo}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>                
                </tbody>
            </table>
        </section>

        <!-- Contacter Responsable Section -->
        <section id="consulter-responsables">
            <h1>Consulter Responsables Supprimés</h1>
            <table id="responsablesTable">
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>Université</th>
                        <th>Faculté</th> 
                        <th>Option</th>                       
                    </tr>
                </thead>
                <tbody>
				<c:forEach var="responsable" items="${deletedresponsables}">
                    <tr>
                        <td>${responsable.email}</td>
                        <td>${responsable.nom_fac}</td>
                        <td>${responsable.nom_uni}</td>
                        <td>
                         <a href="AdminRespoProfile?id_respo=${ responsable.id_respo}">Modifier</a> | <a href="AdminHome?option=activer&id_respo=${ responsable.id_respo}">Activer</a>
                        </td>
                    </tr>
                </c:forEach>                
                </tbody>
            </table>
        </section>
    </main>


</body>
</html>