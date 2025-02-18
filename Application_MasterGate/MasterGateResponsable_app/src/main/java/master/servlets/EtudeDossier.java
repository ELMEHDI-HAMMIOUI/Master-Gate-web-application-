package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.beans.EtudiantListe;
import master.beans.MasterCard;
import master.beans.ResponsableCard;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;
import java.util.List;



@WebServlet(name = "EtudeDossier", urlPatterns = {"/EtudeDossier"})
public class EtudeDossier extends HttpServlet {
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
		String option = request.getParameter("option");
		if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }
	    int id_respo = (Integer) session.getAttribute("id_respo");
	    ResponsableCard respo = RDO.getRespoCardbyId(id_respo);
	    request.setAttribute("respo", respo);
	    
		if(id_masterString!=null && !id_masterString.isEmpty()) {
			id_master=Integer.parseInt(id_masterString);
			
			
		    int status=MDAO.getMasterStatus(id_master);
		    request.setAttribute("status", status);
			List<EtudiantListe> etudiantsInscrits= MDAO.getMasterEtudiantsListe(id_master,"inscriptionEtudedossier");
			if (option != null) {
				if(option.equals("accept_all")) {
				    for (EtudiantListe etudiant : etudiantsInscrits) {
				        MDAO.AddPreInscriprion(etudiant.getId(), id_master);
				    }
					etudiantsInscrits= MDAO.getMasterEtudiantsListe(id_master,"inscriptionEtudedossier");

				}

			}
			if(MDAO.getMasterEtudiantsListe(id_master,"tmp_preselection").isEmpty()) {
				request.setAttribute("finEtudeDossier","false");
			}else {
				request.setAttribute("finEtudeDossier","true");
		    	MasterCard master=MDAO.getMasterCardById(id_master);
		    	request.setAttribute("master", master);
			}
			request.setAttribute("etudiantsInscrits", etudiantsInscrits);
			request.setAttribute("id_master", id_master);
			request.setAttribute("src", "etudeDossier");
		    this.getServletContext().getRequestDispatcher("/WEB-INF/ListCandidats.jsp").forward(request, response);
	
		}else {
			System.out.println("doGet : id_master is null");
			request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
		}
	}


}
