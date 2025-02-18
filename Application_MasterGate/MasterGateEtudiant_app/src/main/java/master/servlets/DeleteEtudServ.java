package master.servlets;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.utils.SessionUtils;

@WebServlet(name = "delete", urlPatterns = { "/delete" })
public class DeleteEtudServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Profile");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		if(SessionUtils.isLoggedIn(request)) {
			try {
				//get idEtudiant
				String token = SessionUtils.getUserIdFromSession(request);
				int idEtudiant = OraFactory.getUserDao().getEtudiantIdFromToken(token);
				//delete Etudiant
				OraFactory.getUserDao().deleteEtudiant(idEtudiant);
				//signout
				response.sendRedirect("Signout");
				
			}catch (EtudiantDaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//if error occurs, send error msg to the profile page
				request.setAttribute("errorMsg", e.getMessage());
				doGet(request, response);
				
			}
		}
    }
}