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

@WebServlet(name = "RespoConcours", urlPatterns = {"/RespoConcours"})
@MultipartConfig
public class RespoConcours extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponsableDao RDO = OraFactory.getResponsableDao();
		MasterDao MDAO = OraFactory.getMasterDao();
		String option=request.getParameter("option");
		
		String id_masterString=request.getParameter("id_master");
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
	    if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }else {	int id_master;
			    int id_respo = (int) session.getAttribute("id_respo") ;
			    ResponsableCard respo = RDO.getRespoCardbyId(id_respo);
			    if(id_masterString!=null && !id_masterString.isEmpty()) {
			    	id_master=Integer.parseInt(id_masterString);
			    	if(option != null) {
			    		if(option.equals("delete")) {
			    			if(request.getParameter("id_etudiant")!=null) {
								int id_etudiant=Integer.parseInt(request.getParameter("id_etudiant"));
								if(MDAO.deleteFromList(id_etudiant, id_master,"tmp_preselection")){//delete from preselection temporaire 
									System.out.println("doPost : deleted succefully ");
								}
								else {
									System.out.println("doPost : failed to delete");
									request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
								}
			    			}else {
								System.out.println("doPost : id_etudiant null");
								request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
			    			}
			    			
// option 3 save score		    			
			    		}else if(option.equals("save")) {
				    			String[] scores   = request.getParameterValues("scores");
				                String[] studentIds = request.getParameterValues("etudiant_id");
				                int id_etd;
				                double score;
				                int j =0;
				                for(String i : scores) {
				                	if(!i.isEmpty()) {
				                		id_etd=Integer.parseInt(studentIds[j]);
				                		score=Double.parseDouble(i);
				                		if(MDAO.saveScore(score,id_etd,id_master)) {
				                			System.out.println("saved");
				                		 }else {
							                System.out.println("failed to save");
						                }
				                	}
				                	j++;
				                }
			    		}
			    	}
			    List<EtudiantListe> etudiantsPreselectionnes= MDAO.getMasterEtudiantsListe(id_master,"tmp_preselection");

			    MasterCard masterCard=MDAO.getMasterCardById(id_master);
			    int status=MDAO.getMasterStatus(id_master);
			    request.setAttribute("status", status);
				request.setAttribute("etudiantsPreselectionnes", etudiantsPreselectionnes);	
				request.setAttribute("master", masterCard);
				request.setAttribute("id_master",id_master);
				request.setAttribute("respo", respo);
				request.setAttribute("src", "concours");
				this.getServletContext().getRequestDispatcher("/WEB-INF/ListPreselectionnes.jsp?id_etudiant="+request.getParameter("id_etudiant")).forward(request, response);			
			}
	    }
    }

}
