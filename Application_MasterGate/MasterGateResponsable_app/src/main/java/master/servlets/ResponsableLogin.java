package master.servlets;

import jakarta.servlet.ServletException;   
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.dao.factory.OraFactory;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;
@WebServlet(name = "ResponsableLogin", urlPatterns = {"/ResponsableLogin"})
@MultipartConfig
public class ResponsableLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ResponsableLogin.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int id_respo;
		ResponsableDao r=OraFactory.getResponsableDao();
		if(r.checkLogin(email,password)){ 
			id_respo=r.getIdByEmail(email);

			request.getSession().setAttribute("id_respo", id_respo);
			response.sendRedirect("ResponsableHome");
		}else {
			request.setAttribute("failed","email/password is incorrect ");
			this.getServletContext().getRequestDispatcher("/WEB-INF/ResponsableLogin.jsp").forward(request, response);
		}
	}
}