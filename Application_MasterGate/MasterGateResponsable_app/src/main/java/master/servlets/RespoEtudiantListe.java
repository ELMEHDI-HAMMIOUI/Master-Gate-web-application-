package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.EtudiantListe;
import master.beans.Master;
import master.beans.MasterCard;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MasterDao;

import java.io.IOException;
import java.util.List;
import java.util.Locale;



@WebServlet(name = "RespoEtudiantListe", urlPatterns = {"/RespoEtudiantListe"})
@MultipartConfig
public class RespoEtudiantListe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @Override
	    public void init() throws ServletException {
	        super.init();
	        Locale.setDefault(Locale.FRENCH);
	    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MasterDao MDAO=OraFactory.getMasterDao();
		int id_master;
		String src=request.getParameter("src");
		String id_masterString=request.getParameter("id_master");
		if(id_masterString!=null && !id_masterString.isEmpty()) {
			id_master=Integer.parseInt(id_masterString);
		  	MasterCard masterCard=MDAO.getMasterCardById(id_master);
		  	request.setAttribute("src", src);
			switch(src) {
					case "master":
						Master master=MDAO.getMasterById(id_master);
						request.setAttribute("master", master);
					    this.getServletContext().getRequestDispatcher("/iframe/MasterPage.jsp").forward(request, response);
				    break;	 
				  case "inscription":
					    	List<EtudiantListe> etudiantsInscrits= MDAO.getMasterEtudiantsListe(id_master,src);
							request.setAttribute("etudiantsInscrits", etudiantsInscrits);	
							request.setAttribute("master", masterCard);
							if(request.getParameter("id_etudiant")!=null) {
								request.setAttribute("id_etudiant", request.getParameter("id_etudiant"));
							}else {
								request.setAttribute("id_etudiant",0);
							}
							
					    this.getServletContext().getRequestDispatcher("/iframe/ListCandidats.jsp").forward(request, response);
				    break;
				  case "preselection":
					  List<EtudiantListe> etudiantsPreselectionnes= MDAO.getMasterEtudiantsListe(id_master,src);
						request.setAttribute("etudiantsPreselectionnes", etudiantsPreselectionnes);	
						request.setAttribute("master", masterCard);
						this.getServletContext().getRequestDispatcher("/iframe/ListPreselectionnes.jsp?id_etudiant="+request.getParameter("id_etudiant")).forward(request, response);
				    break;
				  case "finale":
					  List<EtudiantListe> etudiantsAdmis= MDAO.getMasterEtudiantsListe(id_master,src);
						request.setAttribute("etudiantsAdmis", etudiantsAdmis);	
						request.setAttribute("master", masterCard);
					    this.getServletContext().getRequestDispatcher("/iframe/ListAdmis.jsp?id_etudiant="+request.getParameter("id_etudiant")).forward(request, response);
				  break;
				  case "attente":
					  List<EtudiantListe> etudiantsAttente= MDAO.getMasterEtudiantsListe(id_master,src);
						request.setAttribute("etudiantsAttente", etudiantsAttente);	
						request.setAttribute("master", masterCard);
					    this.getServletContext().getRequestDispatcher("/iframe/ListAttente.jsp?id_etudiant="+request.getParameter("id_etudiant")).forward(request, response);
				  break;
				  case "tmp_preselection":
					  List<EtudiantListe> etudiantsTmpPre= MDAO.getMasterEtudiantsListe(id_master,src);
						request.setAttribute("etudiantsPreselectionnes", etudiantsTmpPre);	
						request.setAttribute("master", masterCard);
					    this.getServletContext().getRequestDispatcher("/iframe/ListTmpPreselection.jsp").forward(request, response);
				  break;
				  
				  
				  default:
			}
		}else {
			this.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
		}
	}

}
