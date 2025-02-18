package master;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class Login
 */

@WebServlet(name = "Login", urlPatterns = {"/Login"})
@MultipartConfig
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/AdminLogin.jsp").forward(request, response);			

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (Imp.checkLogin(username, password)) {
            // Create a session and set the user attribute
            HttpSession session = request.getSession();
            session.setAttribute("user", username);  // Store username or user object

      
            response.sendRedirect("AdminHome");
            
        } else {
            request.setAttribute("loginError", "Invalid username or password. Please try again.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/AdminLogin.jsp").forward(request, response);
        }
	}

}
