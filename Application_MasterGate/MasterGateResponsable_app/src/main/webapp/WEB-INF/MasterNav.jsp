<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.nav-liste{
	    height: 100%;
	    width: 241px;
	    background-color: #666461;
	    position: fixed;
	    margin: 0;
	    border-color: #666461;
	    padding: 25px 13px;
	    display: flex;
	    gap: 1em;
	    flex-wrap: nowrap;
	    flex-direction: column;
	    justify-content: space-between;
	}
	.nav-liste hr{
		margin: 6px 0;
	}
	.nav-liste button{
	    border: 0;
	    background-color: #444444;
	    cursor: pointer;
	    border-radius: 8px;
	    padding: 12px;
	    text-align: left;
	}
	.sideBare-down{
	    margin-bottom: 124px;
	    border: 1px dashed #eaeaea;
	    border-radius: 9px;
	    position: relative;
	    padding: 6px;
	}
	
	.sideBare-down span{
		position: absolute;
	    top: -10px;
	    left: 10px;
	    background: #666461;
	    padding: 0 5px;
	    color: aliceblue;
	}    
	*,*::after,*::before{
	    margin:0;
	    padding: 0;
	    box-sizing: border-box;
	}
</style>
</head>
<body>
	<div class="nav-liste">
	<div class="sideBare-top">
        <form action="ResponsableMasterInformation" method="post" style="display:inline;">
            <input type="hidden" name="id_master" value="${id_master}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="submit" class="link" style="color:#ffff;background-color: #666461;<c:if test="${ListeType eq 'master'}">background-color:#848483;</c:if>">
                À propos du Master
            </button>
        </form>
        <hr>
	    <form  action="Listes" method="post" style="display:inline;">
		    <input type="hidden" name="id_master" value="${id_master}">
		   	<input type="hidden" name="ListeType" value="inscription">
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		    <button type="submit" class="link"  style="color:#ffff;background-color: #666461;<c:if test="${ListeType eq 'inscription'}">background-color:#848483;</c:if>">
				Étudiants Inscrits		    
			</button>
		</form>
	    <form  action="Listes" method="post" style="display:inline;">
		    <input type="hidden" name="id_master" value="${id_master}">
		   	<input type="hidden" name="ListeType" value="preselection">
		    <button type="submit" class="link"  style="color:#ffff;background-color: #666461;<c:if test="${ListeType eq 'preselection'}">background-color:#848483;</c:if>">
				Étudiants Présélectionnés	
			</button>
		</form>
		<form  action="Listes" method="post" style="display:inline;">
		    <input type="hidden" name="id_master" value="${id_master}">
		   	<input type="hidden" name="ListeType" value="rejected">
		    <button type="submit" class="link"  style="color:#ffff;background-color: #666461;<c:if test="${ListeType eq 'rejected'}">background-color:#848483;</c:if>">
				Étudiants rejetés
			</button>
		</form>
		<c:if test="${status >= 2 }">
		
		<form  action="Listes" method="post" style="display:inline;">
		    <input type="hidden" name="id_master" value="${id_master}">
		   	<input type="hidden" name="ListeType" value="finale">
		    <button type="submit" class="link"  style="color:#ffff;background-color: #666461;<c:if test="${ListeType eq 'finale'}">background-color:#848483;</c:if>">
				Liste Finale et attente 
			</button>
		</form>
		</c:if>
		       
		
		<c:if test="${status >= 2 }">
		 <hr>
		<form  action="Listes" method="post" style="display:inline;">
		    <input type="hidden" name="id_master" value="${id_master}">
		   	<input type="hidden" name="ListeType" value="tmp_preselection">
		    <button type="submit" class="link"  style="color:#ffff;background-color: #666461;<c:if test="${ListeType eq 'tmp_preselection'}">background-color:#848483;</c:if>">
				Résultats du Concours	
			</button>
		</form>
		</c:if>
		<c:if test="${status >= 3 }">
		 <hr>
		<form  action="Listes" method="post" style="display:inline;">
		    <input type="hidden" name="id_master" value="${id_master}">
		   	<input type="hidden" name="ListeType" value=convocationR>
		    <button type="submit" class="link"  style="color:#ffff;background-color: #666461;<c:if test="${ListeType eq 'convocationR'}">background-color:#848483;</c:if>">
				Étudiants convoqués de la liste d'attente
			</button>
		</form>
		</c:if>
		<c:if test="${status >= 3 }">
		 
		<form  action="Listes" method="post" style="display:inline;">
		    <input type="hidden" name="id_master" value="${id_master}">
		   	<input type="hidden" name="ListeType" value="tmp_finale">
		    <button type="submit" class="link"  style="color:#ffff;background-color: #666461;<c:if test="${ListeType eq 'tmp_finale'}">background-color:#848483;</c:if>">
				Liste Finale 
			</button>
		</form>
		</c:if>
<%-- 			<c:if test="${status >= 3 }"> --%>
<!-- 		<form  action="Listes" method="post" style="display:inline;"> -->
<%-- 		    <input type="hidden" name="id_master" value="${id_master}"> --%>
<!-- 		   	<input type="hidden" name="ListeType" value=convocationF> -->
<%-- 		    <button type="submit" class="link"  style="color:#ffff;background-color: #666461;<c:if test="${ListeType eq 'convocationF'}">background-color:#848483;</c:if>"> --%>
<!-- 				Étudiants convoqués <br>(non présentés) -->
<!-- 			</button> -->
<!-- 		</form> -->
<!-- 		<hr> 
-->
	

		</div>
		
		<c:if test="${status >= 1 }">
			<div class="sideBare-down">
			<c:if test="${status >= 1 or status>33}">
			
		    <span>
		        Publication
		    </span>
			<form  action="PublicationResultats" method="post" style="display:inline;">
			    <input type="hidden" name="id_master" value="${id_master}">
			    <input type="hidden" name="type_pub" value="PRE">
			    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			    <button type="submit" class="link"  style="color:#ffff;background-color: #666461;<c:if test="${type_pub eq 'PRE'}">background-color:#848483;</c:if>">
			    	Préselection
			    </button>
			</form>
			</c:if>
			<c:if test="${status >= 2 or status>33}">
			<form  action="PublicationResultats" method="post" style="display:inline;">
			    <input type="hidden" name="id_master" value="${id_master}">
			    <input type="hidden" name="type_pub" value="LFA">
			    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			    <button type="submit" class="link"  style="color:#ffff;background-color: #666461;<c:if test="${ type_pub eq 'LFA'}">background-color:#848483;</c:if>">
			    	Finale/Attente	
			    </button>
			</form>
			</c:if>
			</div>
		</c:if>
	</div>
</body>
</html>