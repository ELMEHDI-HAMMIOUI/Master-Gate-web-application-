package master.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.MasterCard;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.FacultesDao;
import master.dao.interfaces.FilieresLicenseDao;
import master.dao.interfaces.MasterDao;
import master.utils.MasterCardFilter;
import master.utils.SessionUtils;

@WebServlet(name="Masters", urlPatterns= {"/Masters"})
public class AllMastersServ extends HttpServlet{
	private static int pageNumber = 1;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//graber tout les filieres de license, cities, faculties, universities et les forwarder vers la jsp 
		FacultesDao fad = OraFactory.getFacultesDao();
		try {
			request.setAttribute("facultes", fad.getAllFacultes());
			request.setAttribute("specialities", fad.getAllSpecialities());		
			request.setAttribute("cities", fad.getAllCities());
			request.setAttribute("universities", fad.getAllUnis());

		
			}catch(EtudiantDaoException ede) {
			request.setAttribute("errorMsg", ede.getMessage());
		}
		
		/*
		//if no type is specified then get all the masters paginated
		if(request.getParameter("type") == null) {
			handleAllMastersRequest(request, response);
		}
		*/
		
		//if a type is requested then handle it and pass it to the filter
		//else {
			try {
				List<MasterCard> lmc = new ArrayList<MasterCard>();
				lmc = handleMastersRequest(request, response);
				lmc = handleFiltersRequest(request, response, lmc);
		        request.setAttribute("mastercards", lmc);
		        //this.getServletContext().getRequestDispatcher("/WEB-INF/allmasters-page.jsp").forward(request, response);

			}catch(Exception e) {
				e.printStackTrace();
			}
		//}
			//if(request.getParameter("type") == null) request.setAttribute("type", "all");
			//else request.setAttribute("type", request.getParameter("type"));

		    this.getServletContext().getRequestDispatcher("/WEB-INF/allmasters-page.jsp").forward(request, response);

		

	    
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int requestedPageNumber = Integer.parseInt(request.getParameter("page"));
//		pageNumber = requestedPageNumber;
//		System.out.println("from post" + pageNumber);
		doGet(request, response);
	}
	
	
	//method that handle master Requests, if the requested masters are : all, 
	//inscrit(masters dont je suis inscrit), lf(masters dont je suis affiché dans leur liste finale), la(masters dont je suis affiché dans leur liste d'attente)
	protected List<MasterCard> handleMastersRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MasterCard> lmc = new ArrayList<MasterCard>();//the returned list of mastercards
		MasterDao md = OraFactory.getMasterDao();
		String type = request.getParameter("type");
		
		//generate error if the type is null
		if(type == null) throw new Exception("Type non specifié");
		
		//if no type is specified then get all mastercards and exit the function
//		System.out.println("type: "+type);
//		if(type == null) {
//			lmc = md.getAllMasterCards();
//			return lmc;
//		}

		//getting the idEtudiant if it's logged in
		int idEtudiant = 0;
		if(SessionUtils.isLoggedIn(request)) {
			//get the id from token
			String token = SessionUtils.getUserIdFromSession(request);
			idEtudiant = OraFactory.getUserDao().getEtudiantIdFromToken(token);
		}
			
		switch(type) {
		  //if the requested type is: all
		  case "all":
		    lmc = md.getAllMasterCards();
		    break;
		    
		  //if the requested type is: pres(preselectioné)
		  case "pres":
			if( idEtudiant > 0 ) 	lmc = md.getPresMasterCards(idEtudiant);
			else throw new Exception("Connexion requise pour faire cette action");
		    break;
		    
		  //if the requested type is: lf(liste finale)
		  case "lf":
			if( idEtudiant > 0 ) 	lmc = md.getLfMasterCards(idEtudiant);
			else throw new Exception("Connexion requise pour faire cette action");
			break;
			
	      //if the requested type is: la(liste d'attente)
		  case "la":
			if( idEtudiant > 0 ) 	lmc = md.getLaMasterCards(idEtudiant);
			else throw new Exception("Connexion requise pour faire cette action");
			break;
			
		  //if the requested type is: insc(les masters dont je suis inscrit)
		  case "insc":
				if( idEtudiant > 0 ) 	lmc = md.getMasterCardsByIdEtudiant(idEtudiant);
				else throw new Exception("Connexion requise pour faire cette action");
				break;

		
		  //if the requested type is: noninsc(les masters dont je ne suis pas inscrit)
		  case "noninsc":
				if( idEtudiant > 0 ) 	lmc = md.getNonInscritMasterCards(idEtudiant);
				else throw new Exception("Connexion requise pour faire cette action");
				break;
				
		  default:
			  //generate error
			  throw new Exception("Type non specifié");
		}
		
		return lmc;
		
	}
	
	
	
	protected void handleAllMastersRequest(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		//init
		MasterDao masterDao = OraFactory.getMasterDao();

        //getting all mastercards records and total pages
        int totalRecords = masterDao.getTotalMasterCardsRecords();
        int totalPages = (int) Math.ceil((double) totalRecords / masterDao.MASTER_CARDS_PAGE_SIZE);

		//getting the requested page, if it is requested and < totalpages and > 0, else get the first page
		int requestedPageNumber = 1;
		if(request.getParameter("page") != null) {
			if( ( Integer.parseInt(request.getParameter("page")) > 0 ) && ( Integer.parseInt(request.getParameter("page")) <= totalPages ) ){
				requestedPageNumber = Integer.parseInt(request.getParameter("page"));
			}
		}
		pageNumber = requestedPageNumber;
		
		//getting all masters of the page 
        List<MasterCard> mCards = masterDao.getMasterCardsByPage(pageNumber);
        		
		//sending data
        request.setAttribute("mastercards", mCards);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        this.getServletContext().getRequestDispatcher("/WEB-INF/allmasters-page.jsp").forward(request, response);
 

	}
	
	/*
	protected List<MasterCard> handleFiltersRequest(HttpServletRequest request, HttpServletResponse response, List<MasterCard> parlmc) throws Exception{
		List<MasterCard> lmc = new ArrayList<MasterCard>();//the returned list of mastercards
		lmc.addAll(parlmc);
		//expired is radio button
		if(request.getParameter("expired").equals("true")) {
			lmc = MasterCardFilter.getExpiredMasterCards(lmc);
		}
		if(request.getParameter("expired").equals("false")) {
			lmc = MasterCardFilter.getNotExpiredMasterCards(lmc);
		}
		
		if(request.getParameter("new").equals("true")) {
			lmc = MasterCardFilter.getNotExpiredMasterCards(lmc);
		}
		
		//d_debut is radio button
		if(request.getParameter("date").equals("d_debut")) {
			MasterCardFilter.sortMasterCardsByDebutInscriptionDescending(lmc);
		}
		if(request.getParameter("date").equals("d_fin")) {
			MasterCardFilter.sortMasterCardsByFinInscriptionAscending(lmc);
		}
		
		//filter by cities
	    String[] cities = request.getParameterValues("city");
	    
		if(cities != null) {
		    List<String> citiesList = Arrays.asList(cities);
			lmc = MasterCardFilter.getMasterCardsByCities(lmc, citiesList);
		}

		//filter by faculties
	    String[] faculties = request.getParameterValues("faculty");
	    
		if(faculties != null) {
		    List<String> facultiesList = Arrays.asList(faculties);
			lmc = MasterCardFilter.getMasterCardsByFaculties(lmc, facultiesList);
		}
		
		//filter by universities
	    String[] universities = request.getParameterValues("university");
	    
		if(universities != null) {
		    List<String> universitiesList = Arrays.asList(universities);
			lmc = MasterCardFilter.getMasterCardsByUniversities(lmc, universitiesList);
		}
		
		//filter by specialities
	    String[] specialities = request.getParameterValues("speciality");
	    
		if(specialities != null) {
		    List<String> specialitiesList = Arrays.asList(specialities);
			lmc = MasterCardFilter.getMasterCardsBySpecialities(lmc, specialitiesList);
		}
		
		
		//return the list
		return lmc;
		
	}
	*/
	
    protected List<MasterCard> handleFiltersRequest(HttpServletRequest request, HttpServletResponse response, List<MasterCard> parlmc) throws Exception {
        List<MasterCard> lmc = new ArrayList<>(parlmc);

        String expired = request.getParameter("expired");
        if (expired != null) {
            if (expired.equals("true")) {
                lmc = MasterCardFilter.getExpiredMasterCards(lmc);
            } else if (expired.equals("false")) {
                lmc = MasterCardFilter.getNotExpiredMasterCards(lmc);
            }
        }

        String isNew = request.getParameter("new");
        if (isNew != null && isNew.equals("true")) {
            lmc = MasterCardFilter.getNewMasterCards(lmc);
        }

        String date = request.getParameter("date");
        //String order = request.getParameter("order");
        //the date input is a <select> so just one value will be passed, so there is no need to make an else if inside an if
        if (date != null) {
            if (date.equals("d_debut_asc")) {
                    MasterCardFilter.sortMasterCardsByDebutInscriptionAscending(lmc);
            } 
            
            if (date.equals("d_debut_desc")) {
                    MasterCardFilter.sortMasterCardsByDebutInscriptionDescending(lmc);
            }
            
        	if (date.equals("d_fin_desc")) {
                    MasterCardFilter.sortMasterCardsByFinInscriptionDescending(lmc);
            }
        	if(date.equals("d_fin_asc")){
                    MasterCardFilter.sortMasterCardsByFinInscriptionAscending(lmc);
            }
            
        }

        String[] cities = request.getParameterValues("city");
        if (cities != null) {
            List<String> citiesList = Arrays.asList(cities);
            for(String city: cities) {
            	System.out.println("from param: "+city);
            }
            for(MasterCard mcd: lmc) {
            	System.out.println("from mastercards: "+mcd.getVille());
            }
            lmc = MasterCardFilter.getMasterCardsByCities(lmc, citiesList);
        }

        String[] faculties = request.getParameterValues("faculty");
        if (faculties != null) {
            List<String> facultiesList = Arrays.asList(faculties);
            lmc = MasterCardFilter.getMasterCardsByFaculties(lmc, facultiesList);
        }

        String[] universities = request.getParameterValues("university");
        if (universities != null) {
            List<String> universitiesList = Arrays.asList(universities);
            lmc = MasterCardFilter.getMasterCardsByUniversities(lmc, universitiesList);
        }

        String[] specialities = request.getParameterValues("speciality");
        if (specialities != null) {
            List<String> specialitiesList = Arrays.asList(specialities);
            lmc = MasterCardFilter.getMasterCardsBySpecialities(lmc, specialitiesList);
        }

        return lmc;
    }
	
}
