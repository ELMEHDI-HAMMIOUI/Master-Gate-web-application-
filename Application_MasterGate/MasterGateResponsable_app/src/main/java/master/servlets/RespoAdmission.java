package master.servlets;

import jakarta.servlet.ServletException ;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;


@WebServlet(name = "RespoAdmission", urlPatterns = {"/RespoAdmission"})
public class RespoAdmission extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
	    if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }
	    ResponsableDao RDAO = OraFactory.getResponsableDao();
	    MasterDao MDAO = OraFactory.getMasterDao();
	    int id_respo = (Integer) session.getAttribute("id_respo");
	    String admis = request.getParameter("admis");
	    String id_etudiantString = request.getParameter("id_etudiant");
	    String id_masterString = request.getParameter("id_master");
	    if(id_etudiantString != null && !id_etudiantString.isEmpty() && id_masterString != null && !id_masterString.isEmpty()) {
	        int id_etudiant = Integer.parseInt(id_etudiantString);
	        int id_master = Integer.parseInt(id_masterString);
	        if(RDAO.checkMasterPermition(id_respo, id_master)) {
	            if(admis.equals("yes")) {
	                if(MDAO.AddPreInscriprion(id_etudiant, id_master)) {
	                	System.out.println("doPost : added to tmpPreselection with success");
            			response.sendRedirect("EtudeDossier?id_master="+id_master);
        				
	                }else {
	                	System.out.println("doPost : failed to added to tmpPreselection ");
	        			request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
	                }
	            }else if(admis.equals("no")) {
	            		if(MDAO.deleteFromList(id_etudiant, id_master,"inscription")) {
	            			response.sendRedirect("EtudeDossier?id_master="+id_master);
	            		}else {
	        				System.out.println("doPost : failed to delete ");
	            			request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
	            		}
	            	}
	        }else {			
				System.out.println("doPost : permission denied ");
				request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
		    }
	    }else {			    
			System.out.println("doPost : id(s) null ");
			request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
	    } 
	}

}
