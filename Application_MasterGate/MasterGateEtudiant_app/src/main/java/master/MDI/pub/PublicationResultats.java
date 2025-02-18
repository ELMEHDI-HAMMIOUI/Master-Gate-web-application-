package master.mhdi.pub;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.mhdi.pub.EtudiantListe;
import master.mhdi.pub.Publication;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MasterDao;

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
		if(id_masterString!=null && !id_masterString.isEmpty()) {
			id_master=Integer.parseInt(id_masterString);
			String type_pub=request.getParameter("type_pub");
			if(type_pub!=null) {
				Publication pub=MDAO.getPublication(id_master, type_pub);
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
