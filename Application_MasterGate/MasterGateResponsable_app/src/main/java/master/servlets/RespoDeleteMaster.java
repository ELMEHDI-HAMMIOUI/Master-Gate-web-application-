package master.servlets;

import jakarta.servlet.ServletException;
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


@WebServlet(name = "RespoDeleteMaster", urlPatterns = {"/RespoDeleteMaster"})
@MultipartConfig
public class RespoDeleteMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponsableDao RDAO = OraFactory.getResponsableDao();
		MasterDao MDAO = OraFactory.getMasterDao();	
		String id_masterString=request.getParameter("id_master");
		HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
	    if (session == null || session.getAttribute("id_respo") == null) {
		        response.sendRedirect("ResponsableLogin");
		        return;
	    }else {
			int id_respo = (Integer) request.getSession().getAttribute("id_respo");
			if(id_masterString!=null && !id_masterString.isEmpty() && id_respo!=0 ) {
				int id_master=Integer.parseInt(id_masterString);
				if(RDAO.checkMasterPermition(id_respo, id_master)) {
					MDAO.deleteMaster(id_master);
				}
			}
			response.sendRedirect("ResponsableHome");
		}
	}

}
