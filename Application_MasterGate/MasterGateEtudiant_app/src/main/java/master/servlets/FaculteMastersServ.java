package master.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.Facultes;
import master.beans.MasterCard;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;

@WebServlet(name="faculte-masters", urlPatterns= {"/faculte-masters"})
public class FaculteMastersServ extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
				//try and catch to check if the param is a valid number
					int facId = Integer.parseInt(request.getParameter("fac"));
					//check if the faculty exists
						Facultes fac = OraFactory.getFacultesDao().getFaculteById(facId);
						List<MasterCard> mds= OraFactory.getMasterDao().getMasterCardsByFacId(facId);
						request.setAttribute("mastercards", mds);
						request.setAttribute("fac", fac);
						
				        this.getServletContext().getRequestDispatcher("/WEB-INF/faculty-masters.jsp").forward(request, response);
		}
		//if the param is not a number
		catch(NumberFormatException | EtudiantDaoException n) {
			response.sendRedirect("facultes");
		}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
