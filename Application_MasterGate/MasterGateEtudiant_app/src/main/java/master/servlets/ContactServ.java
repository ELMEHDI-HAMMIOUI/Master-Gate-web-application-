package master.servlets;
import java.io.IOException;
import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Contact", urlPatterns = { "/contact" })
public class ContactServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/contact.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("Last Name");
        String email = request.getParameter("Email");
        String phoneNumber = request.getParameter("PhoneNumber");
        String message = request.getParameter("Message");

        // Send email
        String recipient = System.getenv("GMAIL_USERNAME"); // Your email address
        String subject = "Contact Form Submission from " + firstName + " " + lastName;
        String content = "Name: " + firstName + " " + lastName + "\n"
                + "Email: " + email + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "Message: " + message;

        sendEmail(recipient, subject, content);

        // Set attributes to forward to the JSP
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("message", message);

        // Forward to a success page
        this.getServletContext().getRequestDispatcher("/WEB-INF/success.jsp").forward(request, response);
    }

    private void sendEmail(String recipient, String subject, String content) {
        // Get the environment variables
        final String username = System.getenv("GMAIL_USERNAME");
        final String appPassword = System.getenv("GMAIL_APP_PASSWORD"); // Use the app password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, appPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
