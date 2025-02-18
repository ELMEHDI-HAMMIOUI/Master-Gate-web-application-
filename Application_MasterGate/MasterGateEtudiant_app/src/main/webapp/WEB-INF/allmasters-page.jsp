<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ page import="master.beans.*" %>
 <%@page import= "java.time.LocalDate"%>
 <%@page import="java.time.Period" %>
 <%@page import= "java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Masters</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
    <!-- Montserra -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    
    <!-- css -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/masters-page-style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/generic-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagination-style.css">

	<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
<%
// 	//tags available are: ALL, Not expired, expired, new
// 	if( request.getSession().getAttribute("tag") == null ){
//     	//storing the tag 
// 		  request.getSession().setAttribute("tag", "All");
// 	}

// 	//filters available are: default, noninscrit
// 	if( request.getSession().getAttribute("filter") == null ){
// 		//storing the filter
// 		  request.getSession().setAttribute("filter", "default");
// 	}

	

%>

    
</head>
<body>

	
	<style>
	        .filter-sort {
            display: flex;
            justify-content: space-between;
            gap: 10px;
            align-items: center;
        }
        .filter-div, .sort-div{
            position: relative;
            display: flex;
        }
        .filter-div select , .sort-div select{
            padding: .5em;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            z-index: 10;

            background-color: white;
        }
        .filter-div select{
            left: 0;
        }
        .filter-sort-icon{
            display: block;
            padding: 8px 16px;
            border-radius: 35px;
        }
        .filter-sort-icon:hover{
            background-color: #f4f4f4;
            cursor: pointer;
        }
        
        .masters-subheader{
        	margin-top: 1em;
        	margin-bottom: 1em;
        	display: flex;
        	align-items: center;
        	gap: 3em;
        	width: 50%;
  			margin: 1em	 auto;
        }
        .nav-btn{
		padding: 0 16px;
		border-radius: 9999999px;
  		color: #0d0c22;
	  	font-size: 14px;
  		border: none;
  		padding: .5em 1em;
  		font-weight: 600;
  		background: none;
  		font-family: Lato;
        }
        .nav-btn:hover{
        	cursor: pointer;
        }
 		.selected{
        	background: #f8f7f4;
 		}
 		.masters-nav{
 			display: flex;
 			align-items: center;
 			justify-content: space-between;
 			gap: 1em;
 			padding: 1.5em;
 			width: 100%;
    		overflow-x: auto;
 		}
 		main{
 		display: flex;
 		}
 		.sidebar{
 		width: 18% !important;
 		}
 		.right{
 		width: 80% !important;
 		}
	</style>
	
	
	<%@ include file="/includes/header.jspf" %>
	

<main>
	
	
	
	
		<%@ include file="/includes/filter.jspf" %>
		
		
		<section class="right">
<!-- 	subheader -->
 
<!-- 		<form method="GET" action="filterMasterRedirect"  class="masters-subheader"  id="filter-form"> -->
<!-- 			<!--filter options --> 
<!-- 			 <div class="filter-div"> -->
<!--                     <select name="filter" id="filter" class="" data-tg-tour="Vous pouvez filtrer uniquement les masters dans lesquels vous n'etes pas encore inscrit, mais vous devez être connecte avec votre compte pour cela." data-tg-order="4"> -->

<!--                         <option value="default"  -->
<%--                         if the searched filter is masters, then it should be selected by default    --%>
<%-- 		                   <c:if test="${sessionScope.filter == 'default' }"> --%>
<%-- 		                     <c:out value="selected"></c:out> --%>
<%-- 		                   </c:if>                  --%>
<%--                        >Filter</option> --%>
<%--                         if the user is logged in --%>
<%-- 						<c:if test="${!empty sessionScope.userId }"> --%>
<%--	                        <option value="noninscrit" --%>
<%-- 	                        if the searched filter is facs, then it should be selected by default --%>
<%-- 			                   <c:if test="${sessionScope.filter == 'noninscrit' }"> --%>
<%--  			                     <c:out value="selected"></c:out> --%>
<%--  			                   </c:if>   --%>
			                  
<!-- 	                        >Non Inscrit</option> -->
<%-- 						</c:if> --%>

<!--                     </select> -->
<!--              </div> -->
              
<!--                 navigations -->

			<section class="masters-nav">
<%--                 <input type="submit" name="type" value="all" class="nav-btn  --%>
<%--                 	<c:if test="${param.type == 'all' }"> --%>
<%--                		<c:out value="selected"></c:out> -->
<!--                 	</c:if> -->
<%--                 " data-tg-tour='Explorer touts les masters publiees.' data-tg-order="5"> --%>
<%--                 <input type="submit" name="type" value="insc" class="nav-btn --%>
<%--                 	<c:if test="${param.type == 'insc' }"> --%><%--
                 		<c:out value="selected"></c:out> -->
<!--                 	</c:if> -->
<%--                 " data-tg-tour='Voir juste les masters qui sont valides et ne sont pas encore terminees .' data-tg-order="6"> --%>
                
<%--                 <input type="submit" name="type" value="pres" class="nav-btn --%>
<%--                 	<c:if test="${param.type == 'pres' }"> --%>
<%--                 		<c:out value="selected"></c:out> -->
<!--                 	</c:if> -->
<%--                 " data-tg-tour='Explorer les masters publiees recement .' data-tg-order="7"> --%>
                
<%--                 <input type="submit" name="type" value="lf" class="nav-btn --%>
<%--                 	<c:if test="${param.type == 'lf' }"> --%>
<%--                		<c:out value="selected"></c:out> -->
<!--                 	</c:if> -->
<%--                 " > --%>
                
<%--                 <input type="submit" name="type" value="la" class="nav-btn --%>
<%--                 	<c:if test="${param.type == 'la' }"> --%>
<%--                		<c:out value="selected"></c:out> -->
<!--                 	</c:if> -->
<%--                 " > --%>
                
                 <a href="Masters?type=all" class="nav-btn  
                 	<c:if test="${param.type == 'all' }">
                 		<c:out value="selected"></c:out> 
                 	</c:if> 
                 ">All</a> 
                
                 <a href="Masters?type=popular" class="nav-btn  
                 	<c:if test="${param.type == 'popular' }">
                 		<c:out value="selected"></c:out> 
                 	</c:if> 
                 ">Popular</a> 
                
            <%--if logged in --%>
            <c:if test="${sessionScope.userId != null }">

                <a href="Masters?type=noninsc" class="nav-btn 
                	<c:if test="${param.type == 'noninsc' }">
                		<c:out value="selected"></c:out>
                	</c:if>
                ">Non Inscrit</a>
                
                
                <a href="Masters?type=insc" class="nav-btn 
                	<c:if test="${param.type == 'insc' }">
                		<c:out value="selected"></c:out>
                	</c:if>
                ">Inscription</a>
                                
                <a href="Masters?type=pres" class="nav-btn 
                	<c:if test="${param.type == 'pres' }">
                		<c:out value="selected"></c:out>
                	</c:if>
                ">Preselection</a>
                
                                
                <a href="Masters?type=lf" class="nav-btn 
                	<c:if test="${param.type == 'lf' }">
                		<c:out value="selected"></c:out>
                	</c:if>
                ">Liste Finale</a>
                
                                
                <a href="Masters?type=la" class="nav-btn 
                	<c:if test="${param.type == 'la' }">
                		<c:out value="selected"></c:out>
                	</c:if>
                ">Liste Attente</a>
                

			</c:if>
                
<!--                 <input type="submit" value="new" class="nav-btn" >New</button> -->
<!--                 <input type="submit" value="expired" class="nav-btn" >Expired</button> -->
			</section>
            
            
  </form>    
<!-- 		</form> -->

<!-- content -->
	<section class="t-container">
        <section class="t-main">
        <%--  
		<c:forEach var="mastercard" items="${mastercards}">
            <section  class="master-card">
                <!-- master card head -->
                <div class="master-card-head">
                    <span  class="specialite" data-tg-tour='La specialite de ce master .' data-tg-order="10" > ${ mastercard.specialite }</span>
                    <div  class="fac">
                        <a href="${ mastercard.web_site_uni }" class="fac-logo link" data-tg-tour='La faculte qui a publie ce master .' data-tg-order="9">
                            <img src="${mastercard.logo_uni}" alt="logo" >

                        </a>
                        <span  class="fac-nom"></span>
                    </div>
                </div>
                <!-- master card body -->
                <div class="master-card-body">
                    <h5 class="master-card-body-title">Master
                        <span class="master-card-body-title_facSurnom">${mastercard.surnom_fac }</span>
                        <span class="master-card-body-title_ville">${mastercard.ville }</span>
                        <span class="master-card-body-title_date"></span>
                    </h5>
                </div>
                <!-- master card footer -->
                <div class="master-card-footer" data-tg-tour="Vous trouvez ici toutes informations concernant ce master, ainsi que la possibilite de s'inscrire." data-tg-order="13">

	                    <a href="Inscription?idMaster=${ mastercard.id_master }" class="consulter-master link" data-tg-tour="Un master est expire s'il acheve sa date limite d'inscription." data-tg-order="12">
	                       Consulter
	                    </a>
	                    
                    <section class="master-card-footer-meta">
                        <div class="master-card-footer-ville-div">
                            <img src="img/icons/location-icon.svg" alt="ville" class="master-card-footer-ville_icon">
                            <span  class="master-card-footer-ville_txt">${ mastercard.ville}</span>
                        </div>
                        <div class="master-card-footer-date-div" data-tg-tour="La date de fin d'inscription ." data-tg-order="10">
                            <img src="img/icons/date-icon.svg" alt="date" class="master-card-footer-date_icon">
                            <span class="master-card-footer-date_txt">Termine le ${ mastercard.d_fin_inscription }</span>
                        </div>
                    </section>
                </div>
                
				<!-- expiration badge -->
                <c:if test="${mastercard.expired}" >
                	<span class="expire-badge badge" data-tg-tour="Un master est expire s'il acheve sa date limite d'inscription." data-tg-order="11" >Expired</span>
                </c:if>
                
				<!-- new badge -->
                <c:if test="${mastercard.newMaster}" >
                	<span class="new-badge badge" data-tg-tour="Un master est considere nouveau s'il n'a pas depasser 3jours depuis sa publication." data-tg-order="11">New</span>
                </c:if>
                
                
            </section>
                
             
			<!--end of mastercard    -->
		</c:forEach>
    		
    	--%>	
    	
    	
    	<c:set var="index" value="0" />
		<c:forEach var="mastercard" items="${mastercards}">
		    <c:if test="${index == 0}">
		    
		        <!-- This handles the first item -->
		        <!-- this item will be toured, the others will not -->
		        <section  class="master-card">
                <!-- master card head -->
                <div class="master-card-head">
                    <span  class="specialite" data-tg-tour='La specialite de ce master .' data-tg-order="10" > ${ mastercard.specialite }</span>
                    <div  class="fac">
                        <a href="${ mastercard.web_site_uni }" class="fac-logo link" data-tg-tour='La faculte qui a publie ce master .' data-tg-order="9">
                            <img src="${mastercard.logo_uni}" alt="logo" >

                        </a>
                        <span  class="fac-nom"></span>
                    </div>
                </div>
                <!-- master card body -->
                <div class="master-card-body">
                    <h5 class="master-card-body-title">Master
                        <span class="master-card-body-title_facSurnom">${mastercard.surnom_fac }</span>
                        <span class="master-card-body-title_ville">${mastercard.ville }</span>
                        <span class="master-card-body-title_date"></span>
                    </h5>
                </div>
                <!-- master card footer -->
                <div class="master-card-footer" >

	                    <a href="Inscription?idMaster=${ mastercard.id_master }" class="consulter-master link" data-tg-tour="Vous trouvez ici toutes informations concernant ce master, ainsi que la possibilite de s'inscrire ❤️." data-tg-order="12">
	                       Consulter
	                    </a>
	                    
                    <section class="master-card-footer-meta">
                        <div class="master-card-footer-ville-div">
                            <img src="img/icons/location-icon.svg" alt="ville" class="master-card-footer-ville_icon">
                            <span  class="master-card-footer-ville_txt">${ mastercard.ville}</span>
                        </div>
                        <div class="master-card-footer-date-div" data-tg-tour="La date de fin d'inscription 🔥." data-tg-order="10">
                            <img src="img/icons/date-icon.svg" alt="date" class="master-card-footer-date_icon">
                            <span class="master-card-footer-date_txt">Termine le ${ mastercard.d_fin_inscription }</span>
                        </div>
                    </section>
                </div>
                
				<!-- expiration badge -->
				
                <c:if test="${mastercard.expired}" >
                	<span class="expire-badge badge" data-tg-tour="Un master est expire s'il acheve sa date limite d'inscription ✨." data-tg-order="11" >Expired</span>
                </c:if>
                
				<!-- new badge -->
                <c:if test="${mastercard.newMaster}" >
                	<span class="new-badge badge" data-tg-tour="Un master est considere nouveau s'il n'a pas depasser 3jours depuis sa publication ✨." data-tg-order="11">New</span>
                </c:if>
                
                
            </section>
                
             
			<!--end of mastercard    -->
		        

		    </c:if>
		    
		    <c:if test="${index > 0}">
		    
		        <!-- This handles the remaining items -->
		        <!-- Process each mastercard item here -->
            <section  class="master-card">
                <!-- master card head -->
                <div class="master-card-head">
                    <span  class="specialite"  > ${ mastercard.specialite }</span>
                    <div  class="fac">
                        <a href="${ mastercard.web_site_uni }" class="fac-logo link" >
                            <img src="${mastercard.logo_uni}" alt="logo" >

                        </a>
                        <span  class="fac-nom"></span>
                    </div>
                </div>
                <!-- master card body -->
                <div class="master-card-body">
                    <h5 class="master-card-body-title">Master
                        <span class="master-card-body-title_facSurnom">${mastercard.surnom_fac }</span>
                        <span class="master-card-body-title_ville">${mastercard.ville }</span>
                        <span class="master-card-body-title_date"></span>
                    </h5>
                </div>
                <!-- master card footer -->
                <div class="master-card-footer" >

	                    <a href="Inscription?idMaster=${ mastercard.id_master }" class="consulter-master link" >
	                       Consulter
	                    </a>
	                    
                    <section class="master-card-footer-meta">
                        <div class="master-card-footer-ville-div">
                            <img src="img/icons/location-icon.svg" alt="ville" class="master-card-footer-ville_icon">
                            <span  class="master-card-footer-ville_txt">${ mastercard.ville}</span>
                        </div>
                        <div class="master-card-footer-date-div" >
                            <img src="img/icons/date-icon.svg" alt="date" class="master-card-footer-date_icon">
                            <span class="master-card-footer-date_txt">Termine le ${ mastercard.d_fin_inscription }</span>
                        </div>
                    </section>
                </div>
                
				<!-- expiration badge -->
                <c:if test="${mastercard.expired}" >
                	<span class="expire-badge badge"  >Expired</span>
                </c:if>
                
				<!-- new badge -->
                <c:if test="${mastercard.newMaster}" >
                	<span class="new-badge badge" >New</span>
                </c:if>
                
                
            </section>
                
             
			<!--end of mastercard    -->

		    </c:if>
		    <c:set var="index" value="${index + 1}" />
		</c:forEach>














    	
	  <!--end of t-main	 -->
      </section>
      <%--
    <form method="post" action="Masters" class="page-container">
    
        <section class="pagination">
		
			<!-- previous button -->
	        <c:if test="${( currentPage - 1 ) > 0}">	    
			    <a href="Masters?page=${currentPage - 1}" class="page-link prev-page-link link" title="Next page" type="submit" name="page" ${currentPage <= 1 ? 'disabled' : ''}>
				    <svg xmlns="http://www.w3.org/2000/svg" width="8.233" height="13.861" viewBox="0 -4.861 8.233 13.861" class="page-svg prev-svg">
					    <path d="M8.233 2.018L1.251 9 0 7.645l5.627-5.627L0-3.61l1.251-1.25 6.982 6.878z">
					    </path>
					</svg>
				    Previous
				</a>
			</c:if>
			
			<input type="text" maxlength="${totalPages}" class="input-pagenum" autocomplete="off" name="page"  value="${currentPage}" > of 
		    <span class="total-pages-span">${totalPages}</span>
		
			<!-- next button -->
	        <c:if test="${( currentPage + 1 ) <= totalPages}">	    
	    		<a href="Masters?page=${currentPage + 1}" class="next-page-link page-link link" title="Previous page" type="submit" name="page" ${currentPage >= totalPages ? 'disabled' : ''}>
				    NEXT
				    <svg xmlns="http://www.w3.org/2000/svg" width="8.233" height="13.861" viewBox="0 -4.861 8.233 13.861" class="page-svg next-svg">
					    <path d="M8.233 2.018L1.251 9 0 7.645l5.627-5.627L0-3.61l1.251-1.25 6.982 6.878z">
					    </path>
					</svg>
				</a>
			</c:if>

		
		</section>

     --%>
   
   </section>
   
   
   </section>
   
   
 </main>
   
   
      <script>
        //whenever a filter is changed(from default to noninscrit) then submit and get masters relative to that filter
   		document.getElementById("filter").addEventListener("change",()=>{
   			document.getElementById("filter-form").submit();
   		})
   </script>
    
 
 <%@include file="/includes/tour.jspf" %>
 
    
</body>
</html> 