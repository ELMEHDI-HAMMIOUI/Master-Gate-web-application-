<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding:0;
        }
        .container {
		    padding: 20px;
		    border-radius: 8px;
		    min-width: 300px;
		    margin: 12px 107px;
		    box-shadow: rgba(0, 0, 0, 0.16) 0px 10px 36px 0px, rgba(0, 0, 0, 0.06) 0px 0px 0px 1
        }
        .table-container span {
		    display: flex;
		    font-size: 1.2em;
		    margin-bottom: 10px;
		    font-weight: bold;
		    color: #333;
		    justify-content: center;
        }
        .styled-table {
            width: 100%;
            border-collapse: collapse;
            margin: 25px 0;
            font-size: 0.9em;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
        }
        .styled-table thead tr {
            background-color: #009879;
            color: #ffffff;
            text-align: left;
        }
        .styled-table th, .styled-table td {
            padding: 12px 15px;
            border-bottom: 1px solid #dddddd;
			font-family: system-ui;
        }
        .styled-table tbody tr:nth-of-type(even) {
            background-color: #f3f3f3;
        }
        .styled-table tbody tr:hover {
            background-color: #f1f1f1;
        }
        .styled-input {
            width: 60px;
            height: 100%;
            box-sizing: border-box;
            padding: 8px;
            border: 2px solid #ccc;
            border-radius: 5px;
        }
		.Back-btn {
		margin: 5px;
        display: inline-block;
        padding: 10px 20px;
        font-size: 14px;
        font-weight: bold;
        text-transform: uppercase;
        color: #ffffff;
        background-color: #3498db; /* Blue background */
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease, transform 0.3s ease;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    	}
    	.entete{
			display: flex;
		    flex-wrap: nowrap;
		    justify-content: space-between;
    	}
		.logo{
			height: 90px;
		}
		.uni-fac{
			font-family: sans-serif;
       		display: flex;
		    flex-wrap: wrap;
		    justify-content: center;
		    align-items: center;
		    flex-direction: column;
		}
		.date{
			display: flex;
		    flex-direction: column;
		    justify-content: flex-start;
		    flex-wrap: wrap;
		    align-content: flex-end;
		    font-size: 16px;
		    align-items: flex-end;
		    font-family: system-ui;
		}
		.subtitle{
			    margin-top: 23px;
			    font-family: 'system-ui';
			    font-size:19px;
		}
		.title{
		    font-family: 'system-ui';
		    display: flex;
		    flex-wrap: wrap;
		    justify-content: center;
		    align-items: center;
		    flex-direction: column;
		    margin-top: 47px;
		    font-size:30px;
		}
		.table-titre{
			font-size: x-large;
    		font-family: system-ui;
		    color: #124b12;
		}
		.footer{
			display: flex;
		    justify-content: space-between;
		    font-size: smaller;
		    font-family: system-ui;
		    margin-top: 90px;
		}
		
		 .empty-state-div { 
		   display: flex; 
		   justify-content: center; /* Center horizontally */
		   align-items: center;     /* Center vertically */ 
		  height: 70vh;            
		   flex-direction: column; 
		  gap: 1em; 
		   margin: auto; 

 		} 
    </style>
</head>
<body>
		

		<c:if test="${empty etudiantsFinale }">
			<div class="empty-state-div">
			<img src="img/empty-state.svg" alt="Empty state illustration">
		  	<h4>Les Résultats Finales ne sont pas encore sorties, Veuillez respectez la date de publication mentionée.</h4>
		</div>
		</c:if>

		
		<c:if test="${!empty etudiantsFinale }">

		    <div class="container" id="container">
				<div class="header">
					<div class="entete">
						<img class="logo" src="${pub.logo_uni}">
						
					<div class="uni-fac">
						<span style="font-size: 31px;"> ${pub.nom_uni}</span>
						<span >${pub.nom_fac} </span>
					</div>
					
					<div class="date">
						<span>${pub.date_publication}</span>
						<span>${pub.ville}</span>
					</div>
					</div>
					
					<div class="title">    
		    			<span id="titre" >${ pub.titre }</span>
		    			</div>
		    			
		   			<div class="subtitle">
		   				<span id="sousTitre" >${ pub.sous_titre }</span>
		   			</div>
		   			<hr>
				</div>
			<div>
			<span class="table-titre">Liste finale : </span>
			    <table  class="styled-table">
			        <thead>
			            <tr>
			            	<th>N°</th>
			                <th>CNE</th>
			                <th>CNI</th>
			                <th>NOM PRÉNOM</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach var="etudiant" items="${etudiantsFinale}" varStatus="status">
			            	
			                <tr>
			                	<td>${status.index + 1}</td>
			                    <td>${etudiant.massar}</td>
			                    <td>${etudiant.cin}</td>
			                    <td>${etudiant.nom} ${etudiant.prenom}</td>
		
			                </tr>
			            </c:forEach>
			        </tbody>
			    </table>
		    </div>
		    <hr style="width: 60%;background-color: forestgreen;   height: 1px;">
		    <div>
		    	<span class="table-titre">Liste d'attente : </span>
			    <table  class="styled-table">
			        <thead>
			            <tr>
			            	<th>N°</th>
			                <th>CNE</th>
			                <th>CNI</th>
			                <th>NOM PRÉNOM</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach var="etudiant" items="${etudiantsAttente}" varStatus="status">
			            	
			                <tr>
			                	<td>${status.index + 1}</td>
			                    <td>${etudiant.massar}</td>
			                    <td>${etudiant.cin}</td>
			                    <td>${etudiant.nom} ${etudiant.prenom}</td>
		
			                </tr>
			            </c:forEach>
			        </tbody>
			    </table>
		    </div>
		    <div class="footer">
		    	<div>
	 				<span> ${pub.nom_uni}</span>
					<span >${pub.nom_fac} </span>
				</div>
		    	<span>${ pub.date_publication }</span>
		    </div>
	  	</div>
  
  </c:if>
</body>
</html>