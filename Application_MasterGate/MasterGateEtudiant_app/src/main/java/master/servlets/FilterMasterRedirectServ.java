package master.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "filterMasterRedirect", urlPatterns = { "/filterMasterRedirect" })
public class FilterMasterRedirectServ extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String filter = "";
    	String tag = "";
    	
    	if(request.getParameter("filter") != null) {
    	  //storing the filter
          filter = request.getParameter("filter");
  		  request.getSession().setAttribute("filter", filter);
    	}
    	
    	if(request.getParameter("tag") != null) {
      	  //storing the tag
           tag = request.getParameter("tag");
 		  request.getSession().setAttribute("tag", tag);
      	}
    	 


		  
    	switch(filter) {
    	  case "default":
    		  response.sendRedirect("defaultFilter");
    	    break;
    	    
    	  case "noninscrit":
    		  response.sendRedirect("nonInscritFilter");
    	    break;
    	  default:

    	}

    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	
    }
}
