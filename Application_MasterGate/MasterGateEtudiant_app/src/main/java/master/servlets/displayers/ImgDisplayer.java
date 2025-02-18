package master.servlets.displayers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.dao.factory.OraFactory;
import master.utils.SessionUtils;

//import java.sql.Blob;
import java.sql.Blob;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//blog things


@WebServlet(name = "ImgDisplayer", urlPatterns = {"/ImgDisplayer"})
public class ImgDisplayer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
        try {
        	//get the etudiantId from the given token(for responsable use, just use the int etudiantId = request.getParameter("etudiantId"); you don't need the token ) 
			String token = SessionUtils.getUserIdFromSession(request);
			int etudiantId = OraFactory.getUserDao().getEtudiantIdFromToken(token);
			
			//connect to db
            Connection con = OraFactory.getConnection();
            
            PreparedStatement ps = con.prepareStatement("select photo from profile where id_etudiant = ?");
            ps.setInt(1, etudiantId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            Blob b = rs.getBlob("photo");
            response.setContentType("image/jpeg");
            response.setContentLength((int) b.length());
            InputStream is = b.getBinaryStream();
            OutputStream os = response.getOutputStream();
            byte buf[] = new byte[(int) b.length()];
            is.read(buf);
            os.write(buf);
            os.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.print("error lors de l'affichage d' image");
        }

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
