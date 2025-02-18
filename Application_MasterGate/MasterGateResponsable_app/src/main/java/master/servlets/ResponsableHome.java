package	master.servlets;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.beans.MasterCard;
import master.beans.ResponsableCard;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "ResponsableHome", urlPatterns = {"/ResponsableHome"})
@MultipartConfig

public class ResponsableHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @Override
	    public void init() throws ServletException {
	        super.init();
	        Locale.setDefault(Locale.FRENCH);
	    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponsableDao RDO = OraFactory.getResponsableDao();
		MasterDao MDAO = OraFactory.getMasterDao();
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
	    if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }else {	
		    int id_respo = (int) session.getAttribute("id_respo") ;
		    ResponsableCard respo = RDO.getRespoCardbyId(id_respo);
			List<MasterCard> mastercards =MDAO.getMasterCardsByRespoId(id_respo);
			request.setAttribute("mastercards", mastercards);
			 request.setAttribute("respo", respo);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ResponsableHome.jsp").forward(request, response);
	    	
	    }

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
