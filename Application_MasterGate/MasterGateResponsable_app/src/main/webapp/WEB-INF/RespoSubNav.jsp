<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/RespoHeader.css">
</head>
<body>
<c:if test="${status != 99}">
<nav class="sub-nav">
        <div class="sub-links">
                <form action="ResponsableMasterInformation" method="post" style="display:inline;">
                    <input type="hidden" name="id_master" value="${id_master}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="link" style="color:#fff; <c:if test='${src eq "masterInfo"}'>background-color:#666461;</c:if>">
                        Master
                    </button>
                </form>

          		 <c:choose>
		                <c:when test="${status == 0}">
		                        <form action="EtudeDossier" method="post" style="display:inline;">
		                            <input type="hidden" name="id_master" value="${id_master}">
		                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		                            <button type="submit" class="link" style="color:#fff; <c:if test='${src eq "etudeDossier"}'>background-color:#666461;</c:if>">
		                                Étude de dossier
		                            </button>
		                        </form>

		                        <form action="PreselectionTmp" method="post" style="display:inline;">
		                            <input type="hidden" name="option" value="voirPreselectionTmp">
		                            <input type="hidden" name="id_master" value="${id_master}">
		                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		                            <button type="submit" class="link" style="color:#fff; <c:if test='${src eq "preselectionTmp"}'>background-color:#666461;</c:if>">
		                                Étudiants présélectionnés
		                            </button>
		                        </form>
		                </c:when>
		                <c:when test="${status == 1}">
		                        <form action="RespoConcours" method="post" style="display:inline;">
		                            <input type="hidden" name="id_master" value="${id_master}">
		                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		                            <button type="submit" class="link" style="color:#fff; <c:if test='${src eq "concours"}'>background-color:#666461;</c:if>">
		                                Traitement du concours
		                            </button>
		                        </form>
		              
		                   	  	<form action="RespoGestionListeFA" method="post" style="display:inline;">
							        <input type="hidden" name="id_master" value="${id_master}">
							        <input type="hidden" name="option" value="gestionListe">
								    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
								    <button type="submit" class="link" style="color:#fff; <c:if test='${src eq "GestionFA"}'>background-color:#666461;</c:if>">
							     		listes finale/attente
							     	</button>
								</form>
		                </c:when>
		                <c:when test="${status >= 2 and status<33}">
		                        <form action="TraitementFA" method="post" style="display:inline;">
		                            <input type="hidden" name="id_master" value="${id_master}">
		                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		                            <button type="submit" class="link" style="color:#fff; <c:if test='${src eq "TraitementFA"}'>background-color:#666461;</c:if>">
		                                Gestion des listes Finale & attente
		                            </button>
		                        </form>
		                </c:when> 
            	</c:choose>
        </div>
</nav>
</c:if>

</body>
</html>