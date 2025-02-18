package master.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.notification.Notif;
import master.beans.notification.NotifTag;
import master.dao.exception.EtudiantDaoException;
import master.dao.exception.NotifDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.NotifDao;
import master.utils.SessionUtils;
@WebServlet( name="Notifications", urlPatterns={"/Notifications"} ) 
public class NotifServ extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(SessionUtils.isLoggedIn(request)) {
			//int tagId = Integer.parseInt(request.getParameter("tagId"));
			
			NotifDao nd = OraFactory.getNotifDao();
	        List<NotifTag> tags;
	        List<Notif> notifs;
	        
			try {
				String token = SessionUtils.getUserIdFromSession(request);
				int idEtudiant = OraFactory.getUserDao().getEtudiantIdFromToken(token);
				
				tags = nd.getTags();
		        request.setAttribute("tags", tags);
		        
				for(int i = 0; i < tags.size(); i ++) {
					//get notifs by tag 
					notifs = nd.getNotifsByTagId(i+1, idEtudiant);
					//return notifs to jsp by tags
					String attributeName = tags.get(i).getTagName() + "Notifs";// --> InscriptionNotifs , PreselectionNotifs, ConcoursNotifs
					request.setAttribute(attributeName, notifs);

					//nombre de notifications non lues pour chaque tag
			        String notReadedNum = attributeName + "NotReadedNum";// --> InscriptionNotifsNotReadedNum , PreselectionNotifsNotReadedNum, ConcoursNotifsNotReadedNum
			        request.setAttribute(notReadedNum, getNotReadedNotifsNum( notifs ) );
			        //System.out.println(attributeName);
					
			        //System.out.println(notifs.get(0).isReaded());
					//pour le moment on a envoyer tout les tags et tout les notifs correspondants à ces tags,
					//mais dans la jsp l'ajout d'un autre tag ou les notifs correspondants à ce tag se fait 
					//manuellement--> penser à l'automatiser en faisant un forEach sur tout les tags, dans ce foreach
					//il sera un autre foreach pour les notifications
				}
				request.setAttribute("allNotifs", nd.getAllNotifsByOrder(idEtudiant));
				
				//read all notifs 
				OraFactory.getNotifDao().readNotif(idEtudiant);
				
			} catch (NotifDaoException | EtudiantDaoException e) {
				request.setAttribute("errorMsg", e.getMessage());
			}
			
			//notif page
	        this.getServletContext().getRequestDispatcher("/WEB-INF/notif-page.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("Signin");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	//return the number of not readed notifications on a list of notifications
	protected int getNotReadedNotifsNum(List<Notif> notifs) {
		int num = 0;
		for(Notif nf : notifs) {
			if(! nf.isReaded() ) num ++;
		}
		return num;
	}
}
