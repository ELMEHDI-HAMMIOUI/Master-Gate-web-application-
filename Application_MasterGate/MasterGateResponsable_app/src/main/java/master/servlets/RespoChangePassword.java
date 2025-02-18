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


@WebServlet(name = "ResponsableMasterInformation", urlPatterns = {"/ResponsableMasterInformation"})
@MultipartConfig
public class RespoChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
	   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
		    ResponsableDao RDO=OraFactory.getResponsableDao();
		    if (session == null || session.getAttribute("id_respo") == null) {
		        response.sendRedirect("ResponsableLogin");
		        return;
		    }else {
			    int id_respo = (Integer) session.getAttribute("id_respo");
		    	String email=request.getParameter("email");
		    	String current_password=request.getParameter("current_password");
		    	String new_password=request.getParameter("new_password");
		    	if(RDO.checkLogin(email, current_password)) {
		    		if(RDO.changePassWord(id_respo,new_password)) {
		    			response.sendRedirect("ResponsableLogout");
		    		}else {
		    			response.sendRedirect("ResponsableProfile? * erreur=une erreur s'est produite veuillez réessayer !! ");
		    		}
		    	}else {
		    		response.sendRedirect("ResponsableProfile?erreur= * le mot de passe courant  que vous avez entrer est incorrecte !! ");
		    	}
		    	
		    }
	}

}
