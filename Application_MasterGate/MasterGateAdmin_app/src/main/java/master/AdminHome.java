package master;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;
import bean.*;

@WebServlet(name = "AdminHome", urlPatterns = {"/AdminHome"})
@MultipartConfig
public class AdminHome extends HttpServlet {
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
	    
	    String option = request.getParameter("option");
	    
	    if (option != null) {
	        if (option.equals("deleteRespo")) {
	            String id_respoStr = request.getParameter("id_respo");
	            if (id_respoStr != null) {
	                try {
	                    int id_respo = Integer.parseInt(id_respoStr);
	                    imp.deleteResponsable(id_respo);
	                    response.sendRedirect("AdminHome");
	                    return;
	                } catch (NumberFormatException e) {
	                    // Handle invalid id_respo format
	                    e.printStackTrace(); // Log the exception or handle appropriately
	                }
	            }
	        } else if (option.equals("add")) {
	            String id_facstr = request.getParameter("id_fac");
	            if (id_facstr != null) {
	                try {
	                    int id_fac = Integer.parseInt(id_facstr);
	                    String email = request.getParameter("email");
	                    String password = request.getParameter("password");
	                    imp.insertResponsable(email, password, id_fac);
	                    response.sendRedirect("AdminHome");
	                    return;
	                } catch (NumberFormatException e) {
	                    // Handle invalid id_fac format
	                    e.printStackTrace(); // Log the exception or handle appropriately
	                }
	            }
	        }
		     else if (option.equals("activer")) {
	            String id_respostr = request.getParameter("id_respo");
	            if (id_respostr != null) {
	                try {
	                    int id_respo = Integer.parseInt(id_respostr);
	                    imp.deleteFromBlockedRespo(id_respo);
	                    response.sendRedirect("AdminHome");
	                    return;
	                } catch (NumberFormatException e) {
	                    // Handle invalid id_fac format
	                    e.printStackTrace(); // Log the exception or handle appropriately
	                }
	            }
	    	}
    	}
	    
	    // If no specific action is taken (or if parameters are invalid), fetch data and forward to AdminHome.jsp
	    List<Facultes> facultes = imp.getAllFacultes();
	    List<Responsable> responsables = imp.getAllReso();
	    List<Responsable> deletedresponsables = imp.getAllDeletedRespo();
	    request.setAttribute("deletedresponsables", deletedresponsables);

	    request.setAttribute("facultes", facultes);
	    request.setAttribute("responsables", responsables);
	    this.getServletContext().getRequestDispatcher("/WEB-INF/AdminHome.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
