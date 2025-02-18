package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.MasterCard;
import master.dao.factory.OraFactory;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "home", urlPatterns = {"/home"})

public class HomeServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MasterCard> mCards = OraFactory.getMasterDao().getMasterCardsByPage(1);
        request.setAttribute("mastercards", mCards);

		this.getServletContext().getRequestDispatcher("/WEB-INF/home-page.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
