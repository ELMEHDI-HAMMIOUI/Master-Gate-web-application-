<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="master.beans.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<!--     <meta charset="UTF-8"> -->
<!--     <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<!--     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Sign up</title>
    <link rel="stylesheet" href="css/auth-style.css">
    <link rel="stylesheet" href="css/generic-style.css">
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
    <section class="conatainer" id="signup-container">
        <div class="auth-logo-div">
            <a href="header.jsp">
                <img src="img/icons/monmstr-logo-2.svg" alt="logo">
            </a>
        </div>
        <section class="wraper">
            <h1 class="title">Sign Up</h1>
            <form action="Signup" class=""  enctype="multipart/form-data" method="post" autocomplete="off" >
                <!-- error msg frontend-->
                <div class="error-div hidden"></div>
				<!-- error msg backend-->
				<c:if test="${!empty errorMsg }">
                	<div class="error-div "><c:out value="${errorMsg }"></c:out></div>
                </c:if>
                <!-- Info personnel -->
                <section class="form-up form-part-1 current">

                    <section class="up-info">
                        <div class="info">
                            <div class="input-div">
                                <label for="nom">Nom</label>
                                <input type="text" name="nom" id="nom" class="input" placeholder="Alaoui">
                            </div>
                            <div class="input-div">
                                <label for="prenom">Prenom</label>
                                <input type="text" name="prenom" id="prenom" class="input" placeholder="Hamid">
                            </div>
                        </div>

                        <div class="info">
                            <div class="input-div">
                                <label for="cin">CIN</label>
                                <input type="text" name="cin" id="cni" class="input" placeholder="DO12663">
                            </div>
                            <div class="input-div">
                                <label for="telephone">Telephone</label>
                                <input type="number" name="telephone" id="telephone" class="input" placeholder="0634657933">
                            </div>
                        </div>

                        <div class="info">
                            <div class="input-div">
                                <label for="date_n">Date de Naissance</label>
                                <input type="date" name="date_n" id="date_n" class="input" placeholder="22 Feb 2002">
                            </div>
                            <div class="input-div">
                                <label for="sexe">Sexe</label>
                                <select name="sexe" id="sexe" class="sexe-select select" aria-placeholder="Male">
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                </select>
                            </div>
                        </div>
                        <div class="info">
                            <div class="input-div">
                                <label for="massar">Massar</label>
                                <input type="text" name="massar" id="massar" class="input" placeholder="M16236632">
                            </div>
                            <div class="input-div">
                                <label for="nationalite">NationalitÃ©</label>
                                <select name="nationalite" id="nationalite" class="nationalite-select select" aria-placeholder="Marocain">
                                    <option value="marocain">Marocain</option>
                                    <option value="algerien">AlgÃ©rien</option>
                                    <option value="tunisien">Tunisien</option>
                                    <option value="Ã©gyptien">Ãgyptien</option>
                                    <option value="sÃ©nÃ©galais">SÃ©nÃ©galais</option>
                                    <option value="malien">Malien</option>
                                    <option value="mauritanien">Mauritanien</option>
                                    <option value="comorien">Comorien</option>
                                    <option value="djiboutien">Djiboutien</option>
                                    <option value="gabonais">Gabonais</option>
                                    <option value="ivoirien">Ivoirien</option>
                                    <option value="kenyan">Kenyan</option>
                                    <option value="libyen">Libyen</option>
                                    <option value="malgache">Malgache</option>
                                    <option value="marocain">Marocain</option>
                                    <option value="mauritanien">Mauritanien</option>
                                    <option value="nigÃ©rien">NigÃ©rien</option>
                                    <option value="soudanais">Soudanais</option>
                                    <option value="tchadien">Tchadien</option>
                                    <option value="togolais">Togolais</option>
                                    <option value="yÃ©mÃ©nite">YÃ©mÃ©nite</option>
                                    <option value="amÃ©ricain">AmÃ©ricain</option>
                                    <option value="britannique">Britannique</option>
                                    <option value="canadien">Canadien</option>
                                    <option value="chinois">Chinois</option>
                                    <option value="franÃ§ais">FranÃ§ais</option>
                                    <option value="allemand">Allemand</option>
                                    <option value="italien">Italien</option>
                                    <option value="japonais">Japonais</option>
                                    <option value="espagnol">Espagnol</option>
                                </select>
                            </div>
                        </div>

                    </section>

                    <section class="up-connection">
                        <div class="up-connection-div input-div">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" class="input" placeholder="Alaoui32@gmail.com">
                        </div>
                        <div class="up-connection-div input-div">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password" class="input">
                        </div>
                    </section>
                </section>
                <!-- Info acadÃ©miques  -->
                <section class="form-up form-part-2 hidden" id="info-academ">

                    <section class="up-info">
                        <div class="info">
                            <div class="input-div">
                                <label for="fac">FacultÃ©</label>                            
                                <%--si en a un erreur dans l'importation des facultes alors la faculte va etre entré manuellement par l'user 
								<c:if test="${empty facultes}">
                                	<input type="text" name="fac" id="fac" class="input" placeholder="FacultÃ© des sciences MeknÃ¨s">
                            	</c:if>
                            	--%>
                            	<c:if test="${!empty facultes }">
                            		<select name="fac" id="fac" class="select up-connection-div">
	                            		<c:forEach items="${facultes}" var="faculte">
	                            			<option value="${faculte.id }"><c:out value="${faculte.nom }"></c:out></option>
	                           			</c:forEach>
	                           		</select>
								</c:if>                            
                            </div>

                        </div>

                        <div class="info">
                            <div class="input-div">
                                <label for="date_premiere_inscription">Date 1ere Inscription</label>
                                <input type="date" name="date_premiere_inscription" id="date_premiere_inscription" class="input" placeholder="12 Feb 2000">
                            </div>
							<div class="input-div">
                                <label for="date_graduation">Date de Graduation</label>
                                <input type="date" name="date_graduation" id="date_graduation" class="input" placeholder="12 Feb 2000">
                            </div>
                            
                        </div>
                        
						<div class="info">
							<div class="input-div">
                                <label for="date_bac">Date Bac</label>
                                <input type="date" name="date_bac" id="date_bac" class="input" placeholder="">
                            </div>
						</div>
						
                        <div class="info">
                            <div class="input-div">
                                <label for="fil-bac">FiliÃ¨re Bac</label>
                                <input type="text" name="fil-bac" id="fil-bac" class="input" placeholder="PC">
                            </div>
                        </div>
                        
						<div class="info">
                            <div class="input-div">
                                <label for="fil-license">FiliÃ¨re License</label>
                                <%--si en a un erreur dans l'importation des filieres alors la filiere va etre entré manuellement par l'user 
								<c:if test="${empty filieres}">
                                	<input type="text" name="fil-license" id="fil-license" class="input" placeholder="SMI">
                            	</c:if>
                            	--%>
                            	<c:if test="${!empty filieres }">
                            		<select name="fil-license" id="fil-license" class="select up-connection-div">
	                            		<c:forEach items="${filieres}" var="filiere">
	                            			<option value="${filiere.id_filiere }">${filiere.nom }</option>
	                           			</c:forEach>
	                           		</select>
								</c:if>
                            		
                            </div>
                        </div>
                        
                        <div class="info">
                            <div class="input-div">
                                <label for="moyenne_bac">Moyene Bac</label>
                                <input type="number" name="moy-bac" id="moy-bac" class="input" placeholder="16.2">    
                            </div>
                            <div class="input-div">
                                <label for="moyenne_lic">Moyene License</label>
                                <input type="number" name="moy-lic" id="moy-lic" class="input" placeholder="13.5">    
                            </div>
                        </div>

                        <div class="info">
                            <div class="input-div">
                                <label for="n_s1">Note S1</label>
                                <input type="number" name="n_s1" id="n_s1" class="input" placeholder="">
                            </div>
                            <div class="input-div">
                                <label for="n_s2">Note S2</label>
                                <input type="number" name="n_s2" id="n_s2" class="input" placeholder="">
                            </div>
                            <div class="input-div">
                                <label for="n_s3">Note S3</label>
                                <input type="number" name="n_s3" id="n_s3" class="input" placeholder="">
                            </div>
                        </div>
                        <div class="info">
                            <div class="input-div">
                                <label for="n_s4">Note S4</label>
                                <input type="number" name="n_s4" id="n_s4" class="input" placeholder="">
                            </div>
                            <div class="input-div">
                                <label for="n_s5">Note S5</label>
                                <input type="number" name="n_s5" id="n_s5" class="input" placeholder="">
                            </div>
                            <div class="input-div">
                                <label for="n_s6">Note S6</label>
                                <input type="number" name="n_s6" id="n_s6" class="input" placeholder="">
                            </div>
                        </div>

                    </section>

                </section>
                <!-- Documents acadÃ©miques -->
                <section class="form-up form-part-3 hidden" id="doc-academ">
                    <section class="up-connection">
                        <div class="up-connection-div input-div">
                            <label for="docs">Veuillez Entrer Vos Documents ( Format pdf < 10MB ) 
                                <input type="file" name="docs" id="docs" class="input" placeholder="">
                                <div class="upload-div input">
                                    <span class="upload-text" id="docs-text"> No file uploaded </span>
                                    <div class="upload-subdiv">
                                        <span class="upload-file-name" id="docs-name"></span>
                                        <span class="upload-file-size" id="docs-size"></span>
                                    </div>
                                    <img src="img/icons/upload.svg" alt="upload">
                                </div>
                            </label>
                            <span class="docs-msg">Les documents doivent Ãªtre bien scannÃ©s et comportant: L'origine du BaccalaurÃ©at, L'origine du diplome, La carte d'identitÃ© national et les relevÃ©s de note.</span>
                        </div>
                    </section>
				<!-- image input file -->
					<section class="up-connection">
	                    <div class="up-connection-div input-div">
	                        <label for="photo">Veuillez Entrer Votre Photo 
	                            <input type="file" name="photo" id="photo" class="input" placeholder="">
	                            <div class="upload-div input">
	                                <span class="upload-text" id="photo-text"> No file uploaded </span>
	                                <div class="upload-subdiv">
	                                    <span class="upload-file-name" id="photo-name"></span>
	                                    <span class="upload-file-size" id="photo-size"></span>
	                                </div>
	                                <img src="img/icons/upload.svg" alt="upload">
	                            </div>
	                        </label>
	                    </div>
	                </section>
                </section>
                <!--  -->
                <!--  -->
                <section class="form-down">
                    <div class="control-div">
                        <button  class="btn prev submit hidden" id="prev">
                            <img src="img/icons/left-icon.svg" alt="sumbit">
                        </button>
                        <button type="submit" class="btn submit hidden" id="submit" disabled>Submit</button>
                        <button  class="btn next submit" id="next">
                            <img src="img/icons/right-icon.svg" alt="sumbit">
                        </button>
                    </div>
                    <p class="footer-text">Already have an account ? <a href="signin.jsp" class="link signin-link sign-link">Sign in </a></p>
                </section>
            </form>
        </section>
    </section>
    <script src="js/signup-script.js" defer></script>
</body>
</html>