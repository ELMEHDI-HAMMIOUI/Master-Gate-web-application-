package master.servlets;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import master.beans.Etudiant;
import master.beans.exception.EtudiantBeansException;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.EtudiantDao;
import master.utils.SessionUtils;

@WebServlet(name = "update", urlPatterns = { "/update" })
@MultipartConfig
public class UpdateEtudiantServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(SessionUtils.isLoggedIn(request)) {
			Etudiant e = new Etudiant();
			try {
				EtudiantDao ud = OraFactory.getUserDao();
				
				//get etudiant id
				String token = SessionUtils.getUserIdFromSession(request);
				int idEtudiant = ud.getEtudiantIdFromToken(token);
				
				//get the etudiant info
				e = ud.getProfileById(idEtudiant);
				
				//send etudiant data
				request.setAttribute("e", e);
				
				int mstrNum = ud.getEnrolledMastersNumber(idEtudiant);
				//s'il n'est pas inscris à aucun master alors toute les infos doivent être changés
				if(mstrNum == 0) {
					request.setAttribute("all", "yes");
				}
				
				
			} catch (EtudiantDaoException ede) {
				ede.printStackTrace();
				request.setAttribute("errorMsg", ede.getMessage());
				//send an error msg
			}
			

			this.getServletContext().getRequestDispatcher("/WEB-INF/profile-edit.jsp").forward(request, response);
	
		}
		else {
			response.sendRedirect("Signin");
		}

	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		if(SessionUtils.isLoggedIn(request)) {
			try {
				EtudiantDao ud = OraFactory.getUserDao();
				//get idEtudiant
				String token = SessionUtils.getUserIdFromSession(request);
				int idEtudiant = ud.getEtudiantIdFromToken(token);
				
				//get les nouveaux info à updater
				Etudiant e = getEtudiantInfo(request, response);
				
				// s'il est inscris à un master, alors updateCustom()
				int mstrNum = ud.getEnrolledMastersNumber(idEtudiant);
				if(mstrNum > 0) {
					ud.updateEtudiantCustom(e);
				}
				//s'il n'est pas inscris à aucun master alors il peut modifier tout ses informations, updateAll()
				else if(mstrNum == 0) {
					ud.updateEtudiantAll(e);
				}
				
				

				
			}catch (EtudiantDaoException | EtudiantBeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//if error occurs, send error msg to the profile page
				request.setAttribute("errorMsg", e.getMessage());
				doGet(request, response);
				
			}
		}
		
		
		else {
			response.sendRedirect("Signin");
		}
		
    }
    
    
    //get etudiant info
    private Etudiant getEtudiantInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, EtudiantBeansException {
		String nom = request.getParameter("nom");        
		String prenom = request.getParameter("prenom");
		String cin = request.getParameter("cin");
		String telephone = request.getParameter("telephone");
		String date_n = request.getParameter("date_n");
		String sexe = request.getParameter("sexe");
		String massar = request.getParameter("massar");
		String nationalite = request.getParameter("nationalite");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String fac = request.getParameter("fac");
		String date_graduation = request.getParameter("date_graduation");
		String date_premiere_inscription = request.getParameter("date_premiere_inscription");
		String date_bac = request.getParameter("date_bac");
		String fil_license = request.getParameter("fil-license");
		String fil_bac = request.getParameter("fil-bac");
		String moy_bac = request.getParameter("moy-bac");
		String moy_lic = request.getParameter("moy-lic");
		String n_s1 = request.getParameter("n_s1");
		String n_s2 = request.getParameter("n_s2");
		String n_s3 = request.getParameter("n_s3");
		String n_s4 = request.getParameter("n_s4");
		String n_s5 = request.getParameter("n_s5");
		String n_s6 = request.getParameter("n_s6");
		//Part docs = request.getPart("docs");
		Part photo = request.getPart("photo");
		
		Etudiant e = new Etudiant();
		//To add: pour le moment les exceptions sont juste pour la photo et le docs
		//donc penser à les ajouter pour tout ses setters
		//try {
		e.setNom(nom);
		e.setPrenom(prenom);
		e.setDateNaissance(date_n);
		e.setSexe(sexe);
		e.setTel(telephone);
		e.setEmail(email);
		if( !password.isEmpty() )  e.setPassword(password);
		e.setNationalite(nationalite);
		e.setCin(cin);
		e.setMassar(massar);
		e.setDateBac(date_bac); // Assuming this should be date of birth
		e.setDateGraduation(date_graduation);
		e.setDatePremiereInscription(date_premiere_inscription);
		if((photo != null && photo.getSize() > 0))	e.setPhoto(photo);		
		e.setFilBac(fil_bac);
		e.setIdFaculte(0);
		e.setIdFilLicense(0);
		e.setMoyBac(Float.parseFloat(moy_bac));
		e.setMoyLicense(Float.parseFloat(moy_lic));
		e.setNoteS1(Float.parseFloat(n_s1));
		e.setNoteS2(Float.parseFloat(n_s2));
		e.setNoteS3(Float.parseFloat(n_s3));
		e.setNoteS4(Float.parseFloat(n_s4));
		e.setNoteS5(Float.parseFloat(n_s5));
		e.setNoteS6(Float.parseFloat(n_s6));
		e.setIdFilLicense(Integer.parseInt(fil_license)); 
		e.setIdFaculte(Integer.parseInt(fac));
		
//		}catch(EtudiantBeansException ede) {	
//			//send error msg 
//			request.setAttribute("errorMsg", ede.getMessage());
//			doGet(request, response);
//			
//		}
		
		return e;

    }
    
}