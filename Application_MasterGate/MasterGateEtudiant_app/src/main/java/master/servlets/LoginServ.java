package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.Etudiant;
import master.beans.exception.EtudiantBeansException;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.utils.SessionUtils;

import java.io.IOException;
@WebServlet(name = "Signin", urlPatterns = {"/Signin"})

public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(! SessionUtils.isLoggedIn(request)) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("home");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Etudiant e = new Etudiant();
		try {
			e.setEmail(request.getParameter("email"));
			e.setPassword(request.getParameter("password"));

		} catch (EtudiantBeansException ede) {
			//if the email or password do not satisfy the passwordValidator crieteria then error 
			//msg will be prompted
			request.setAttribute("errorMsg", ede.getMessage());
			//send user data so he don't have to reenter it again (if an error occurs)
			sendUserData(request);
			
			doGet(request, response);

		}


		try {
			int idEtudiantFromCheck = OraFactory.getUserDao().verifyLogin(e);
			//if login success
			if(idEtudiantFromCheck > 0) {
				e = OraFactory.getUserDao().getProfileById(idEtudiantFromCheck);
		        SessionUtils.storeUserInfoInSession(request, response, idEtudiantFromCheck, e.getNom(), e.getPrenom(), e.getEmail());
		        response.sendRedirect("Masters?type=all");
			}
			else {
				request.setAttribute("errorMsg", "Email ou mot de passe incorrectes");
				//send user data so he don't have to reenter it again (if an error occurs)
				sendUserData(request);

				this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
			}
		} catch (EtudiantDaoException edd) {
			request.setAttribute("errorMsg", edd.getMessage());
			//send user data so he don't have to reenter it again (if an error occurs)
			sendUserData(request);

			this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
		}

	}
	
	protected void sendUserData(HttpServletRequest request) {
		request.setAttribute("email", request.getParameter("email"));
		request.setAttribute("password", request.getParameter("password"));

	}

}
