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
    
	<!-- css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth-style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/generic-style.css" type="text/css">
    
    <!-- Lato -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
    <!-- Montserra -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">


	<!-- script -->
   

</head>
<body>

    <section class="conatainer" id="signup-container">
        <div class="auth-logo-div">
            <a href="home">
                <img src="img/icons/MG.svg" alt="logo">
            </a>
        </div>
        <section class="wraper">
            <h1 class="title">Sign Up</h1>
            <form action="Signup" id="signup-form"  enctype="multipart/form-data" method="post" autocomplete="off" >
                <!-- error msg frontend-->
                <div class="error-div hidden" id="error-front"></div>

				<!-- error msg backend-->
<%-- 				<c:if test="${!empty errorMsg }"> --%>
<%--                 	<div class="error-div "><c:out value="${errorMsg }"></c:out></div> --%>
<%--                 </c:if> --%>
                		    <!-- Error Modal -->            
            
		    <c:if test="${!empty errorMsg}">
		        <div id="errorModal" class="modal">
		            <div class="modal-content">
		                <span class="close">&times;</span>
		                <p>${errorMsg }</p>
		            </div>
		        </div>
		    </c:if>

		        
	        <%@include file="/includes/error-msg.jspf" %>
			
			<!-- end of error msg model(include the jspf after the modal -->
                
                
                <!-- Info personnel -->
                <section class="form-up form-part-1 current">

                    <section class="up-info">
                        <div class="info">
                            <div class="input-div">
                                <label for="nom">Nom</label>
                                <input type="text" name="nom" id="nom" class="input" placeholder="Alaoui" value="${empty nom ? '' : nom}" >
                            </div>
                            <div class="input-div">
                                <label for="prenom">Prenom</label>
                                <input type="text" name="prenom" id="prenom" class="input" placeholder="Hamid" value="${empty prenom ? '' : prenom}">
                            </div>
                        </div>

                        <div class="info">
                            <div class="input-div tooltip"  data-tooltip="Le CNIE doit contenir de 1 à 3 lettres suivies d'au moins 5 chiffres.">
                                <label for="cin">CIN</label>
                                <input type="text" name="cin" id="cni" class="input" placeholder="" value="${empty cin ? '' : cin}">
                            </div>
                            <div class="input-div">
                                <label for="telephone">Telephone</label>
                                <input type="number" name="telephone" id="telephone" class="input" placeholder="" value="${empty telephone ? '' : telephone}">
                            </div>
                        </div>

                        <div class="info">
                            <div class="input-div">
                                <label for="date_n">Date de Naissance</label>
                                <input type="date" name="date_n" id="date_n" class="input" placeholder="22 Feb 2002" value="${empty date_n ? '' : date_n}">
                            </div>
                            <div class="input-div">
                                <label for="sexe">Sexe</label>
                                <select name="sexe" id="sexe" class="sexe-select select" aria-placeholder="Male">
								<!--if the user already have chosen a faculty but just an error occurs, so get the nationalite choosed -->
	                                <c:if test="${!empty sexe}">
	                                   <option value="${ sexe}">${ sexe}</option>
	                                </c:if>
	                                    
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                </select>
                            </div>
                        </div>
                        <div class="info">
                            <div class="input-div">
                                <label for="massar">Massar</label>
                                <input type="text" name="massar" id="massar" class="input" placeholder="M16236632" value="${empty massar ? '' : massar}">
                            </div>
                            <div class="input-div">
                                <label for="nationalite">Nationalite</label>
                                <select name="nationalite" id="nationalite" class="nationalite-select select" aria-placeholder="Marocain">
                                    
										<!--if the user already have chosen a natioanlite but just an error occurs, so get the nationalite choosed -->
	                                    <c:if test="${!empty nationalite}">
	                                    	<option value="${ nationalite}">${ nationalite}</option>
	                                    </c:if>
                                    
                                    <option value="marocain">Marocain</option>
                                    <option value="algerien">Algérien</option>
                                    <option value="tunisien">Tunisien</option>
                                    <option value="égyptien">égyptien</option>
                                    <option value="Sénégalais">Sénégalais</option>
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
                                    <option value="Nigérien">Nigérien</option>
                                    <option value="soudanais">Soudanais</option>
                                    <option value="tchadien">Tchadien</option>
                                    <option value="togolais">Togolais</option>
                                    <option value="Yéménite">Yéménite</option>
                                    <option value="Américain">Américain</option>
                                    <option value="britannique">Britannique</option>
                                    <option value="canadien">Canadien</option>
                                    <option value="chinois">Chinois</option>
                                    <option value="Français">Français</option>
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
                            <input type="email" name="email" id="email" class="input" placeholder="Votre email" value="${empty email ? '' : email}">
                        </div>
                        <div class="up-connection-div input-div tooltip" data-tooltip="Le mot de passe doit comporter au moins 8 chars, au moins une lettre minuscule, une majuscule, un chiffre et un caractère spécial (parmi !@#$%^&*).">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password" class="input" value="${empty password ? '' : password}">
                            
					        <span id="toggle-password" class="toggle-password">
					            <img src="img/icons/show-pass.svg" id="toggle-password"></img>
					        </span>
                        </div>
                    </section>
                </section>
                <c:out value="${fac_nom }"></c:out>
                <!-- Info acadÃ©miques  -->
                <section class="form-up form-part-2 " id="info-academ">

                    <section class="up-info">
                        <div class="info">
                            <div class="input-div">
                                <label for="fac">Faculté</label>                            
                                <%--si en a un erreur dans l'importation des facultes alors la faculte va etre entré manuellement par l'user 
								<c:if test="${empty facultes}">
                                	<input type="text" name="fac" id="fac" class="input" placeholder="FacultÃ© des sciences MeknÃ¨s">
                            	</c:if>
                            	--%>
                            	<c:if test="${!empty facultes }">
                            		<select name="fac" id="fac" class="select up-connection-div">
										<!--if the user already have chosen a faculty but just an error occurs, so get the nationalite choosed -->
	                                    <c:if test="${!empty facNom && !empty facId}">
	                                    	<option value="${facId }">${ facNom}</option>
	                                    </c:if>
                                    
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
                                <input type="date" name="date_premiere_inscription" id="date_premiere_inscription" class="input" placeholder="12 Feb 2000" value="${empty date_premiere_inscription ? '' : date_premiere_inscription}">
                            </div>
							<div class="input-div">
                                <label for="date_graduation">Date de Graduation</label>
                                <input type="date" name="date_graduation" id="date_graduation" class="input" placeholder="12 Feb 2000" value="${empty date_graduation ? '' : date_graduation}">
                            </div>
                            
                        </div>
                        
						<div class="info">
							<div class="input-div">
                                <label for="date_bac">Date Bac</label>
                                <input type="date" name="date_bac" id="date_bac" class="input" placeholder="" value="${empty date_bac ? '' : date_bac}">
                            </div>
						</div>
						
                        <div class="info">
                            <div class="input-div">
                                <label for="fil-bac">Filiére Bac</label>
                                <input type="text" name="fil-bac" id="fil-bac" class="input" placeholder="PC" value="${empty fil_bac ? '' : fil_bac}">
                            </div>
                        </div>
                        
						<div class="info">
                            <div class="input-div">
                                <label for="fil-license">Filiére License</label>
                                <%--si en a un erreur dans l'importation des filieres alors la filiere va etre entré manuellement par l'user 
								<c:if test="${empty filieres}">
                                	<input type="text" name="fil-license" id="fil-license" class="input" placeholder="SMI">
                            	</c:if>
                            	--%>
                            	<c:if test="${!empty filieres }">
                            		<select name="fil-license" id="fil-license" class="select up-connection-div">
                            		
										<!--if the user already have chosen a filiere but just an error occurs, so get the nationalite choosed -->
	                                    <c:if test="${!empty filLicenceNom  && !empty filLicenseId}">
	                                    	<option value="${ filLicenseId}"> ${ filLicenceNom} </option>
	                                    </c:if>

	                                    
										<!--  get all filieres -->
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
                                <input type="number" name="moy-bac" id="moy-bac" class="input"  step="0.01" value="${empty moy_bac ? '' : moy_bac}">    
                            </div>
                            <div class="input-div">
                                <label for="moyenne_lic">Moyene License</label>
                                <input type="number" name="moy-lic" id="moy-lic" class="input" step="0.01" value="${empty moy_lic ? '' : moy_lic}">    
                            </div>
                        </div>

                        <div class="info">
                            <div class="input-div">
                                <label for="n_s1">Note S1</label>
                                <input type="number" name="n_s1" id="n_s1" class="input" step="0.01" value="${empty n_s1 ? '' : n_s1}">
                            </div>
                            <div class="input-div">
                                <label for="n_s2">Note S2</label>
                                <input type="number" name="n_s2" id="n_s2" class="input" step="0.01" value="${empty n_s2 ? '' : n_s2}">
                            </div>
                            <div class="input-div">
                                <label for="n_s3">Note S3</label>
                                <input type="number" name="n_s3" id="n_s3" class="input" step="0.01" value="${empty n_s3 ? '' : n_s3}">
                            </div>
                        </div>
                        <div class="info">
                            <div class="input-div">
                                <label for="n_s4">Note S4</label>
                                <input type="number" name="n_s4" id="n_s4" class="input" step="0.01" value="${empty n_s4 ? '' : n_s4}">
                            </div>
                            <div class="input-div">
                                <label for="n_s5">Note S5</label>
                                <input type="number" name="n_s5" id="n_s5" class="input" step="0.01" value="${empty n_s5 ? '' : n_s5}">
                            </div>
                            <div class="input-div">
                                <label for="n_s6">Note S6</label>
                                <input type="number" name="n_s6" id="n_s6" class="input" step="0.01" value="${empty n_s6 ? '' : n_s6}">
                            </div>
                        </div>

                    </section>

                </section>
                <!-- Documents acadÃ©miques -->
                <section class="form-up form-part-3 " id="doc-academ">
<!--                     <section class="up-connection"> -->
<!--                         <div class="up-connection-div input-div"> -->
<!--                             <label for="docs">Veuillez Entrer Vos Documents ( Format pdf < 10MB )  -->
<!--                                 <input type="file" name="docs" id="docs" class="input" placeholder="" accept="application/pdf"> -->
<!--                                 <div class="upload-div input"> -->
<!--                                     <span class="upload-text" id="docs-text"> No file uploaded </span> -->
<!--                                     <div class="upload-subdiv"> -->
<!--                                         <span class="upload-file-name" id="docs-name"></span> -->
<!--                                         <span class="upload-file-size" id="docs-size"></span> -->
<!--                                     </div> -->
<!--                                     <img src="img/icons/upload.svg" alt="upload"> -->
<!--                                 </div> -->
<!--                             </label> -->
<!--                             <span class="docs-msg">Les documents doivent étre bien scannés et comportant: L'origine du Baccalauréat, L'origine du diplome, La carte d'identité national et les relevés de note.</span> -->
<!--                         </div> -->
<!--                     </section> -->
				<!-- image input file -->
					<section class="up-connection">
	                    <div class="up-connection-div input-div">
	                        <label for="photo">Veuillez Entrer Votre Photo 
	                            <input type="file" name="photo" id="photo" class="input" placeholder="" accept="image/png, image/webp, image/jpeg" required>
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
                    <!--  
                        <button  class="btn prev submit hidden" id="prev">
                            <img src="img/icons/left-icon.svg" alt="sumbit">
                        </button>
                    -->
                        <button type="submit" class="btn submit " id="submit" >Submit</button>
                        <!--  
                        <button  class="btn next submit" id="next">
                            <img src="img/icons/right-icon.svg" alt="sumbit">
                        </button>
                        -->
                    </div>
                    <p class="footer-text">Already have an account ? <a href="Signin" class="link signin-link sign-link">Sign in </a></p>
                </section>
            </form>
        </section>
    </section>
    
    
    
   
        <script>
//     	document.getElementById("email").focus();
    	
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
    	
        // Function to remove duplicate options from the select element
        function removeDuplicateOptions(selectElement) {
            const options = selectElement.options;
            const values = new Set();
            
            for (let i = 0; i < options.length; i ++) {
                const option = options[i];
                if (values.has(option.value)) {
                    selectElement.remove(i);
                } else {
                    values.add( option.value );
                }
            }
        }

        // Get the select element
        const sexeSelect = document.getElementById('sexe');
        const facSelect = document.getElementById('fac');
        const filSelect = document.getElementById('fil-license');
        const natSelect = document.getElementById('nationalite');

        
        
        // Call the function to remove duplicates
        removeDuplicateOptions(sexeSelect);
        removeDuplicateOptions(facSelect);
        removeDuplicateOptions(filSelect);
        removeDuplicateOptions(natSelect);
        
        
        //make the cin uppercased
        document.getElementById('signup-form').addEventListener("submit", ()=>{
        	document.getElementById('cni').value = document.getElementById('cni').value.toUpperCase();
        })

    </script>
   

<!--     <script src="js/signup-script.js" ></script> -->
<script type="text/javascript">

const imageTypes = ["image/jpeg","image/webp","image/png"]
document.getElementById("photo").addEventListener("change",(e)=>{
    const selectedFile = e.target.files[0];
    const fileName = selectedFile.name;
    const fileSize = selectedFile.size;
    document.getElementById("photo-text").textContent ="";
    document.getElementById("photo-name").textContent = fileName;
    document.getElementById("photo-size").textContent = SizeConversion(fileSize);
    
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


document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('signup-form');
    const inputs = form.querySelectorAll('input');
    const submitButton = document.getElementById('submit');

    function validateInputs() {
        let allValid = true;
        inputs.forEach(input => {
            if (input.type === 'file') {
                if (!input.files.length) {
                    allValid = false;
                }
            } else if (!input.value.trim()) {
                allValid = false;
            }
        });
        submitButton.disabled = !allValid;
    }

    inputs.forEach(input => {
        input.addEventListener('input', validateInputs);
        if (input.type === 'file') {
            input.addEventListener('change', validateInputs);
        }
    });

    // Initial check
    validateInputs();
});

</script>

</body>
</html>