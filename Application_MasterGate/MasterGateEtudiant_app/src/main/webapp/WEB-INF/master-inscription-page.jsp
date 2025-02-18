<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Master Inscription</title>
    <!-- css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/master-inscription-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/auth-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">
      
      <!-- fonts -->
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

	<%@ include file="/includes/header.jspf" %>
    <section class="m-container">
      <header class="header">
        <!-- the subheader contain title, desc, labels(links) -->
        <section class="subheader header-sub-section">
          <h3 class="header-subtitle">${master.respo.faculte.nom}</h3>
          <h1 class="header-title">Master ${master.respo.faculte.surnom} ${master.respo.faculte.uni.ville} en ${master.specialite }</h1>
          <p class="header-description">${master.information } </p>
          <div class="header-links-div">
            <a href="" class="header-link">
                <img src="img/icons/location-icon-1.svg" alt="location" >
                <strong>${master.respo.faculte.uni.ville }</strong>
            </a>
            <a href="" class="header-link">
                <img src="img/icons/calendar-icon.svg" alt="calendar">
                <strong>Publié le ${master.d_debut_inscription }</strong>
            </a>
            <a href="" class="header-link">
                <img src="img/icons/calendar-icon.svg" alt="calendar">
                <strong>Termine le ${master.d_fin_inscription }</strong>
            </a>
            <a href="" class="header-link">
                <img src="img/icons/graduate-icon.svg" alt="graduate">
                <strong>${master.specialite }</strong>
            </a>
          </div>
          
		  <form action="Inscription?idMaster=${param.idMaster}" enctype="multipart/form-data" method="post" autocomplete="off" data-tg-tour="Vous pouvez s'inscrire au master s'il n'est pas encore expire, mais vous devez etre connecte avec votre compte pour cela." data-tg-order="1">
		  	
		  	<%--error msg --%>
		  	<p class="error">${errorMsg }</p>
		  	
		  	<%--inscription msg --%>
          	<%-- <p class="warning">${inscMsg }</p> --%>

				<!-- s'inscrire -->
				<c:if test="${empty inscMsg }">
			          	<button class="insc inscrire-btn"  type="button"
			          		<c:if test="${!empty errorMsg or !empty inscMsg}">
			          			<c:out value="disabled"></c:out>
			          		</c:if> 
			          	>S'inscrire</button>
				</c:if>
          	
		          <div class ="insc-options">
						<!--telecharger le recu d'inscription -->
			          	<c:if test="${!empty inscMsg }">
			          		<a href="recu?idMaster=${param.idMaster }" class="inscrire-btn" >Reçu d'inscription</a>
			          	</c:if> 
			          	
							<!--voir les documents déposés -->
			          	<c:if test="${!empty inscMsg }">
			          		<a href="DocsDisplayer?idMaster=${param.idMaster }" class="inscrire-btn" id="docs-depos">Documents déposés</a>
			          	</c:if> 
			          	
			          	<!-- mhdi publications stuff -->
<!-- 			          	<form  action="PublicationResultats" method="post" style="display:inline;"> -->
<%-- 						    <input type="hidden" name="id_master" value="${param.idMaster}"> --%>
<!-- 						    <input type="hidden" name="type_pub" value="PRE"> -->
<%-- 						    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> --%>
<!-- 						    <button type="submit"> -->
<!-- 						    	Resultats de Préselection -->
<!-- 						    </button> -->
<!-- 						</form> -->
<!-- 						<form  action="PublicationResultats" method="post" style="display:inline;"> -->
<%-- 						    <input type="hidden" name="id_master" value="${param.idMaster}"> --%>
<!-- 						    <input type="hidden" name="type_pub" value="LFA"> -->
<%-- 						    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> --%>
<!-- 						    <button type="submit" > -->
<!-- 						    	Resultat FINALE		 -->
<!-- 						    </button> -->
<!-- 						</form> -->
						
						<a class="inscrire-btn" href="PublicationResultats?id_master=${param.idMaster }&type_pub=PRE">Resultats de Préselection</a>
						<a class="inscrire-btn" href="PublicationResultats?id_master=${param.idMaster }&type_pub=LFA">Resultat FINALE</a>

		          </div>
          	
          </form>
        </section>
        <!-- faculty icon -->
        <section class="illustration-div header-sub-section">
				<img src="${master.respo.faculte.uni.logo}" alt="logo" >
        </section>
      </header>
      <main class="main" >
        <section class="submain">
            <!-- main sections -->
            <!-- filieres admets -->
            <c:if test="${!empty master.filieresAdmet}">
	            <div class="main-section" data-tg-tour="Les candidats peuvent postuler pour l'une des filiéres suivantes" data-tg-order="2">
	                <h4 class="main-section-title">Filiéres admets</h4>
	                <h5 class="main-section-subtitle">Les candidats peuvent postuler pour l'une des filiéres suivantes :</h5>
	                <ul class="conditions">
	                	<c:forEach var="fil" items="${master.filieresAdmet}">
		                    <li class="condition">${fil.nom}  ${fil.surnom }</li>
	                    </c:forEach>
	                </ul>
	            </div>
            </c:if>
            <!-- Dates Importantes -->
            <div class="main-section" data-tg-tour="Nous vous invitons à  faire preuve de vigilance afin de ne pas manquer ces dates importantes." data-tg-order="3">
                <h4 class="main-section-title">Dates Importantes</h4>
                <h5 class="main-section-subtitle">Nous vous invitons à  faire preuve de vigilance afin de ne pas manquer ces dates importantes.</h5>
                <ul class="conditions">
                
                	<c:if test="${!empty master.d_debut_inscription }">
                    	<li class="condition">Date de début d’insription: <strong class="format-date" >${master.d_debut_inscription }</strong></li>
                    </c:if>
                    
                	<c:if test="${!empty master.d_fin_inscription }">
                    	<li class="condition">Date de Fin d’insription: <strong class="format-date" >${master.d_fin_inscription }</strong></li>
                    </c:if>
                    
                	<c:if test="${!empty master.d_aff_preselection }">
                    	<li class="condition">Date d’affichage de resultat de preselection: <strong class="format-date" >${master.d_aff_preselection }</strong></li>
                    </c:if>
                    
                	<c:if test="${!empty master.d_concours }">
                    	<li class="condition">Date de concours: <strong class="format-date" >${master.d_concours }</strong></li>
                    </c:if>
                    
                	<c:if test="${!empty master.d_aff_resultat_concours }">
	                    <li class="condition">Date d’affichage de resultat de concours: <strong class="format-date" >${master.d_aff_resultat_concours }</strong></li>
    				</c:if>
    				
                </ul>
            </div>
            <!-- Conditions -->
            <div class="main-section" data-tg-tour="Nous vous prions de  noter que pour être ligible d’acceder à ce master, vous devez répondre à certains critères." data-tg-order="4">
                <h4 class="main-section-title">Conditions</h4>
                <h5 class="main-section-subtitle">Nous vous prions de  noter que pour être ligible d’acceder à ce master, vous devez répondre à certains critères</h5>
                <ul class="conditions">
                
                	<c:if test="${!empty master.conditions.max_age && master.conditions.max_age > 0}">
	                    <li class="condition">Avoir un age inferieur à  <strong>${master.conditions.max_age }</strong></li>
					</c:if>
					    
                	<c:if test="${!empty master.conditions.max_annee_etude && master.conditions.max_annee_etude > 0}">            
                    	<li class="condition">Votre licence doit avoir été obtenue durant au moins  <strong>${master.conditions.max_annee_etude } ans</strong></li>
                    </c:if>
                    
                	<c:if test="${!empty master.conditions.note_min_semestre && master.conditions.note_min_semestre > 0}">
	                    <li class="condition">Votre note par semestre ne doit pas atteindre  <strong>${master.conditions.note_min_semestre}</strong></li>
    				</c:if>
    				
                	<c:if test="${!empty master.conditions.note_seuil && master.conditions.note_seuil > 0}">                
	                    <li class="condition">Votre moyenne générale ne doit pas atteindre <strong>${master.conditions.note_seuil}</strong></li>
    				</c:if>
    				            
                </ul>
            </div>
            <!-- Concours -->
            <div class="main-section" data-tg-tour="Priere de noter que lors de la phase de passage de concours, il faut être consient de certains informations" data-tg-order="5">
                <h4 class="main-section-title">Concours</h4>
                <h5 class="main-section-subtitle">Priere de noter que lors de la phase de passage de concours, il faut être consient de certains informations</h5>
                <ul class="conditions">
                    <li class="condition">Date de concours :   <strong class="format-date" >${master.d_concours}</strong></li>
                    <li class="condition">Date d’affichage de resultat de concours :    <strong class="format-date" >${master.d_aff_resultat_concours}</strong></li>
                    <li class="condition">Le concours sera passer à  :   <strong>  ${master.lieu_concours}</strong></li>
                </ul>
            </div>
            <!-- Remarques -->
            
            <c:if test="${!empty master.information  }">                
	            <div class="main-section" data-tg-tour="Nous vous recommandons de porter une attention particulière à cette section, car elle contient des remarques essentielles." data-tg-order="6">
	                <h4 class="main-section-title">Remarques</h4>
	                <h5 class="main-section-subtitle">Nous vous recommandons de porter une attention particulière à cette section, car elle contient des remarques essentielles.</h5>
	                <p class="remarque-text">
	                   ${master.information }
	                </p>
	            </div>
			</c:if>


        </section>


        <section class="main-side">
          <section class="illustration-div header-sub-section">
				<img src="${master.respo.faculte.uni.logo}" alt="logo">
          </section>
          <div class="header-links-div">
            <a href="" class="header-link">
                <img src="img/icons/location-icon.svg" alt="location">
                <strong>${master.respo.faculte.uni.ville }</strong>
            </a>

            <a href="" class="header-link">
                <img src="img/icons/calendar-icon.svg" alt="calendar">
                <strong>Termine le ${master.d_fin_inscription }</strong>
            </a>
            <a href="" class="header-link">
                <img src="img/icons/graduate-icon.svg" alt="graduate">
                <strong>${master.specialite }</strong>
            </a>
          </div>
		  <form action="Inscription?idMaster=${param.idMaster}" style="text-align: center;" enctype="multipart/form-data" method="post" autocomplete="off" >
	          	
		  		<%--error msg --%>
	          	<p class="error">${errorMsg }</p>
	          	
		  		<%--inscription msg --%>
				<%-- <p class="warning">${inscMsg }</p> --%>
	          	
	          	
				<c:if test="${empty inscMsg }">
			          	<button class="insc inscrire-btn" type="button"
			          		<c:if test="${!empty errorMsg or !empty inscMsg}">
			          			<c:out value="disabled"></c:out>
			          		</c:if> 
			          	>S'inscrire</button>
				</c:if>
		          
		          <div class ="insc-options">
						<!--telecharger le recu d'inscription -->
			          	<c:if test="${!empty inscMsg }">
			          		<a href="recu?idMaster=${param.idMaster }" class="inscrire-btn" >Reçu d'inscription</a>
			          	</c:if> 
			          	
							<!--voir les documents déposés -->
			          	<c:if test="${!empty inscMsg }">
			          		<a href="DocsDisplayer?idMaster=${param.idMaster }" class="inscrire-btn" id="docs-depos" >Documents déposés</a>
			          	</c:if> 
		          </div>
	        </form>
        </section>
      </main>
      
          <!-- comment section -->
<%--comment section--%>


    <style>

        .comment-section {
            background-color: #fff;
            border-radius: 10px;
/*             box-shadow: 0 4px 8px rgba(0,0,0,0.1); */
			margin-top: 2em;
            width: 400px;
            max-width: 100%;
        }

        .comment-section h2 {
            margin-top: 0;
			color: #000;
			  font-family: Montserrat;
			  font-size: 2.4rem;
			  font-weight: 500;
			  margin-bottom: 1em;
        }

        #comment-form {
            display: flex;
            flex-direction: column;
            margin-bottom: 20px;
        }

        #comment-input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
            font-size: 16px;
            font-family: Lato, sans-serif;
            box-sizing: border-box;
            max-width: 100%;
            min-width: 100%;
            min-height: 40px;
        }

        #comment-input:focus {
            outline: none;
            border-color: #007BFF;
        }

        button {
            background-color: #007BFF;
            color: #fff;
            border: none;
            padding: 10px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
            font-family: Lato, sans-serif;
        }

        button:hover {
            background-color: #0056b3;
        }

        #comments-list {
            margin-top: 20px;
        }

        .comment {
            /*background-color: #f9f9f9;*/
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
            /*box-shadow: 0 2px 4px rgba(0,0,0,0.1);*/
            display: flex;
            align-items: flex-start;
        }

        .comment img {
            border-radius: 50%;
            width: 40px;
            height: 40px;
            margin-right: 10px;
            object-fit: cover;
        }

        .comment-content {
            flex-grow: 1;
        }

        .comment-content h3 {
            font-weight: 700;
            margin: 0;
            font-size: 14px;
            color: black;
        }

        .comment-content p {
            margin: 5px 0 0 0;
            font-size: 14px;
            color: #333;
            line-height: 1.5;
        }

        .name-section {
            display: flex;
            align-items: center;
            gap: 1em;
            align-content: flex-start;
            margin-bottom: .5em;
        }

        .name-section h6 {
            margin: 0;
            font-size: 10px;
            font-weight: 500;
        }

        .collapse-button {
            text-align: center;
            margin-top: 10px;
            cursor: pointer;
            color: #007BFF;
        }

        .collapse-button:hover {
            color: #0056b3;
        }
        .comment-btn:disabled{
        	background: gray;
        }
        

    </style>

    <div class="comment-section">
        <h2>Comments</h2>
        <form id="comment-form" method="POST" action="comment?idMaster=${param.idMaster }">
            <textarea id="comment-input" required name="txt"
	           	<%--if not logged in then its disabled--%>
	            <c:if test="${sessionScope.userId == null }">
	              disabled="true" placeholder="Sign in to post a comment"
           		 </c:if>
            placeholder="Write a comment..."></textarea>
            
            <input type="hidden" name="token" value="${sessionScope.userId }">
            <button class="comment-btn" type="submit"
			<%--if not logged in then its disabled--%>
            <c:if test="${sessionScope.userId == null }">
              disabled
            </c:if>
            >Post Comment</button>
        </form>
        <div id="comments-list">
		<!--loop over comments -->
		<c:forEach items="${mcomments}" var="mcm">
        	<!-- comment -->
            <div class="comment">
                <img src="ImgDisplayerWithId?etudiantId=${mcm.idEtd }" alt="Profile Image">
                <div class="comment-content">
                    <div class="name-section">
                        <h3>${mcm.etudiantName }</h3>
                        <h6>${mcm.createdAt }</h6>
                    </div>
                    <p>${mcm.txt }</p>
                </div>
            </div>
            <!-- end of comment -->
        </c:forEach>
        </div>
        <div class="collapse-button" onclick="toggleComments()">Show More</div>
    </div>

    <script>
        function toggleComments() {
            const comments = document.querySelectorAll('#comments-list .comment');
            const button = document.querySelector('.collapse-button');
            let isCollapsed = false;

            comments.forEach((comment, index) => {
                if (index >= 3) {
                    if (comment.style.display === 'none' || comment.style.display === '') {
                        comment.style.display = 'flex';
                        button.textContent = 'Show Less';
                        isCollapsed = false;
                    } else {
                        comment.style.display = 'none';
                        button.textContent = 'Show More';

                    }
                }
            });

            if (isCollapsed) {
                button.textContent = 'Show More';
            }
        }
        
        toggleComments();
    </script>


      
    </section>
    
    

    

<%@include file="/includes/master-incription-modal.jspf" %>   
    
    
<!--     file input script -->
    <script>
    document.getElementById("docs").addEventListener("change",(e)=>{
        const selectedFile = e.target.files[0];
        const fileName = selectedFile.name;
        const fileSize = selectedFile.size;
        document.getElementById("docs-text").textContent ="";
        document.getElementById("docs-name").textContent = fileName;
        document.getElementById("docs-size").textContent = SizeConversion(fileSize);
    })
    
    // // convert the sizes
function SizeConversion(sizeInBytes) {
    const units = ['bytes', 'KB', 'MB', 'GB', 'TB'];
    let unitIndex = 0;
    let size = sizeInBytes;

    while (size >= 1024 && unitIndex < units.length - 1) {
        size /= 1024;
        unitIndex++;
    }

    return size.toFixed(2) + units[unitIndex];
}

    </script>
        
        

 <%@include file="/includes/tour.jspf" %>

    
       
</body>
</html>