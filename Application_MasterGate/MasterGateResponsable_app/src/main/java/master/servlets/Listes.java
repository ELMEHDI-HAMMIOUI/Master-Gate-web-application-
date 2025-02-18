package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.beans.EtudiantListe;
import master.beans.ResponsableCard;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Listes", urlPatterns = {"/Listes"})
@MultipartConfig
public class Listes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MasterDao MDAO=OraFactory.getMasterDao();
		int id_master;
		String id_masterString=request.getParameter("id_master");
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
		ResponsableDao RDO = OraFactory.getResponsableDao();
		if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }
	    int id_respo = (Integer) session.getAttribute("id_respo");
	    ResponsableCard respo = RDO.getRespoCardbyId(id_respo);
	    request.setAttribute("respo", respo);
	    
		if(id_masterString!=null && !id_masterString.isEmpty()) {
			id_master=Integer.parseInt(id_masterString);
			String ListeType = request.getParameter("ListeType");
			if(ListeType!=null) {
				List<EtudiantListe> ListeEtudiants= MDAO.getMasterEtudiantsListe(id_master, ListeType);
				if(ListeType.equals("finale")) {
					List<EtudiantListe> ListeAttente= MDAO.getMasterEtudiantsListe(id_master, "attente");
					request.setAttribute("ListeAttente", ListeAttente);
				}
			    int status=MDAO.getMasterStatus(id_master);
			    request.setAttribute("status", status);
				request.setAttribute("id_master", id_master);
				request.setAttribute("ListeEtudiants", ListeEtudiants);
				request.setAttribute("ListeType", ListeType);
				request.setAttribute("src", "masterInfo");
				request.getServletContext().getRequestDispatcher("/WEB-INF/Liste.jsp").forward(request, response);
			}
		}
	}

}
