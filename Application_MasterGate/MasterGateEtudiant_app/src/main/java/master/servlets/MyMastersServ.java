package master.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.MBoard;
import master.beans.MasterCard;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MasterDao;
import master.utils.SessionUtils;

@WebServlet(name="Mymasters", urlPatterns= {"/Mymasters"})
public class MyMastersServ extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(SessionUtils.isLoggedIn(request)) {
			try {
				String token = SessionUtils.getUserIdFromSession(request);
				int idEtudiant = OraFactory.getUserDao().getEtudiantIdFromToken(token);
				MasterDao masterDao = OraFactory.getMasterDao();
		        List<MasterCard> mCards = masterDao.getMasterCardsByIdEtudiant(idEtudiant);
		        request.setAttribute("mastercards", mCards);
		        List<MBoard> mboard = masterDao.getBoardInfo(idEtudiant);
		        request.setAttribute("mboard", mboard);

			} catch (EtudiantDaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	        this.getServletContext().getRequestDispatcher("/WEB-INF/mymasters-page.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("Signin");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
