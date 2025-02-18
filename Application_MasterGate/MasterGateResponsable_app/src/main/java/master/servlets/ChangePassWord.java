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

/**
 * Servlet implementation class ChangePassWord
 */

@WebServlet(name = "ChangePassWord", urlPatterns = {"/ChangePassWord"})
@MultipartConfig
public class ChangePassWord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/RecuperPassWord/ChangePassWord.jsp").forward(request, response);
	}


	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        ResponsableDao RDO = OraFactory.getResponsableDao();
	        String newPassword = request.getParameter("new_password");
	        HttpSession session = request.getSession(false); // Get the session if it exists, do not create a new one

	        if (session == null || session.getAttribute("user_id") == null ) {
	            response.sendRedirect("ResponsableLogin");
	            return;
	        }

	        Integer userId = (Integer) session.getAttribute("user_id");
	        if (userId != null && userId > 0 && newPassword != null && !newPassword.trim().isEmpty()) {
	            if(RDO.changePassWord(userId, newPassword)) {
		    		this.getServletContext().getRequestDispatcher("/WEB-INF/RecuperPassWord/Succées.jsp").forward(request, response);
	            }
	        } else {
	        	request.setAttribute("erreur"," * une erreur s'est produite veuillez réessayer");
	    		this.getServletContext().getRequestDispatcher("/WEB-INF/RecuperPassWord/ChangePassWord.jsp").forward(request, response);
	        }
	    }

}
