package master.servlets;

import jakarta.servlet.ServletException;  
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.beans.Master;
import master.beans.ResponsableCard;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;

import java.io.IOException;
import java.util.Locale;



@WebServlet(name = "ResponsableMasterInformation", urlPatterns = {"/ResponsableMasterInformation"})
@MultipartConfig
public class ResponsableMasterInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @Override
	    public void init() throws ServletException {
	        super.init();
	        Locale.setDefault(Locale.FRENCH);
	    }
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 doPost(request,response);
		}
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
		ResponsableDao RDO = OraFactory.getResponsableDao();
		if (session == null || session.getAttribute("id_respo") == null) {
	        response.sendRedirect("ResponsableLogin");
	        return;
	    }
	    int id_respo = (Integer) session.getAttribute("id_respo");
	    ResponsableCard respo = RDO.getRespoCardbyId(id_respo);
	    request.setAttribute("respo", respo);	

	    ResponsableDao RDAO = OraFactory.getResponsableDao();
	    MasterDao MDAO = OraFactory.getMasterDao();    
	    String id_masterString = request.getParameter("id_master");

	    if (id_masterString != null && !id_masterString.isEmpty() && id_respo != 0) {
	        int id_master = Integer.parseInt(id_masterString);
	        
	        if (RDAO.checkMasterPermition(id_respo, id_master)) { // Vérifie si le responsable a les permissions pour accéder au master
				Master master=MDAO.getMasterById(id_master);
			    int status=MDAO.getMasterStatus(id_master);
			    request.setAttribute("status", status);
				request.setAttribute("master", master);
				request.setAttribute("id_master", id_master);
				request.setAttribute("src","masterInfo");
				request.setAttribute("ListeType","master");
	            this.getServletContext().getRequestDispatcher("/WEB-INF/ResponsableMasterInfo.jsp").forward(request, response);
	        } else {
	            response.sendRedirect("ResponsableHome");
	        }
	    } else {
	        response.sendRedirect("ResponsableHome");
	    }
 	}

}
