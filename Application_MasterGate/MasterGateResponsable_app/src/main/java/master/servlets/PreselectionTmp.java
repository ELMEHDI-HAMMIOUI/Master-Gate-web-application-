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

@WebServlet(name = "PreselectionTmp", urlPatterns = {"/PreselectionTmp"})
public class PreselectionTmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option=request.getParameter("option");
		MasterDao MDAO=OraFactory.getMasterDao();
		String id_masterString=request.getParameter("id_master");
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
		ResponsableDao RDO = OraFactory.getResponsableDao();
		if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
		}else {	
			int id_master;
    		int id_respo = (int) session.getAttribute("id_respo") ;
	    	if(id_masterString!=null && !id_masterString.isEmpty()) {
		    	id_master=Integer.parseInt(id_masterString);
		    	
		    	
		    	if(option != null) {
		    		if(option.equals("delete")){
						if(request.getParameter("id_etudiant")!=null) {
							int id_etudiant=Integer.parseInt(request.getParameter("id_etudiant"));
							if(MDAO.deleteFromList(id_etudiant, id_master, "tmp_preselection")){//delete from preselection temporaire 
								System.out.println("doPost : deleted succefuly ");
							}
							else {
								System.out.println("doPost : failed to delete");
								request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
							}
						
						}else {
							System.out.println("doPost : id_etudiant is null");
							request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
			        	}	
					}
		    	}
		    	ResponsableCard respo = RDO.getRespoCardbyId(id_respo);
		    	MasterCard master=MDAO.getMasterCardById(id_master);
			    List<EtudiantListe> etudiantsTmpPre= MDAO.getMasterEtudiantsListe(id_master,"tmp_preselection");
			    int status=MDAO.getMasterStatus(id_master);
			    request.setAttribute("status", status);
			    request.setAttribute("master",master);
			    request.setAttribute("id_master",id_master);
			    request.setAttribute("respo", respo);
				request.setAttribute("etudiantsPreselectionnes", etudiantsTmpPre);	
				request.setAttribute("src", "preselectionTmp");
				this.getServletContext().getRequestDispatcher("/WEB-INF/ListTmpPreselection.jsp").forward(request, response);

	    	}else {
				System.out.println("doGet : id_master is null");
				request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
			}
    	}
	}

}
