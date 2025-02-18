package master.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.MasterCard;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.utils.SessionUtils;

@WebServlet(name = "nonInscritFilter", urlPatterns = { "/nonInscritFilter" })
public class NonInscritFilterServ extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(SessionUtils.isLoggedIn(request)) {
        	//grabing the tag
        	String tag = (String) request.getSession().getAttribute("tag");
        	//grabing the filter
        	String filter = (String) request.getSession().getAttribute("filter");

        	int idEtudiant = 0;
			try {
			//grabbing the etudiantId
			String token = SessionUtils.getUserIdFromSession(request);
			idEtudiant = OraFactory.getUserDao().getEtudiantIdFromToken(token);
			    		  
			} catch (EtudiantDaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				  
        	//getting the masterCards that this etudiant has not yet inscribed  
    		  List<MasterCard> mc = new ArrayList<MasterCard>();
    		  mc = OraFactory.getMasterDao().getNonInscritMasterCards(idEtudiant);

			
        	switch(tag) {
        	
        	  case "All":
        		  request.setAttribute("mastercards", mc);
        		  this.getServletContext().getRequestDispatcher("/WEB-INF/filtered-masters-page.jsp").forward(request, response);
        	    break;
        	    
        	  case "Not Expired":
        		  List<MasterCard> notExpiredMasterCards = filterByNotExpired(mc);
        		  request.setAttribute("mastercards", notExpiredMasterCards);
        		  this.getServletContext().getRequestDispatcher("/WEB-INF/filtered-masters-page.jsp").forward(request, response);
        	    break;

        	  case "New":
        		  
        		  List<MasterCard> newMasterCards = filterByNew(mc);
        		  request.setAttribute("mastercards", newMasterCards);
        		  this.getServletContext().getRequestDispatcher("/WEB-INF/filtered-masters-page.jsp").forward(request, response);
        	    break;
        	    
        	  case "Expired":
        		  
        		  List<MasterCard> expiredMasterCards = filterByExpired(mc);
        		  request.setAttribute("mastercards", expiredMasterCards);
        		  this.getServletContext().getRequestDispatcher("/WEB-INF/filtered-masters-page.jsp").forward(request, response);
        	    break;
        	    
        	  default:

        	}
        	
    	}

    	else {
    		response.sendRedirect("Signin");
    	}

    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	
    }
    
    //filter a list of masterCards and return those who are not expired
    protected List<MasterCard> filterByNotExpired( List<MasterCard> mc ){
		List<MasterCard> filteredMc = new ArrayList<MasterCard>();
    	for(MasterCard m : mc) {
    		if( ! m.isExpired() ) {
    			filteredMc.add(m);
    		}
    	}
    	
    	return filteredMc;
    }
    
    //filter a list of masterCards and return those who are new
    protected List<MasterCard> filterByNew( List<MasterCard> mc ){
		List<MasterCard> filteredMc = new ArrayList<MasterCard>();
    	for(MasterCard m : mc) {
    		if( m.isNewMaster() ) {
    			filteredMc.add(m);
    		}
    	}
    	
    	return filteredMc;
    }
    
    
    //filter a list of masterCards and return those who are expired
    protected List<MasterCard> filterByExpired( List<MasterCard> mc ){
		List<MasterCard> filteredMc = new ArrayList<MasterCard>();
    	for(MasterCard m : mc) {
    		if( m.isExpired() ) {
    			filteredMc.add(m);
    		}
    	}
    	
    	return filteredMc;
    }
}
