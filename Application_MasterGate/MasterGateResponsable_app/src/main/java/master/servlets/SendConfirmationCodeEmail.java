package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.dao.factory.OraFactory;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(name = "ResponsableAddMaster", urlPatterns = {"/ResponsableAddMaster"})
@MultipartConfig
public class SendConfirmationCodeEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private String user = "mastergateapp@gmail.com";
     private String password = "uilkkkxqtpcavfve"; // Use the correct app password here

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/RecuperPassWord/ForgotPassWord.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Codeconfirmation = String.valueOf((int) ((Math.random() * (999999 - 100000)) + 100000));
        String to = request.getParameter("email");
        ResponsableDao RDO=OraFactory.getResponsableDao();
        String from = user;
        int user_id=RDO.getIdByEmail(to);
        if( user_id > 0) {
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
                msg.setSubject("Code de confirmation via Master Gate ");
                String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;'>"
                        + "<h2 style='color: #4CAF50;'>Votre code de confirmation</h2>"
                        + "<p>Bonjour,</p>"
                        + "<p>Merci d'utiliser nos services. Votre code de confirmation est :</p>"
                        + "<div style='font-size: 24px; font-weight: bold; color: #333; text-align: center; margin: 20px 0;'>"
                        + Codeconfirmation
                        + "</div>"
                        + "<p style='color: #555;'>Veuillez entrer ce code dans la page de confirmation pour vérifier votre adresse e-mail.</p>"
                        + "<p>Cordialement,<br>L'équipe de MyMaster</p>"
                        + "<hr style='border: 0; border-top: 1px solid #eee;'>"
                        + "<p style='font-size: 12px; color: #888;'>Si vous n'avez pas demandé ce code, veuillez ignorer cet e-mail.</p>"
                        + "</div>";
                msg.setContent(htmlContent, "text/html");
                Transport.send(msg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            
            HttpSession s = request.getSession();
            s.setAttribute("Codeconfirmation",""+Codeconfirmation);
            s.setAttribute("user_id", user_id);
            response.sendRedirect("ConfirmationCode");
        }else {
        	request.setAttribute("erreur","* Adresse e-mail non trouvée dans l'application .");
    		this.getServletContext().getRequestDispatcher("/WEB-INF/RecuperPassWord/ForgotPassWord.jsp").forward(request, response);
        }
        
	}

}
