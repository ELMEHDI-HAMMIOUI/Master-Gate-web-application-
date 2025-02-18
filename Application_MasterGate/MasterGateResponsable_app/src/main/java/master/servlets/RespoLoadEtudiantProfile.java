package master.servlets;

import jakarta.servlet.ServletException ;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.Etudiant;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.EtudiantDao;

import java.io.IOException;

@WebServlet(name = "RespoLoadEtudiantProfile", urlPatterns = {"/RespoLoadEtudiantProfile"})
@MultipartConfig
public class RespoLoadEtudiantProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EtudiantDao ETDAO = OraFactory.getUserDao();
		String id_etudiantString=request.getParameter("id_etudiant");
		if(id_etudiantString != null && !id_etudiantString.isEmpty()) {
			int e_id=Integer.parseInt(id_etudiantString);
			try {
				Etudiant e=ETDAO.getProfileById(e_id);
				request.setAttribute("e",e);
			} catch (EtudiantDaoException e) {
				e.printStackTrace();
			}
		}
        this.getServletContext().getRequestDispatcher("/iframe/CandidatDossier.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
