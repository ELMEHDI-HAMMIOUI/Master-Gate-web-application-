package master.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.beans.DocsBean;
import master.beans.Etudiant;
import master.beans.Master;
import master.beans.MasterConditions;
import master.beans.exception.EtudiantBeansException;
import master.dao.exception.EtudiantDaoException;
import master.dao.exception.MCommentDaoException;
import master.dao.factory.OraFactory;
import master.utils.SessionUtils;


@WebServlet(name="Inscription", urlPatterns= {"/Inscription"})
@MultipartConfig
public class InscriptionServ extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idMaster = Integer.parseInt(request.getParameter("idMaster"));
		Master m = new Master();
		m = OraFactory.getMasterDao().getMasterById(idMaster);

		request.setAttribute("master", m);
		
		
		//get comments
		try {
			request.setAttribute("mcomments", OraFactory.getMCommentDao().getAllComments(idMaster));
		} catch (MCommentDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//grab the idEtudiant from session if it exist

		if(SessionUtils.isLoggedIn(request)) {
			try {
				//get the id from token
				String token = SessionUtils.getUserIdFromSession(request);
				int idEtudiant = OraFactory.getUserDao().getEtudiantIdFromToken(token);
				//check enrollement
				if(OraFactory.getMasterDao().checkEnrollement(idEtudiant, idMaster)) 
					request.setAttribute("inscMsg", "Vous êtes déja inscrit dans ce master");
				// the same thing if the conditions are not satisfied
				if(! conditionChecker( OraFactory.getUserDao().getProfileById(idEtudiant), OraFactory.getMasterDao().getMasterById(idMaster).getConditions() ) )
					request.setAttribute("errorMsg", "Malheureusement, les conditions requises n'ont pas été satisfaites");
				
			} catch (EtudiantDaoException ede) {
				ede.printStackTrace();
			}	
		}
		//check dates validity

		if( !checkDateFinInsc(m) )
			request.setAttribute("errorMsg", "Ce master est expiré" );

		//if the master is not open yet then send redirect to another page
		if( checkDateDebutInsc(m) ) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/master-inscription-page.jsp").forward(request, response);
			return;
		}
		else {
			response.sendRedirect("Masters");
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(SessionUtils.isLoggedIn(request)) {
			int idEtudiant = 0;
			int idMaster = Integer.parseInt(request.getParameter("idMaster"));
			//get the etudiant and the masterConditions
			MasterConditions mc = OraFactory.getMasterDao().getMasterById(idMaster).getConditions();
			Etudiant e = new Etudiant();
			try {
				String token = SessionUtils.getUserIdFromSession(request);
				idEtudiant = OraFactory.getUserDao().getEtudiantIdFromToken(token);
				
				e = OraFactory.getUserDao().getProfileById(idEtudiant);
			} catch (EtudiantDaoException ede) {
				//send error msg to jsp page
				request.setAttribute("errorMsg", ede.getMessage());
				doGet(request, response);
			}
			
			try {
				Master m = OraFactory.getMasterDao().getMasterById(idMaster);
				//check master date if they are still valid
				if( checkDateDebutInsc(m) && checkDateFinInsc(m)) {
					//check if the etudiant is not already enrolled in the master
					if(! OraFactory.getMasterDao().checkEnrollement(idEtudiant, idMaster)) {
						//if conditions are satisfied
						if(conditionChecker(e, mc)) {
							//get doc things
							
							DocsBean dc = new DocsBean();
							dc.setIdEtudiant(idEtudiant);
							dc.setIdMaster(idMaster);
							dc.setDocs( request.getPart("docs") );
							//inscrire dans le master
							OraFactory.getMasterDao().inscrire(dc);
							OraFactory.getDocsDao().addDocs(dc);
							
						}
						else {
							request.setAttribute("errorMsg", "Malheureusement, les conditions requises n'ont pas été satisfaites");
						}
					}
					else {
						request.setAttribute("inscMsg", "Vous êtes déja inscrit dans ce master");

					}
				}
				else {
					request.setAttribute("errorMsg", "Veullez verifier les date du master (début et fin)." );

				}
			} catch (EtudiantDaoException | EtudiantBeansException ede) {
				//send error msg to jsp page
				request.setAttribute("errorMsg", ede.getMessage());
				doGet(request, response);

			}
			
			doGet(request, response);
		}
		
		else {
			response.sendRedirect("Signin");
		}

	}
	
	
	
	//difference between two dates in years
    protected int datesDiff(LocalDate firstDate, LocalDate secondDate) {
        if ((firstDate != null) && (secondDate != null)) {
            return Period.between(firstDate, secondDate).getYears();
        } else {
            return 0;
        }
    }
    protected LocalDate stringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(dateString!= null) return LocalDate.parse(dateString, formatter);
        return null;
        
    }

	protected boolean conditionChecker(Etudiant e, MasterConditions condi) {
		
		if( datesDiff( stringToDate(e.getDateNaissance() ), LocalDate.now() ) > condi.getMax_age()) return false ;
		if( datesDiff( stringToDate(e.getDatePremiereInscription()), stringToDate(e.getDateGraduation()) ) > condi.getMax_annee_etude() ) return false ;
		if( e.getNoteS1() < condi.getNote_min_semestre() || e.getNoteS2() < condi.getNote_min_semestre() || e.getNoteS3() < condi.getNote_min_semestre() || e.getNoteS4() < condi.getNote_min_semestre() || e.getNoteS5() < condi.getNote_min_semestre() || e.getNoteS6() < condi.getNote_min_semestre() ) return false;
		if( ( e.getNoteS1() + e.getNoteS2() + e.getNoteS3() + e.getNoteS4() + e.getNoteS5() + e.getNoteS6() ) / 6 < condi.getNote_seuil() ) return false;
		
		return true;
	}
	
	//difference between two dates in days, useful for testing if the master due date is arrived 
    protected int datesDiffInDays(LocalDate firstDate, LocalDate secondDate) {
        if ((firstDate != null) && (secondDate != null)) {
            return Period.between(firstDate, secondDate).getDays();
        } else {
            return 0;
        }
    }
    
	
	protected boolean checkDateDebutInsc(Master m) {
		//if the d_debut_inscription > now()
		LocalDate d_debut = stringToDate( m.getD_debut_inscription() );
		if( datesDiffInDays(d_debut , LocalDate.now() ) < 0) {
			return false;
		}
		return true;
	}
	
	protected boolean checkDateFinInsc(Master m) {
		//if the D_fin_inscription < now()
		LocalDate d_fin = stringToDate( m.getD_fin_inscription() );
		if( datesDiffInDays(d_fin , LocalDate.now() ) >= 0) {
			return false;
		}
		
		return true;
	}


}

