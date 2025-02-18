package master.servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import master.dao.exception.EtudiantDaoException;
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


@WebServlet(name = "DocsDisplayerRespo", urlPatterns = {"/DocsDisplayerRespo"})
public class DocsDisplayerRespo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idEtudiant = Integer.parseInt( request.getParameter("etudiantId") );
		int idMaster = Integer.parseInt(request.getParameter("idMaster"));

        try {
        	
			//connect to db
            Connection con = OraFactory.getConnection();

            PreparedStatement ps = con.prepareStatement("select doc from doc where id_etudiant = ? AND id_master = ? ");
            ps.setInt(1, idEtudiant);
            ps.setInt(2, idMaster);

            ResultSet rs = ps.executeQuery();
            rs.next();
            
            Blob b = rs.getBlob("doc");
            response.setContentType("application/pdf");
            response.setContentLength((int) b.length());
            InputStream is = b.getBinaryStream();
            OutputStream os = response.getOutputStream();
            byte buf[] = new byte[(int) b.length()];
            is.read(buf);
            os.write(buf);
            os.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.print("error lors de l'affichage du document");
        }

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
