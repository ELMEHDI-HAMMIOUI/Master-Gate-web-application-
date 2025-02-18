package master.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.Facultes;
import master.beans.MasterCard;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.FacultesDao;
import master.dao.interfaces.MasterDao;

@WebServlet(name="facultes", urlPatterns= {"/facultes"})
public class FacultesServ extends HttpServlet{
	private static int pageNumber = 1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//init
		FacultesDao fd = OraFactory.getFacultesDao();

        //getting all FACULTES HAVING A MASTER records and total pages
//        int totalRecords = fd.getTotalFacsRecords();
		int totalRecords = fd.getTotalFacsRecordsHavingMaster();
        int totalPages = (int) Math.ceil((double) totalRecords / fd.FACULTES_CARDS_PAGE_SIZE);

		//getting the requested page, if it is requested and < totalpages and > 0, else get the first page
		int requestedPageNumber = 1;
		if(request.getParameter("page") != null) {
			if( ( Integer.parseInt(request.getParameter("page")) > 0 ) && ( Integer.parseInt(request.getParameter("page")) <= totalPages ) ){
				requestedPageNumber = Integer.parseInt(request.getParameter("page"));
			}
		}
		pageNumber = requestedPageNumber;
		
		//getting all masters of the page 
		List<Facultes> facs = null;
		try {
			facs = fd.getFacsHavingMasterByPage(pageNumber);
		} catch (EtudiantDaoException e) {
	        request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
        		
		//sending data
        request.setAttribute("facs", facs);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        this.getServletContext().getRequestDispatcher("/WEB-INF/all-facultes-page.jsp").forward(request, response);
 
		
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
