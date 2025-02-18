<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Profile</title>

    <!-- Lato -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
    <!-- Montserra -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
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
.hidden {
    display: none !important;
    transition: all ease 200ms !important;
}
input{
    font-family: Lato, sans-serif;
}

input, select, .input{
    box-sizing: border-box;
    padding: 16px;
    /* background-color: var(--input-back, #FFF9EB); */
    background-color: #ffff;
    color: var(--black, #121211);
    border-radius: 10px;
    border: 2px solid #E8E8E8;
    width: 100%;
    font-size: 1rem;
    transition: all ease 300ms;
}
select:focus, input:focus{
    outline: none;
}
input:hover, select:hover{
    -webkit-box-shadow: 0 0 0 4px var(--scnd-clr-l);
    box-shadow: 0 0 0 4px var(--scnd-clr-l);
}
input::selection, select::selection{
    -webkit-box-shadow: 0 0 0 4px var(--scnd-clr-l);
    box-shadow: 0 0 0 4px var(--scnd-clr-l);
    background-color: var(--pr-clr-l);
}
input:focus, select:focus{
    /* border: 2px solid var(--scnd-clr-l); */
    -webkit-box-shadow: 0 0 0 4px var(--scnd-clr-l);
    box-shadow: 0 0 0 4px var(--scnd-clr-l);
}
input[type=number] {
    -moz-appearance: textfield;
  }

label{
    font-size: 0.875rem;/*14px*/
    font-weight: 500;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    color: var(--black, rgba(18, 18, 17, 0.50));

}
select{
    appearance: none; /* Hide default arrow in modern browsers */
    -webkit-appearance: none; /* Hide default arrow in older versions of Safari */
    -moz-appearance: none; /* Hide default arrow in older versions of Firefox */
    background-image: url('chevron-down.svg'); /* Add your custom arrow image */
    background-position: calc(100% - 16px) center; /* Adjust arrow position */
    background-repeat: no-repeat; /* Ensure the arrow is only shown once */
}
select:disabled{
    background-image: none;
}




/* p-main-section p-main-section-1 */
.p-container{
    display: flex;
    gap: 2em;
    padding: 2em;
}
.p-side{
    width: 5%;
    flex-grow: 1;
    display: flex;
    flex-direction: column;
    align-items: start;
    gap: 2em;
    padding: 1em;
    border: 1px solid var(--grey, #E1E1E1);
    border-radius: 10px;
    position: sticky;
    top: 0;
    overflow-y: auto;
    overflow-x: hidden;
    padding-bottom: 0;
    img{
        width: 150px;
        
    }
}

.p-side-options{
    font-size: .9rem;
    display: flex;
    flex-direction: column;
    align-items: start;
    gap: 1.5em;
    width: 100%;
}
.p-side-options .selected{
    font-weight: 600;
    background-color: var(--pr-clr-l);        
}
h2{
	    font-size: 22px;
}
.p-side-option{
    transition: all ease 200ms;
    color: var(--black);
    text-decoration: none;
    font-family: Montserrat;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
    padding: .8em 1em;
    border-radius: 10px;
    width: 100%;
    display: flex;
    align-items: center;
    gap: 1em;
}
.p-side-option:not(.selected):hover {
    background-color: #edeae6;
}
.p-side-option img{
    width: 24px;
   	margin-bottom: 15px;
}
.p-side-photo-div{
    width: 100%;
}
.p-side-photo-div, .p-side-photo-div img{
    border-radius: 10px;
    
}
.p-side-photo-div span{
        font-size: x-large;
    color: black;
    
}
.p-main{
    flex-grow: 1;
}
.p-main-section{
    padding: 2em;
    border: 1px solid var(--grey, #E1E1E1);
    border-radius: 10px;
}
.p-main-section-2{
    margin-bottom: 0;
}
.p-info-wraper{
    display: flex;
	justify-content: center;
    gap: 1em;
    margin-bottom: 2em;
}
.p-main-section-title{
    color: #000;
    font-family: Montserrat;
    font-size: 2.438rem;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
    margin-bottom: 2em;
}
.p-info{
	display: flex;
    flex-direction: column;
    justify-content: start;
    gap: 8px;
    width: 100%;
}
.control-btns{
    display: flex;
    flex-direction: column;
    width: fit-content;
    margin-top: -1em;
    margin-bottom: 3em;
    gap: 1em;
    width: 100%;
    transition: all ease 200ms;
}
.btn{
    font-size: .7rem;
    font-family: Lato, sans-serif;
    font-weight: 600;
    padding: 1.3em;
    color: var(--black);
    border-radius: 35px;
    border: none;
    transition: all ease 200ms;
    width: 100%;
    text-decoration: none;
    text-align: center;
}
.btn:hover{
    cursor: pointer;
}
.edit-btn{
    background-color: var(--pr-clr-l);
}
.edit-btn:hover{
    background-color: #f0d078;
}
.delete-btn{
    background-color: var(--alert-clr);
    color: white;
}
.delete-btn:hover{
    background-color: #d84350;   
}
/* customize the input file */
input[type=file]{
    display: none;
}
.upload-div{
    margin-top: 16px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-family: Lato, sans-serif;
    font-weight: 600;
}
.upload-div:hover{
    cursor: pointer;
}
.upload-div span{
    font-family: Lato, sans-serif;
    font-weight: 600; 
}
.upload-subdiv{
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 0 auto 0 0;
    gap: 8px;
    max-width: 90%;
    overflow: hidden;
}
.photo-msg{
    font-size: 13px;
    display: flex;
    align-items: start;
    gap: 4px;
    margin-top: -16px;
    margin-bottom: 32px;
}
.photo-msg::before{
    content: url('alert-circle.svg');
    display: inline-block;
}
/* error div */
.error-div{
    padding: 8px;
    font-weight: 600;
    color: #A66;
    background-color:  #FEE;
    border: 1px solid #EDD;
    text-align: center;
    text-transform: capitalize;
    margin-top: -1em;
    margin-bottom: 16px;
    border-radius: 5px;
}
.upload-label .upload-icon{
    width: auto;
}
.p-side-photo-div{
    position: relative;
}
.upload-img{
    position: absolute;
    display: flex;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    width: 80%;
    height: 64px;
    margin: auto;
    /* flex-direction: column; */
}
.upload-div{
    /* flex-direction: column-reverse; */
    text-align: center;
    gap: .5em;
    padding: .5em;
}
/* customizing the control-btns */
/* settings */
.settings-btn{
    display: flex;
    align-items: center;
    position: relative;
    background: none;
    border: none;  
}
/* the chevron down in the settings btn is hidden by default except for large screens > 790px */
.settings-chevron{
    display: none;
}
@media only screen and (min-width: 790px){
    .settings-chevron{
        display: flex;
        margin: auto .5em auto auto;
    }
}

 @media only screen and (max-width: 1060px){
    .p-container{
        flex-direction: column;
    }
    .p-side{
        flex-direction: column-reverse;
        height: auto;
        width: 100%;
        position: inherit;
        border: none;
        padding: 0;
    }
    .p-side-options{
        flex-direction: row;
    }
    .p-side-photo-div{

        width: 25%;
        margin: 0 auto;
        min-width: 200px;
    }
    .p-side-photo-div img{
        width: 100%;
        
    }
    .p-side-option{
        justify-content: center;
    }
    /*ordering the side components  */
    .p-side-options{
        order: 3;
    }
    .control-btns{
        order: 2;
    }
    .p-side-photo-div{
        order: 1;
    }
 }


 @media only screen and (max-width: 700px){
    .p-info-wraper{
        flex-wrap: wrap;
    }
    .p-info{
        min-width: 40%;
        flex-grow: 1;
    }
    .p-main-section-title{
        font-size: 1.5rem;
    }
    label{
        font-size: .775rem;
    }
    input, select, .input{
        font-size: .85rem;
    }
    .p-side-options {
        font-size: .75rem;
    }
    .p-side-option img {
        width: 20px;
    }
    .p-side-option{
        justify-content: center;
        padding: .5em 0em;
        flex-direction: column;
        gap: .5em;

    }
    
}
@media only screen and (max-width: 435px){
    .p-container{
        gap: 1em;
    }
    .p-side-option{
        gap: .3em;
    }
    .p-side-options{
        gap: 1em;
    }
    .p-side-option{
        font-size: .6rem;
    }
    .p-info{
        width: 100%;
    }
    .p-main-section {
        padding: 1em;
        margin-bottom: 1em;
    }       
    .p-container{
        padding: 1em;
    }
    .p-side-photo-div{
        padding: .5em;
    }
    .p-side-photo-div img{
        width: 100%;
    }
    .p-main-section-title {
        font-size: 1.3rem;
    }
    .p-side-options{
        font-size: .65rem;
    }
}
@media screen and (max-width: 250px) {
    .p-side-option span{
        display: none;
    }
}
.card {
    margin-top: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.6);
    padding: 20px;
    width: 100%;
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  .form-group label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
        margin-top: 10px;
  }
  
  .form-group input {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    
    
  }
  
  .btn-submit {
     background-color: #FFDF8A;
    color: #110202;
    border: none;
    border-radius: 4px;
    padding: 10px 20px;
    cursor: pointer;
    width: 100%;
    font-size: medium;

  }
  
  .btn-submit:hover {
    background-color: #F0D078;
  }
  a{
  	text-decoration:none;
  }
  .chevron-down{
  	transform: rotate(180deg);
}
/* tooltip things */
.p-info{
    position: relative;
}
.p-info::before {
    content: 'Vous pouvez contacter MasterGate@gmail.com pour toute réclamation concernant des données incorrectes.' ;
    border-radius: 4px;
    background-color: rgb(44, 43, 43);
    color: white;
    font-family: Lato, sans-serif;
    padding: 5px 10px;
    width: auto;
    position: absolute;
    z-index: 1;
    top: -23%;
    left: 18%;
    opacity: 0;
    visibility: hidden;
    transition: opacity 0.3s, visibility 0.3s;
    font-size: .7rem;
    text-align: center;
    display: flex;
    align-items: center;
    gap: 4px;
}



#erreur {
    background-color: #f8d7da;
    color: #721c24;
    border: 1px solid #f5c6cb;
    border-radius: 4px;
    padding: 10px;
    margin-bottom: 20px;
    width:100%;
    font-size: larger;
}
	
.p-info:hover::before {
    opacity: 1;
    visibility: visible;
}
.btn {
  padding: 10px 20px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width:6em;
  background-color: #4CAF50;
  color: #fff;
}
.btnC {
  padding: 10px 20px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width:6em;
  background-color: #DD3C4B;
  color: #fff;
}



    	
    </style>
</head>

<body>


    <section class="p-container">
            <section class="p-side">
                  <div class="p-side-photo-div">
                    <!-- profile image link -->
                    <a href="${respo.web_site}">
                    <img src="${respo.logo_uni }" alt="profile-pic">
         			<br>
         			<span>${respo.nom_uni }  </span> 
         			</a>
                </div>
                <!-- error message -->
           
       
                <!-- control btns -->

                <section class="control-btns hidden" id="control-btns">
                    	
                </section>
            </section>
            <section class="p-main">
                <!-- info personel -->
                <section class="p-main-section p-main-section-1">
                   <h3 class="p-main-section-title">Responsable Profile</h3>
<form action="AdminRespoProfile" method="post">
    <section class="p-info-wraper">
        <div class="p-info">
            <label for="email">Email</label>
            <input type="text" name="email" id="email" class="input" value="${respo.email}" class="tooltip">
        </div>
        <div class="p-info">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" class="input" value="${respo.password}">
        </div>
    </section>
    <section class="p-info-wraper">
        <div class="p-info">
            <label for="universite">Université</label>
            <input type="text" name="universite" id="universite" class="input" value="${respo.nom_uni}">
        </div>
        <div class="p-info">
            <label for="faculte">Faculté</label>
            <input type="text" name="faculte" id="faculte" class="input" value="${respo.nom_fac}">
        </div>
    </section>
    <section class="p-info-wraper">
        <div class="p-info">
            <label for="ville">Ville</label>
            <input type="text" name="ville" id="ville" class="input" value="${respo.ville}">
        </div>
        <div class="p-info">
            <label for="website">Site Web d'université</label>
            <input type="text" name="website" id="website" class="input" value="${respo.web_site}">
        </div>
    </section>
    <input type="hidden" name="id_respo" value="${respo.id_respo}">
    <div> 
    <button type="submit" class="btn">Modifier</button>
    <a href="AdminHome"><button class="btnC" type="button">Retour</button></a>
    </div>
</form>

                </section>
			</section>
    </section>


  


    <script>
   
        // show the settings options
        document.getElementById("settings-btn").addEventListener("click",(e)=>{
            e.preventDefault();
            document.querySelector(".settings-chevron").classList.toggle("chevron-down");
            document.getElementById("control-btns").classList.toggle("hidden");
        })
        document.getElementById("nom").addEventListener("mouseover",(e)=>{
            console.log("hello")
            e.target.classList.add("tooltiped");
        })
        
      const editPasswordBtn = document.getElementById("edit-password-btn");
	  const passwordCard = document.querySelector(".card");
	
	  editPasswordBtn.addEventListener("click", function() {
	    passwordCard.classList.toggle("hidden");
	  });
	
	  document.addEventListener("click", function(event) {
	    if (!passwordCard.contains(event.target) && event.target !== editPasswordBtn) {
	      passwordCard.classList.add("hidden");
	    }
	  });
	  document.getElementById('password-form').addEventListener('input', function(event) {
		  const newPassword = document.getElementById('new-password').value;
		  const confirmationPassword = document.getElementById('confirmationNew-password').value;
		  const btnSubmit = document.getElementById('btn-submit');
		  
		  // Check if the passwords match
		  if (newPassword !== confirmationPassword) {
		    // If passwords don't match, add red border to both input fields
		    document.getElementById('new-password').style.borderColor = '#ee0000';
		    document.getElementById('confirmationNew-password').style.borderColor = '#ee0000';
		    document.getElementById('new-password').style.boxShadow = '0 4px 8px #ff7373';
		    document.getElementById('confirmationNew-password').style.boxShadow = '0 4px 8px #ff7373';
		    btnSubmit.style.backgroundColor = "#ccc";
		    btnSubmit.disabled = true; 
		  } else {
		    // If passwords match, remove red border and enable submit button
		    document.getElementById('new-password').style.borderColor = '#ccc';
		    document.getElementById('confirmationNew-password').style.borderColor = '#ccc';
		    document.getElementById('new-password').style.boxShadow = 'none';
		    document.getElementById('confirmationNew-password').style.boxShadow = 'none';
		    
		    btnSubmit.disabled = false; 
		    btnSubmit.style.backgroundColor = "";
		  }
		});
	  
      

    </script>


</body>

</html>