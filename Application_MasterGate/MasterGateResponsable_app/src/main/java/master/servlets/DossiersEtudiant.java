package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.beans.Etudiant;
import master.beans.EtudiantListe;
import master.beans.MasterCard;
import master.beans.MasterConditions;
import master.beans.ResponsableCard;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.EtudiantDao;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DossiersEtudiant", urlPatterns = {"/DossiersEtudiant"})
public class DossiersEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
	    if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }
	    EtudiantDao ETDAO = OraFactory.getUserDao();
	    MasterDao MDAO=OraFactory.getMasterDao();
	    String id_etudiantString=request.getParameter("id_etudiant");
		int id_master;
		String id_masterString=request.getParameter("id_master");
		if(id_etudiantString != null && !id_etudiantString.isEmpty() && id_masterString!=null && !id_masterString.isEmpty()) {
			int e_id=Integer.parseInt(id_etudiantString);
			id_master=Integer.parseInt(id_masterString);
			try {
				Etudiant e=ETDAO.getProfileById(e_id);
				request.setAttribute("e",e);
			} catch (EtudiantDaoException e) {
				e.printStackTrace();
			}
			MasterConditions condition=MDAO.getMasterConditionByMasterId(id_master);
			request.setAttribute("condition", condition);
			request.setAttribute("id_master",id_master);
			request.setAttribute("traiter", request.getParameter("traiter"));//pour indiquer au jsp qu'on veux traiter (pour activer les boutons d'admission)
		    this.getServletContext().getRequestDispatcher("/WEB-INF/DossiersEtudiant.jsp").forward(request, response);
			
		}	
	}

}
