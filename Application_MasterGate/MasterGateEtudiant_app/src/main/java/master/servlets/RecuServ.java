package master.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.Etudiant;
import master.beans.Inscription;
import master.beans.Master;
import master.beans.MasterConditions;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.utils.SessionUtils;

@WebServlet(name="recu", urlPatterns= {"/recu"})
public class RecuServ extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idMaster = Integer.parseInt(request.getParameter("idMaster"));
		Master m = new Master();
		m = OraFactory.getMasterDao().getMasterById(idMaster);
		request.setAttribute("master", m);
		//grab the idEtudiant from session if it exist

		if(SessionUtils.isLoggedIn(request)) {
			try {
				//get id from given token
				String token = SessionUtils.getUserIdFromSession(request);
				int idEtudiant = OraFactory.getUserDao().getEtudiantIdFromToken(token);
				
				if(OraFactory.getMasterDao().checkEnrollement(idEtudiant, idMaster)) {
					//here goes the reçu handling
					Etudiant e = OraFactory.getUserDao().getProfileById(idEtudiant);
					Inscription in = OraFactory.getUserDao().getInsc(idEtudiant, idMaster); 
					request.setAttribute("e", e);
					request.setAttribute("in", in);
//					LocalDate anneAccLocalDate = stringToDate2(m.getD_fin_inscription());
//					String anneAcc = anneAccLocalDate.getYear() + "-" + anneAccLocalDate.plusYears(1).getYear();
//					request.setAttribute("anneAcc", anneAcc);
					this.getServletContext().getRequestDispatcher("/WEB-INF/recu-page.jsp").forward(request, response);

				}
								
			} catch (EtudiantDaoException ede) {
				ede.printStackTrace();
				request.setAttribute("errorMsg", ede.getMessage());
			}	
		}
		else {
			response.sendRedirect("Signin");
		}

		//response.sendRedirect("Inscription?idMaster=" + idMaster);
		//don't forget to add an other redirecting page if the id is incorrect or not set
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	
	
	//difference between two dates
    protected int datesDiff(LocalDate firstDate, LocalDate secondDate) {
        if ((firstDate != null) && (secondDate != null)) {
            return Period.between(firstDate, secondDate).getYears();
        } else {
            return 0;
        }
    }
    protected LocalDate stringToDate2(String dateString) {
    	System.out.println(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD / MM / YYYY");
        if(dateString!= null) return LocalDate.parse(dateString, formatter);
        return null;
        
    }




}

