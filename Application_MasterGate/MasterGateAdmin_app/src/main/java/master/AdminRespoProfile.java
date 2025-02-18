package master;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import bean.*;


@WebServlet(name = "AdminRespoProfile", urlPatterns = {"/AdminRespoProfile"})
@MultipartConfig
public class AdminRespoProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;  
		HttpSession session = httpRequest.getSession(false);

	        // Check if the user is logged in
	        if (session == null || session.getAttribute("user") == null) {
	            // User is not logged in, redirect to login page
	            httpResponse.sendRedirect("Login");
	            return;
	        }
		
		Imp imp = new Imp();

        String id_respoStr = request.getParameter("id_respo");
        if (id_respoStr != null) {
                int id_respo = Integer.parseInt(id_respoStr);
                Responsable respo=imp.getRespobyId(id_respo);
                request.setAttribute("respo", respo);
        }
        
	    this.getServletContext().getRequestDispatcher("/WEB-INF/ResponsableProfile.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Imp imp = new Imp();
	    
	    String id_respoStr = request.getParameter("id_respo");
	    if (id_respoStr != null) {
	        try {
	            int id_respo = Integer.parseInt(id_respoStr);
	            
	            String email = request.getParameter("email");
	            String password = request.getParameter("password");

	            
	            Responsable respo = new Responsable();
	            respo.setId_respo(id_respo);
	            respo.setEmail(email);
	            respo.setPassword(password);

	            
	            boolean success = imp.ModifierProfile(respo);
	            if (success) {

	            } else {
	                request.setAttribute("error", "Update failed");
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Invalid input");
	        }
	    }
	    
	    // In case of error or invalid data, forward back to the profile page
	    doGet(request, response);
	}

}
