package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.beans.ResponsableCard;
import master.dao.factory.OraFactory;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;

@WebServlet(name = "ResponsableProfile", urlPatterns = {"/ResponsableProfile"})
@MultipartConfig
public class ResponsableProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponsableDao RDO = OraFactory.getResponsableDao();
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
	    if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }else {	
	    	int id_respo = (int) session.getAttribute("id_respo") ;
	    	ResponsableCard respo=RDO.getRespoCardbyId(id_respo);
	    	request.setAttribute("respo", respo);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ResponsableProfile.jsp").forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
