package master.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.FacultesDao;
import master.dao.interfaces.MasterDao;


@WebServlet(name="Search", urlPatterns= {"/search"})
public class SearchServ extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchQuery = request.getParameter("search");
		String filterChoice = request.getParameter("filter");
		try {
			MasterDao md = OraFactory.getMasterDao();
			FacultesDao fd = OraFactory.getFacultesDao();
			switch(filterChoice) {
			  case "masters":
			    // code block
				  request.setAttribute("mastercards", md.searchMasters(searchQuery));
					this.getServletContext().getRequestDispatcher("/WEB-INF/search-masters-result.jsp").forward(request, response);

			    break;
			  case "facs":
			    // code block
				  request.setAttribute("facs", fd.searchFacs(searchQuery));
				  this.getServletContext().getRequestDispatcher("/WEB-INF/search-facs-result.jsp").forward(request, response);
			    break;
			  case "uni":
				  //
				break;
				
			  default:
			    // code block
			}
		}catch(EtudiantDaoException ede) {
			request.setAttribute("errorMsg", ede.getMessage());
		}

		

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	




}

