package master.servlets;

import java.io.IOException;
import java.sql.Clob;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.MComment;
import master.dao.exception.EtudiantDaoException;
import master.dao.exception.MCommentDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MCommentDao;
import master.utils.SessionUtils;

@WebServlet(name="comment", urlPatterns= {"/comment"} )
public class CommentServ extends HttpServlet{
	
	MCommentDao mcdo = OraFactory.getMCommentDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getting all comments
		try {
			int idMaster = Integer.parseInt( request.getParameter("idMaster") );
			request.setAttribute("mcomments", mcdo.getAllComments(idMaster));
			this.getServletContext().getRequestDispatcher("/WEB-INF/comment-page.jsp").forward(request, response);

		} catch (MCommentDaoException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/comment-page.jsp").forward(request, response);

		}

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idMaster = Integer.parseInt( request.getParameter("idMaster") );
		
		if(SessionUtils.isLoggedIn(request)) {
			//adding the comment
			try {
				MComment mc = comInit(request, response);
				mcdo.addComment(mc);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
			}	
		}
		
		response.sendRedirect("Inscription?idMaster=" + idMaster);
	}
	
	
	protected MComment comInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//do this before doPost()

		//the returned comment
		MComment mc = new MComment();

		try {
			//getting parameters
			int idMaster = Integer.parseInt( request.getParameter("idMaster") );
			//get idEtudiant from given token
			String token = request.getParameter("token");
			int idEtudiant = OraFactory.getUserDao().getEtudiantIdFromToken(token);
			String txt = request.getParameter("txt");
			
			//setting up the comment Bean
			mc.setIdEtd(idEtudiant);
			mc.setIdMaster(idMaster);
			if(!txt.isBlank()) {
				mc.setTxt(txt);	
			}
			else {
				throw new Exception("Le texte ne doit pas etre vide.");
			}


		}catch(EtudiantDaoException edx) {
			edx.printStackTrace();
		}
		
		return mc;
		
	}
	
	
}
