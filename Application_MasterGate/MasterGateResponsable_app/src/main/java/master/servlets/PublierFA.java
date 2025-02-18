package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "PublierFA", urlPatterns = {"/PublierFA"})
@MultipartConfig
public class PublierFA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponsableDao RDO = OraFactory.getResponsableDao();
		MasterDao MDAO = OraFactory.getMasterDao();		
		String id_masterString=request.getParameter("id_master");
		String option=request.getParameter("option");
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
	    if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }else {
			    int id_respo = (int) session.getAttribute("id_respo") ;
			    if(id_masterString!=null && !id_masterString.isEmpty()) {
			    	int id_master;
			    	id_master=Integer.parseInt(id_masterString);
			    	if(option != null) {
			    		if(option.equals("publier")) {
			    			String titre= request.getParameter("titre");
			    			String sous_titre= request.getParameter("sous_titre");
			    			if(MDAO.publierListeFA(id_master,titre,sous_titre)) {
								System.out.println("doGet : résultats de preselection sont publier avec succés");
								response.sendRedirect("ResponsableMasterInformation?id_master="+id_master);								
								return;
			    			}else {
								System.out.println("doGet : failed to shared preselection !");
								request.getServletContext().getRequestDispatcher("/ErrorPage.jsp?error=Une erreur s'est produite lors de la publication des résultats de présélection. Veuillez réessayer").forward(request, response);
								return;
			    			}
			    		}
			    	}
			    	ResponsableCard respo = RDO.getRespoCardbyId(id_respo);
				    MasterCard master=MDAO.getMasterCardById(id_master);
					List<EtudiantListe> ListeFinale= MDAO.getMasterEtudiantsListe(id_master,"tmp_finale");
					List<EtudiantListe> ListeAttente= MDAO.getMasterEtudiantsListe(id_master,"tmp_attente");
				    request.setAttribute("master", master);
				    request.setAttribute("respo", respo);
					request.setAttribute("ListeFinale", ListeFinale);
					request.setAttribute("ListeAttente", ListeAttente); 
					request.setAttribute("sysdate", LocalDate.now());
					this.getServletContext().getRequestDispatcher("/WEB-INF/PublierFA.jsp").forward(request, response);			
   
			    }else {
					System.out.println("doGet : id_master is null");
					request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);

			    }
			    
	    }
	}

}
