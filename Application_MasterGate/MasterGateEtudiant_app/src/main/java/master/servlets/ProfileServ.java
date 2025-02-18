package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.Etudiant;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.utils.SessionUtils;

import java.io.IOException;
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})

public class ProfileServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(SessionUtils.isLoggedIn(request)) {
			Etudiant e = new Etudiant();
			try {
				String token = SessionUtils.getUserIdFromSession(request);
				int idEtudiant = OraFactory.getUserDao().getEtudiantIdFromToken(token);
				
				e = OraFactory.getUserDao().getProfileById(idEtudiant);
			} catch (EtudiantDaoException ede) {
				ede.printStackTrace();
				//send an error msg
			}
			
			request.setAttribute("e", e);
			this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
	
		}
		else {
			response.sendRedirect("Signin");
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
