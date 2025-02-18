package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.beans.EtudiantListe;
import master.beans.Publication;
import master.beans.ResponsableCard;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PublicationResultats", urlPatterns = {"/PublicationResultats"})
@MultipartConfig
public class PublicationResultats extends HttpServlet {
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
			String type_pub=request.getParameter("type_pub");
			request.setAttribute("type_pub",type_pub);

			if(type_pub!=null) {
				Publication pub=MDAO.getPublication(id_master, type_pub);
			    int status=MDAO.getMasterStatus(id_master);
			    request.setAttribute("status", status);
				request.setAttribute("id_master", id_master);
				if(type_pub.equals("PRE")) {
					List<EtudiantListe> etudiants= MDAO.getMasterEtudiantsListe(id_master,"preselection");
					request.setAttribute("pub", pub);
					request.setAttribute("etudiants", etudiants);
				    this.getServletContext().getRequestDispatcher("/WEB-INF/ResultatPreselection.jsp").forward(request, response);
				}else if(type_pub.equals("LFA")) {
					List<EtudiantListe> etudiantsFinale= MDAO.getMasterEtudiantsListe(id_master, "finale");
					List<EtudiantListe> etudiantsAttente= MDAO.getMasterEtudiantsListe(id_master, "attente");
					
					request.setAttribute("etudiantsFinale", etudiantsFinale);
					request.setAttribute("etudiantsAttente", etudiantsAttente);
					request.setAttribute("pub", pub);

				    this.getServletContext().getRequestDispatcher("/WEB-INF/ResultatsFinale.jsp").forward(request, response);

				}else {
					System.out.println("doGet : Type de publication est non définie");
				}
			}else {
				System.out.println("doGet : Type de publication est null");
			}
			
		}
	}

}
