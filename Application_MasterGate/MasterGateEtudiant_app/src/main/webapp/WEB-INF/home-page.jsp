<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/masters-page-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header-style.css">
    
      <!-- fonts -->
      <!-- Lato -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
      <!-- Montserra -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    	<link rel="icon" href="${pageContext.request.contextPath}/img/icons/iconeMG.svg" type="image/x-icon">
    
</head>
<body>

<%@include file="/includes/header.jspf" %>


    <section class="hero" style="background: url('${pageContext.request.contextPath}/img/hero-img.jpg') no-repeat center center/cover">
        <div class="hero-content">
            <div class="hero-text">
                <h1 class="scale-up-center">Découvrez l'excellence académique réunie en un seul endroit.</h1>
                <p class="scale-up-center">Explorez et inscrivez-vous aux meilleurs masters disponibles dans nos facultés partenaires.</p>
                <a href="Masters?type=all" class="cta-button scale-up-center">Explorez les Masters</a>
            </div>

        </div>
    </section>

    <!-- features -->
    <section class="features" id="features" onscroll="myFunction()">
        <h2 class="section-title " id="features-h2"  onscroll="myFunction()">Simplifiez votre Parcours Académique</h2>
        
        <div class="feature-container">
            <div class="feature ">
                <img src="<%=request.getContextPath()%>/img/icons/school-icon.svg" alt="Icône Accès Centralisé" class="feature-icon">
                <h3>Accès Centralisé</h3>
                <p>Toutes les informations sur les programmes de master en un seul endroit.</p>
            </div>
            <div class="feature ">
                <img src="${pageContext.request.contextPath}/img/icons/easy-icon.svg" alt="Icône Facilité d'Utilisation" class="feature-icon">
                <h3>Facilité d'Utilisation</h3>
                <p>Interface intuitive et processus d'inscription simplifié.</p>
            </div>
            <div class="feature ">
                <img src="${pageContext.request.contextPath}/img/icons/community-icon.svg" alt="Icône Support Personnalisé" class="feature-icon">
                <h3>Support Personnalisé</h3>
                <p>Assistance dédiée pour répondre à toutes vos questions.</p>
            </div>
        </div>
    </section>

    <section class="masters-section" id="masters-section">
        <h2 class="section-title " id="masters-section-h2">Jeter un coupe d'oeil sur nos masters </h2>
        <section class="masters-wrap">
            <!-- add masters here -->
            
        <c:forEach var="mastercard" items="${mastercards}" begin="0" end="2" step="1">
            <section  class="master-card">
                <!-- master card head -->
                <div class="master-card-head">
                    <span  class="specialite">${ mastercard.specialite }</span>
                    <div  class="fac">
                        <a href="${ mastercard.web_site_uni }" class="fac-logo link">
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
                <div class="master-card-footer">
	                    <a href="Inscription?idMaster=${ mastercard.id_master }" class="consulter-master link">
	                       Consulter
	                    </a>
<!--                     </a> -->
                    <section class="master-card-footer-meta">
                        <div class="master-card-footer-ville-div">
                            <img src="${pageContext.request.contextPath}/img/icons/location-icon.svg" alt="ville" class="master-card-footer-ville_icon">
                            <span  class="master-card-footer-ville_txt">${ mastercard.ville}</span>
                        </div>
                        <div class="master-card-footer-date-div">
                            <img src="${pageContext.request.contextPath}/img/icons/date-icon.svg" alt="date" class="master-card-footer-date_icon">
                            <span class="master-card-footer-date_txt">Termine le ${ mastercard.d_fin_inscription }</span>
                        </div>
                    </section>
                </div>
            </section>
		</c:forEach>
    </section>
        
	</section>

    <section class="testimonial-section">
        <div class="responsive-container-block big-container">
            <div class="responsive-container-block bg">
              <p class="text-blk title section-title" id="testimonial-section-h2">
                Ils Ont Choisi Notre Plateforme
              </p>
              <div class="responsive-container-block blocks">
                <div class="responsive-cell-block wk-desk-1 wk-tab-1 wk-mobile-1 wk-ipadp-1 content">
                  <p class="text-blk info-block">
                    Grâce à cette plateforme, j'ai pu trouver le programme de master parfait pour mes ambitions professionnelles. L'inscription a été incroyablement simple et rapide.                  </p>
                  <div class="responsive-container-block person">
                    <div class="responsive-cell-block wk-desk-6 wk-ipadp-6 wk-tab-12 wk-mobile-12 icon-block">
                      <img class="profile-img" src="https://images.rawpixel.com/image_png_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIyLTA4L2pvYjEwMzQtZWxlbWVudC0wNy00MDMucG5n.png">
                    </div>
                    <div class="responsive-cell-block wk-desk-6 wk-ipadp-6 wk-tab-12 wk-mobile-12 text-block">
                      <p class="text-blk name">
                        Yassine Benjelloun
                      </p>
                      <p class="text-blk desig">
                        Rabat
                      </p>
                    </div>
                  </div>
                </div>
                <div class="responsive-cell-block wk-desk-1 wk-tab-1 wk-mobile-1 wk-ipadp-1 content">
                  <p class="text-blk info-block">
                    J'ai adoré recevoir des notifications en temps réel sur les dates limites et les événements importants. Cela m'a aidé à ne rien manquer pendant ma recherche de master.                  </p>
                  <div class="responsive-container-block person">
                    <div class="responsive-cell-block wk-desk-6 wk-ipadp-6 wk-tab-12 wk-mobile-12 icon-block">
                      <img class="profile-img" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhGJPxvhV4u_WpRUlvawm9YpDkbtL0d8D2FlZ6HgC5JcoeHfqR-FmG0eWyeLfbATOv2EU&usqp=CAU">
                    </div>
                    <div class="responsive-cell-block wk-desk-6 wk-ipadp-6 wk-tab-12 wk-mobile-12 text-block">
                      <p class="text-blk name">
                        Mohamed Boukhriss
                      </p>
                      <p class="text-blk desig">
                        Fes
                      </p>
                    </div>
                  </div>
                </div>
                <div class="responsive-cell-block wk-desk-1 wk-tab-1 wk-mobile-1 wk-ipadp-1 content bottom">
                  <p class="text-blk info-block">
                    Cette plateforme a vraiment simplifié mon parcours académique. Tout est centralisé et bien organisé, je recommande vivement.                  </p>
                  <div class="responsive-container-block person">
                    <div class="responsive-cell-block wk-desk-6 wk-ipadp-6 wk-tab-12 wk-mobile-12 icon-block">
                      <img class="profile-img" src="https://pngset.com/images/circle-profile-picture-no-background-person-face-man-student-transparent-png-2576921.png">
                    </div>
                    <div class="responsive-cell-block wk-desk-6 wk-ipadp-6 wk-tab-12 wk-mobile-12 text-block">
                      <p class="text-blk name">
                        Samir El Idrissi
                      </p>
                      <p class="text-blk desig">
                        Kenitra
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
    </section>

        <div class="footer-clean">
            <footer>
                <div class="container">
                    <div class="row justify-content-center">
                        <a class="col-sm-4 col-md-3 item" href="home">
                            <img src="${pageContext.request.contextPath}/img/icons/monmstr-logo-2.svg" alt="" class="logo-footer">
                        </a>

                        <div class="col-sm-4 col-md-3 item">
                            <h3>Liens Utiles</h3>
                            <ul>
                                <li><a href="home">Accueil</a></li>
                                <li><a href="Masters">Masters</a></li>
                                <li><a href="facultes">Facultes</a></li>
                                <li><a href="#">Responsable</a></li>
                                <li><a href="contact">Contactez-nous</a></li>

                            </ul>
                        </div>
                        <div class="col-sm-4 col-md-3 item">
                            <h3>Contactez-nous</h3>
                            <ul>
                                <li><a href="#">Email: info@example.com</a></li>
                                <li><a href="#">Téléphone: +123456789</a></li>
                            </ul>
                        </div>

                        <div class="col-lg-3 item social"><a href="#"><i class="icon ion-social-facebook"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-instagram"></i></a>
                            <p class="copyright">Company Name © 2018</p>
                        </div>
                    </div>
                </div>
            </footer>
        </div>

    
</body>

</html>
