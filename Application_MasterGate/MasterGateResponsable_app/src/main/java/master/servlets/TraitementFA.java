package master.servlets;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.beans.EtudiantListe;
import master.beans.MasterCard;
import master.beans.ResponsableCard;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@WebServlet(name = "TraitementFA", urlPatterns = {"/TraitementFA"})
@MultipartConfig
public class TraitementFA extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private String user = "mastergateapp@gmail.com";
     private String password = "uilkkkxqtpcavfve"; 


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MasterDao MDAO=OraFactory.getMasterDao();
		int id_master;
		String id_masterString=request.getParameter("id_master");
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
		ResponsableDao RDO = OraFactory.getResponsableDao();
		if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }
	    int id_respo = (Integer) session.getAttribute("id_respo");
	    ResponsableCard respo = RDO.getRespoCardbyId(id_respo);
	    request.setAttribute("respo", respo);
	    
		if(id_masterString!=null && !id_masterString.isEmpty()) {
			id_master=Integer.parseInt(id_masterString);
			MasterCard master=MDAO.getMasterCardById(id_master);
			String option=request.getParameter("option");
			List<EtudiantListe> ListeFinale= MDAO.getMasterEtudiantsListe(id_master,"tmp_finale");
			List<EtudiantListe> ListeAttente= MDAO.getMasterEtudiantsListe(id_master,"tmp_attente");
			List<EtudiantListe> ListeConvo= MDAO.getMasterEtudiantsListe(id_master,"tmp_convocation");
			List<EtudiantListe> ListeConvocated= MDAO.getMasterEtudiantsListe(id_master,"convocation");
			int status=MDAO.getMasterStatus(id_master);

			if(option!=null) {
				if(option.equals("deleteFinale")) {
					String id_etudiantString=request.getParameter("id_etudiant");
					if(id_etudiantString != null ) {
						int id_etudiant=Integer.parseInt(id_etudiantString);
						
						if(MDAO.deleteFromList(id_etudiant, id_master, "tmp_finale") ) {
							System.out.println("doGet : deleted succesfuly from FINALE");
							response.sendRedirect("TraitementFA?id_master="+id_master);
							return;
						}
					}else {
						System.out.println("doGet : failde to delete from list finale");
						request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
						return;
					}
					response.sendRedirect("TraitementFA?id_master="+id_master);
					return;
				}
				else if(option.equals("sendConvo")) {
					String message=request.getParameter("message");
					if(message != null) {
						for(EtudiantListe e:ListeConvo) {
							sendConvocation(MDAO.getEmailEtudiantByIdEtudiant(e.getId()),master,message);
							MDAO.convocate(id_master,message,status);
						}
						response.sendRedirect("TraitementFA?id_master="+id_master);
						return;
					}else {
						System.out.println("doGet : message is null");
						request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
					}
					response.sendRedirect("TraitementFA?id_master="+id_master);
					return;
				}else if(option.equals("ajoute")) {
					String id_etudiantString=request.getParameter("id_etudiant");
					if(id_etudiantString != null ) {
						int id_etudiant=Integer.parseInt(id_etudiantString);
						if(MDAO.ajouteAuListFinale(id_etudiant,id_master)) {
							System.out.println("doGet : ajoute au liste finale correctly");
							response.sendRedirect("TraitementFA?id_master="+id_master);
							return;
						}else {
							System.out.println("doGet : failed to add to liste finale ");
							request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
						}
					}
					response.sendRedirect("TraitementFA?id_master="+id_master);
					return;
				}else if(option.equals("deleteConvocation")) {
					String id_etudiantString=request.getParameter("id_etudiant");
					if(id_etudiantString != null ) {
						int id_etudiant=Integer.parseInt(id_etudiantString);
						if(MDAO.deleteFromList(id_etudiant, id_master, "convocation")) {
							MDAO.deleteFromList(id_etudiant, id_master, "tmp_convocation");
							System.out.println("doGet : delete from convocation correctly");
						}else {
							System.out.println("doGet : failed to delete from convocation  ");
							request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
						}
					}
					response.sendRedirect("TraitementFA?id_master="+id_master);
					return;
				}else if(option.equals("fin")) {
					if(MDAO.terminerMaster(id_master)) {
						System.out.println("doGet : End");
					    request.setAttribute("status", status);
						request.setAttribute("master", master);
						request.setAttribute("id_master", id_master);
						request.setAttribute("src","masterInfo");
						request.setAttribute("ListeType","master");
						response.sendRedirect("ResponsableMasterInformation?id_master="+id_master);						
						return;
					}else {
						System.out.println("doGet : failed to End  ");
						request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
					}
					
				}
				
				 ListeFinale= MDAO.getMasterEtudiantsListe(id_master,"tmp_finale");
				 ListeAttente= MDAO.getMasterEtudiantsListe(id_master,"tmp_attente");
				 ListeConvo= MDAO.getMasterEtudiantsListe(id_master,"tmp_convocation");
				 ListeConvocated= MDAO.getMasterEtudiantsListe(id_master,"convocation");
			}
		    request.setAttribute("status", status);
			request.setAttribute("src","TraitementFA");
			request.setAttribute("master",master);

			request.setAttribute("ListeFinale", ListeFinale);
			request.setAttribute("ListeAttente", ListeAttente);
			request.setAttribute("ListeConvo", ListeConvo);
			request.setAttribute("ListeConvocated", ListeConvocated);

			request.setAttribute("id_master", id_master);
		    this.getServletContext().getRequestDispatcher("/WEB-INF/TraitmentFA.jsp").forward(request, response);
		}else {
			System.out.println("doGet : id_master is null");
			request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
		}
	}

	
	
	void sendConvocation(String email,MasterCard master,String message) {
        String to = email;
        String from = user;
    	Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }		
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("Convocation pour le dépôt du dossier");
            String htmlContent = "<!DOCTYPE html>\r\n"
            	    + "<html lang=\"fr\">\r\n"
            	    + "<head>\r\n"
            	    + "    <meta charset=\"UTF-8\">\r\n"
            	    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
            	    + "    <style>\r\n"
            	    + "        .card {\r\n"
            	    + "            width: 100%;\r\n"
            	    + "            background-color: #f0f0f0;\r\n"
            	    + "            padding: 20px;\r\n"
            	    + "            border-radius: 10px;\r\n"
            	    + "            box-shadow: 0 4px 8px rgba(0,0,0,0.1);\r\n"
            	    + "            font-family: Arial, sans-serif;\r\n"
            	    + "        }\r\n"
            	    + "        .entete {\r\n"
            	    + "            margin-bottom: 20px;\r\n"
            	    + "        }\r\n"
            	    + "        .uni-fac {\r\n"
            	    + "            font-size: 31px;\r\n"
            	    + "            font-weight: bold;\r\n"
            	    + "            color: #333;\r\n"
            	    + "        }\r\n"
            	    + "        .date {\r\n"
            	    + "            font-size: 14px;\r\n"
            	    + "            color: #666;\r\n"
            	    + "        }\r\n"
            	    + "        #titre {\r\n"
            	    + "            font-size: 24px;\r\n"
            	    + "            color: #333;\r\n"
            	    + "            font-weight: bold;\r\n"
            	    + "        }\r\n"
            	    + "        #sousTitre {\r\n"
            	    + "            font-size: 18px;\r\n"
            	    + "            color: #666;\r\n"
            	    + "        }\r\n"
            	    + "        .main-message {\r\n"
            	    + "            margin-top: 20px;\r\n"
            	    + "            font-size: 16px;\r\n"
            	    + "            line-height: 1.6;\r\n"
            	    + "        }\r\n"
            	    + "        .footer {\r\n"
            	    + "            margin-top: 20px;\r\n"
            	    + "            font-size: 14px;\r\n"
            	    + "            color: #999;\r\n"
            	    + "        }\r\n"
            	    + "    </style>\r\n"
            	    + "</head>\r\n"
            	    + "<body>\r\n"
            	    + "    <div class=\"card\">\r\n"
            	    + "        <div class=\"header\">\r\n"
            	    + "            <div class=\"entete\">\r\n"
            	    + "                <div class=\"uni-fac\">\r\n"
            	    + "                    <span>"+master.getNom_uni()+"</span>\r\n"
            	    + "                    <span>"+master.getNom_fac()+"</span>\r\n"
            	    + "                </div>\r\n"
            	    + "                <div class=\"date\">\r\n"
            	    + "                    <span>"+LocalDate.now()+"</span>\r\n"
            	    + "                    <span>"+master.getVille()+"</span>\r\n"
            	    + "                </div>\r\n"
            	    + "            </div>\r\n"
            	    + "            <div class=\"title\">\r\n"
            	    + "                <span id=\"titre\">Convocation de la liste d'attente pour le dépôt du dossier - Master "+master.getSpecialite()+"</span>\r\n"
            	    + "            </div>\r\n"
            	    + "            <div class=\"subtitle\">\r\n"
            	    + "                <span id=\"sousTitre\">Vous êtes sélectionné de la liste d'attente pour le Master en "+master.getSpecialite()+".</span>\r\n"
            	    + "            </div>\r\n"
            	    + "            <hr>\r\n"
            	    + "            <div class=\"main-message\">\r\n"
            	    + "                <span class=\"message\">"+message+"</span>\r\n"
            	    + "            </div>\r\n"
            	    + "        </div>\r\n"
            	    + "        <div class=\"footer\">\r\n"
            	    + "            <div>\r\n"
            	    + "                <span>"+master.getNom_uni()+"</span>\r\n"
            	    + "                <span>"+master.getNom_fac()+" </span>\r\n"
            	    + "            </div>\r\n"
            	    + "            <span>"+LocalDate.now()+"</span>\r\n"
                    + "            <span>Master Gate</span>\r\n"
            	    + "        </div>\r\n"
            	    + "    </div>\r\n"
            	    + "</body>\r\n"
            	    + "</html>";
            msg.setContent(htmlContent, "text/html");
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
	}

}
