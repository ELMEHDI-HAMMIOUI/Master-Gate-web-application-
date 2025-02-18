	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<!DOCTYPE html>
	<html lang="en" dir="ltr">
	<head>
	    <meta charset="UTF-8">
	    <title>Liste des Étudiants Admis en Master ${master.specialite} ${master.nom_fac}</title>

	<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f8f9fa;
        }

        .tables-container {
		    display: flex;
		    justify-content: space-between;
		    gap: 39px;
		    flex-wrap: wrap;
        }

        .table-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            flex: 1 1 calc(50% - 20px);
            display: flex;
            justify-content: space-between;
            gap:3em;
            
            
        }

        .table-container span {
            display: block;
            font-size: 1.2em;
            margin-bottom: 10px;
            font-weight: bold;
            color: #333;
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
            width: 80px;
            height: 100%;
            box-sizing: border-box;
            padding: 8px;
            border: 2px solid #ccc;
            border-radius: 5px;
        }
        .delete-btn {
            padding: 10px 20px;
            font-size: 12px;
            font-weight: bold;
            text-transform: uppercase;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            outline: none;
            background-color: #e74c3c;
            color: white;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .delete-btn:hover {
            background-color: #c0392b;
			box-shadow: 0 2px 4px rgba(0, 0, 0, 1.4);
            
        }

        .delete-btn:active {
            background-color: #a93226;
            transform: translateY(2px);
        }

        .dossier-btn {
            padding: 10px 16px;
		    font-size: 12px;
		    font-weight: bold;
		    text-transform: uppercase;
		    border: none;
		    border-radius: 5px;
		    cursor: pointer;
			background-color: #f39c12;
            transition: background-color 0.3s ease, transform 0.3s ease;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .dossier-icon {
            width: 16px;
            width: 16px;
            
        }

        .dossier-btn:hover {
            background-color: #e67e22;
            transform: translateY(-2px);
            box-shadow: 0 2px 4px rgba(0, 0, 0, 1.4);
            
        }

        .dossier-btn:focus {
            outline: none;
            box-shadow: 0 0 0 3px rgba(243, 156, 18, 0.5);
        }

        .dossier-btn:active {
            transform: translateY(0);
            box-shadow: 0 2px 4px rgba(0, 0, 0, 1.4);
        }
    	.titre{
	        font-size: xx-large;
	    	color: #3e5c91;
	    	font-family: lato;
    	}	
		.header-page{
		    display: flex;
		    margin: 23px 0px;
		    flex-direction: row;
		    flex-wrap: nowrap;
		    align-items: center;
		    justify-content: space-between;
   		}
   		.main-btn {
	        display: inline-block;
	        padding: 10px 20px;
	        font-size: 14px;
	        font-weight: bold;
	        text-transform: uppercase;
	        color: #ffffff;
	        background-color: #007bff; 
	        border: none;
	        border-radius: 5px;
	        cursor: pointer;
	        transition: background-color 0.3s ease, transform 0.3s ease;
	        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    	}

	    .main-btn:hover {
	        background-color: #0697f8; 
	        transform: translateY(-2px); 
	    }
	
	    .main-btn:focus {
	        outline: none;
	        box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.5); 
	    }
	
	    .main-btn:active {
	        transform: translateY(0); 
	        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); 
	    }
   	    .main-btn span{
		    font-size: 10px;
		    display: flex;
   	    }
   	    .main-btn:disabled,
		.main-btn.disabled {
		    background-color: #d6d6d6;
		    color: #9e9e9e;
		    cursor: not-allowed;
		    box-shadow: none;
		    transform: none;
		    pointer-events: none; /* Prevents hover and click events */
		}
   	    .sousTitre{
		    font-size: larger;
		    color: #3e5c91;
		    font-family: lato;
   	    }
   	    .titre-container{
   	    	display: flex; 
   	    	 flex-direction: column;
   	    	 align-items: flex-start;
   	    	 justify-content: center;
   	    	 gap: 0.3em;
   	    	 
   	    }

   	    .container{
   	    	margin: 16px 36px;
   	    	margin-bottom: 100px;
   	    }


   	    textarea{
		    margin-top: 12px;
		    min-width: 93%;
		    max-width: 185px;
		    min-height: 50px;
		    max-height: 300px;
		    font-family: system-ui;
		    font-size: large;
		    padding: 7px;
		    border-radius: 9px;
		    
	    }
	    .side{
	    	width: 100%;
	    }
   	    
    </style>
</head>
	<body>
		<%@ include file="/WEB-INF/RespoHeader.jsp" %>
		<%@ include file="/WEB-INF/RespoSubNav.jsp" %>	
		<div class="container">
			<div class="header-page" > 
				<div class="titre-container">
					<span class="titre">Liste Finale et attente  </span>
					<span class="sousTitre">Veuillez supprimer tous les étudiants de la liste finale qui n'ont pas déposé leurs dossiers avant la date limite. Les premiers étudiants de la liste d'attente remplaceront chacun d'entre eux .</span>
					<span class="sousTitre"> Après cette procédure, veuillez terminer le processus en cliquant sur le bouton de fin .</span>
					
				</div>	 
				<div style="width: 15%;">
					<form action="TraitementFA" method="post" >
						<input type="hidden" name="option" value="fin">
						<input type="hidden" name="id_master" value="${id_master}"> 
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						<button type="submit" class="main-btn"   <c:if test="${ not empty ListeConvo or  not empty ListeConvocated  }">disabled</c:if>>
						    Fin du traitement
						</button>

					</form>	
				</div>
			</div>
			<div class="tables-container">
				<div class="table-container">
					<div class="side">
					<span>Liste Finale</span>
					<table  class="styled-table">
						<thead>
							<tr>
								<th>N°</th>
								<th>CNE</th>
								<th>NOM PRÉNOM</th>
								<th>Score</th>
								<th>Dossier</th>	
								<th ><img alt="delete icone" src="${pageContext.request.contextPath}/img/icons//trash.svg"  style="width: 20px; height: 20px; "></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="etudiant" items="${ListeFinale}" varStatus="status">
								<tr>
									<td>${status.index + 1}</td>
									<td>${etudiant.massar}</td>
									<td>${etudiant.nom} ${etudiant.prenom}</td>
									<td class="td-dossier" style="padding: 5px">
										${etudiant.score} 
									</td>
									<td class="td-dossier">
										<form action="DossiersEtudiant" method="post" >
											<input type="hidden" name="id_etudiant" value="${etudiant.id}">
											<input type="hidden" name="id_master" value="${id_master}"> 
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
											<button type="submit" class="dossier-btn">
											<img src="${pageContext.request.contextPath}/img/icons/folder.svg" alt="Dossier" class="dossier-icon">
											</button>
										</form>	
									</td>
								    <td  class="td-delete">
				  	                    <form action="TraitementFA" method="POST" style="display:inline;">
					                        <input type="hidden" name="id_etudiant" value="${etudiant.id}">
					                        <input type="hidden" name="id_master" value="${id_master}">
					                        <input type="hidden" name="option" value="deleteFinale">
					                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					                        <button type="submit" class="delete-btn" >X</button>
					                    </form>
				                    </td>   

								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					<div class="side">
					<c:if test="${  empty ListeAttente}">
					 	<span>La Liste d'attente est vide </span>
					 			<div style="display: flex;justify-content: center;">
									<img style="width:85%;"class="main-img" alt="no data image" src="${pageContext.request.contextPath}/img/icons/noData.svg">
								</div>
					</c:if> 
					
					<c:if test="${ not empty ListeAttente}">
						<span>Liste d'attente</span>
						<table  class="styled-table">
							<thead>
								<tr>
									<th>N°</th>
									<th>CNE</th>
									<th>NOM PRÉNOM</th>
									<th>Score</th>
									<th>Dossier</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="etudiant" items="${ListeAttente}" varStatus="status">
									<tr>
										<td>${status.index + 1}</td>
										<td>${etudiant.massar}</td>
										<td>${etudiant.nom} ${etudiant.prenom}</td>
										<td class="td-dossier" style="padding: 5px">
											${etudiant.score}       
										</td>
										<td class="td-dossier">
											<form action="DossiersEtudiant" method="post" >
												<input type="hidden" name="id_etudiant" value="${etudiant.id}">
												<input type="hidden" name="id_master" value="${id_master}"> 
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
												<button type="submit" class="dossier-btn">
												<img src="${pageContext.request.contextPath}/img/icons/folder.svg" alt="Dossier" class="dossier-icon">
												</button>
											</form>	
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					</div>
				</div>
	
				<c:if test="${ empty ListeConvo and empty ListeConvocated}">
					<c:if test="${ not empty ListeAttente}">
				
				    <span class="sousTitre">
				        Lorsque vous supprimez un étudiant de la liste finale, le premier étudiant de la liste d'attente prendra sa place. L'étudiant sera convoqué pour déposer son dossier avant une date limite que vous déterminerez, ainsi que pour recevoir les informations nécessaires au dépôt des dossiers.
				    </span>
				    </c:if>
	
				</c:if>
				
				<c:if test="${not empty ListeConvo}">	  
				<div class="table-container">
				
	
						<div class="side">
						<span>Étudiant(s) remplaçons </span>
						<span></span>	
						<table  class="styled-table">
							<thead>
								<tr>
								<th>N°</th>
								<th>CNE</th>
								<th>NOM PRÉNOM</th>
								<th>Score</th>
								<th>Dossier</th>
								   
								</tr>
							</thead>
							<tbody>
								<c:forEach var="etudiant" items="${ListeConvo}" varStatus="status">
									<tr>
										<td>${status.index + 1}</td>
										<td>${etudiant.massar}</td>
										<td>${etudiant.nom} ${etudiant.prenom}</td>
										<td class="td-dossier" style="padding: 5px">
											${etudiant.score}       
										</td>
										<td class="td-dossier">
											<form action="DossiersEtudiant" method="post" >
												<input type="hidden" name="id_etudiant" value="${etudiant.id}">
												<input type="hidden" name="id_master" value="${id_master}"> 
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
												<button type="submit" class="dossier-btn">
												<img src="${pageContext.request.contextPath}/img/icons/folder.svg" alt="Dossier" class="dossier-icon">
												</button>
											</form>	
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
						
					<c:if test="${not empty ListeConvo}">	  
						<div class="side">
							<span>Veuillez fournir les détails concernant le dépôt des dossiers pour ces étudiants de la liste d'attente </span>
				     		<form action="TraitementFA" method="post">
				     			<textarea name="message" rows="" cols=""></textarea>
						        <input type="hidden" name="id_master" value="${id_master}">
						        <input type="hidden" name="option" value="sendConvo">
						        <button type="submit" class="main-btn">Convoqué</button>
						        <input type="text" id="DatePub" value="${master.d_aff_preselection}" hidden>		        
							</form>	
						</div>
					</c:if>
				</div>
				</c:if>
				
				<c:if test="${not empty ListeConvocated}">	  
				<div class="table-container">
					<div class="side">
					<span>Étudiants convoquer</span>
					<span>Si un étudiant a déposé son dossier avant la date limite, veuillez l'ajouter à la liste finale. Sinon, veuillez le supprimer.</span>
					<table  class="styled-table">
						<thead>
							<tr>
							<th>N°</th>
							<th>CNE</th>
							<th>NOM PRÉNOM</th>
							<th>Score</th>
							<th>Dossier</th>	
							<th ><img alt="delete icone" src="${pageContext.request.contextPath}/img/icons//trash.svg"  style="width: 20px; height: 20px; "></th> 
							<th >ajouté à la liste finale</th>       
							</tr>
						</thead>
						<tbody>
							<c:forEach var="etudiant" items="${ListeConvocated}" varStatus="status">
								<tr>
									<td>${status.index + 1}</td>
									<td>${etudiant.massar}</td>
									<td>${etudiant.nom} ${etudiant.prenom}</td>
									<td class="td-dossier" style="padding: 5px">
										${etudiant.score}       
									</td>
									<td class="td-dossier">
										<form action="DossiersEtudiant" method="post" >
											<input type="hidden" name="id_etudiant" value="${etudiant.id}">
											<input type="hidden" name="id_master" value="${id_master}"> 
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
											<button type="submit" class="dossier-btn">
											<img src="${pageContext.request.contextPath}/img/icons/folder.svg" alt="Dossier" class="dossier-icon">
											</button>
										</form>	
									</td>

									<td class="td-delete">
										<form action="TraitementFA" method="POST" style="display:inline;">
											<input type="hidden" name="id_etudiant" value="${etudiant.id}">
											<input type="hidden" name="id_master" value="${master.id_master}">
											<input type="hidden" name="option" value="deleteConvocation">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
											<button type="submit" class="delete-btn">X</button>
										</form>
									</td>
									<td>
										<form>
											<input type="hidden" name="id_etudiant" value="${etudiant.id}">
											<input type="hidden" name="id_master" value="${id_master}"> 
											<input type="hidden" name="option" value="ajoute"> 
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
											<button type="submit" class="main-btn">
											 ajouté 
											</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				
				</div>	
				</c:if>
			</div>
		</div>
	</body>
</html>