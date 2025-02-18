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
import java.util.List;


@WebServlet(name = "RespoGestionListeFA", urlPatterns = {"/RespoGestionListeFA"})
@MultipartConfig
public class RespoGestionListeFA extends HttpServlet {
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
			String option=request.getParameter("option");
			if(option!=null) {
				if(option.equals("delete")) {//supprimer un etudiant
					String id_etudiantString=request.getParameter("id_etudiant");
					if(id_etudiantString!=null) {
						int id_etudiant=Integer.parseInt(id_etudiantString);
						if(MDAO.deleteFromList(id_etudiant, id_master, "tmp_finale")) {
							System.out.println("deleted succefully");
						}else {
							System.out.println("failed to delete");
						}
					}
				}else if(option.equals("numberOf")) {
					String NfinaleString = request.getParameter("Nfinale");
					String NattenteString = request.getParameter("Nattente");
					if(NfinaleString != null && NattenteString!=null) {
						int Nfinale=Integer.parseInt(NfinaleString);
						int Nattente=Integer.parseInt(NattenteString);
						if(Nfinale>=0 && Nattente>=0) {
							if(MDAO.generateListeFA(id_master,Nfinale,Nattente)) {
								System.out.println("Listes has been succesfuly genarated ");
							}else {
								System.out.println("failed to genarate the Lists");
								request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
							}
						}	
					}
				}else if(option.equals("ModifierNumberOf")) {
						request.setAttribute("emptyListe", "true");
						request.setAttribute("id_master", id_master);
				}else if(option.equals("gestionListe")) {
					int Nfinale=MDAO.getSizeOf(id_master, "tmp_finale");
					int Nattente=MDAO.getSizeOf(id_master, "tmp_attente");
					if(Nfinale>=0 && Nattente>=0) {
						if(MDAO.generateListeFA(id_master, Nfinale, Nattente)){
							System.out.println("Listes has been succesfuly genarated ");
						}
					}
				}
			}
			
			MasterCard master=MDAO.getMasterCardById(id_master);
			request.setAttribute("master",master);
			List<EtudiantListe> ListeFinale= MDAO.getMasterEtudiantsListe(id_master,"tmp_finale");
			List<EtudiantListe> ListeAttente= MDAO.getMasterEtudiantsListe(id_master,"tmp_attente");
			if(ListeFinale.isEmpty() && ListeAttente.isEmpty()) {
				request.setAttribute("emptyListe", "true");
			}
			
		    int status=MDAO.getMasterStatus(id_master);
		    request.setAttribute("status", status);
			request.setAttribute("src","GestionFA");
			request.setAttribute("ListeFinale", ListeFinale);
			request.setAttribute("ListeAttente", ListeAttente);
			request.setAttribute("id_master", id_master);
		    this.getServletContext().getRequestDispatcher("/WEB-INF/ListeFA.jsp").forward(request, response);
		}else {
			System.out.println("doGet : id_master is null");
			request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
		}
	}

}
