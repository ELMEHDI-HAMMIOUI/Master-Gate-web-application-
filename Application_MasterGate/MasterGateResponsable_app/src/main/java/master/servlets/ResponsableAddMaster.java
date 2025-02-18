package master.servlets;


import java.io.IOException;  
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.beans.FilieresLicense;
import master.beans.Master;
import master.beans.MasterConditions;
import master.beans.Responsable;
import master.beans.ResponsableCard;
import master.dao.factory.OraFactory;
import master.dao.interfaces.FilieresLicenseDao;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;

@WebServlet(name = "ResponsableAddMaster", urlPatterns = {"/ResponsableAddMaster"})
@MultipartConfig
public class ResponsableAddMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilieresLicenseDao FDAO =OraFactory.getFilieresLicenseDao();
		ResponsableDao RDAO =OraFactory.getResponsableDao();
		//getting id of responsable to getrespoById()
		HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }else {
			int id_respo=(int)session.getAttribute("id_respo");
			ResponsableCard respo = RDAO.getRespoCardbyId(id_respo);
			List<FilieresLicense> filieres =FDAO.getFilieresLicense();
			request.setAttribute("respo",respo);
			request.setAttribute("filieres",filieres);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ResponsableAddMaster.jsp").forward(request, response);
	    }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getting DAOs
		MasterDao MDO=OraFactory.getMasterDao();
		ResponsableDao RDO=OraFactory.getResponsableDao();
		FilieresLicenseDao FDAO=OraFactory.getFilieresLicenseDao();
		
		//getting id of responsable to getrespoById()
		HttpSession session = request.getSession();
		int id_respo=(int)session.getAttribute("id_respo");
		
		//declaration master - responsable - filiere_admet - condition
		Master master = new Master();
		MasterConditions conditions=new MasterConditions();
		List<FilieresLicense> selectedFiliere = new ArrayList<FilieresLicense>();
		Responsable respo=RDO.getRespoByID(id_respo);
		//master
		String specialite=request.getParameter("specialite");
		String nom_coordinateur=request.getParameter("nom_coordinateur");
		String d_debut_inscription=request.getParameter("d_debut_inscription");
		String d_fin_inscription=request.getParameter("d_fin_inscription");
		String d_aff_preselection=request.getParameter("d_aff_preselection");
		String d_concours=request.getParameter("d_concours");
		String d_aff_resultat_concours=request.getParameter("d_aff_resultat_concours");
		String lieu_concours=request.getParameter("lieu_concours");
		String information=request.getParameter("information");
		String condition_str=request.getParameter("condition");
		String document=request.getParameter("document");
		//conditions
		int max_age=Integer.parseInt(request.getParameter("max_age"));
		int max_annee_etude=Integer.parseInt(request.getParameter("max_annee_etude"));
		double note_min_semestre= Double.parseDouble(request.getParameter("note_min_semestre"));	
		double note_seuil= Double.parseDouble(request.getParameter("note_seuil"));
		
		//we handle the filiere check (values returns an array )
		String[] selectedFiliereString = request.getParameterValues("selectedFiliere");
		int idFil=0;
		for(String sfs:selectedFiliereString) {
			idFil=Integer.parseInt(sfs);
			selectedFiliere.add(FDAO.getFilierById(idFil));
		}
		
		conditions.setMax_age(max_age);
		conditions.setMax_annee_etude(max_annee_etude);
		conditions.setNote_min_semestre(note_min_semestre);
		conditions.setNote_seuil(note_seuil);
		
		master.setFilieresAdmet(selectedFiliere);
		master.setRespo(respo);
		master.setConditions(conditions);
		
		master.setSpecialite(specialite);
		master.setNom_coordinateur(nom_coordinateur);
		master.setD_debut_inscription(d_debut_inscription);
		master.setD_fin_inscription(d_fin_inscription);
		master.setD_aff_preselection(d_aff_preselection);
		master.setD_concours(d_concours);
		master.setD_aff_resultat_concours(d_aff_resultat_concours);
		master.setLieu_concours(lieu_concours);
		master.setInformation(information);
		master.setCondition_str(condition_str);	
		master.setDocument(document);
		boolean isAdded = MDO.addMaster(master);
	
		request.setAttribute("isAdded", isAdded);
		if(isAdded) request.setAttribute("resultat","the inserted passed succefully");
		else request.setAttribute("resultat","the inserted failed");
		ResponsableDao RDAO =OraFactory.getResponsableDao();
		request.setAttribute("respo", respo);
		ResponsableCard respo1 = RDAO.getRespoCardbyId(id_respo);
		request.setAttribute("respo",respo1);
		request.getServletContext().getRequestDispatcher("/WEB-INF/ResponsableAddMaster.jsp").forward(request, response);
	}



}
