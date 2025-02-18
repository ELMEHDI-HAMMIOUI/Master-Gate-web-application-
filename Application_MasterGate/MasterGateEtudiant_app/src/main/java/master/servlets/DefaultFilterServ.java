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
import master.dao.factory.OraFactory;

@WebServlet(name = "defaultFilter", urlPatterns = { "/defaultFilter" })
public class DefaultFilterServ extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//grabing the tag
    	String tag = (String) request.getSession().getAttribute("tag");
    	
		  List<MasterCard> mc = new ArrayList<MasterCard>();
		  mc = OraFactory.getMasterDao().getAllMasterCards();


		  
    	switch(tag) {
    	
    	  case "All":
    		  response.sendRedirect("Masters");
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
