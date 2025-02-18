package master.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ConfirmationCode", urlPatterns = {"/ConfirmationCode"})
@MultipartConfig
public class ConfirmationCode extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        
        if (s.getAttribute("tentative") == null) {
            int tentative = 3;
            s.setAttribute("tentative", tentative);
        }
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/RecuperPassWord/EmailCodeConfiramtion.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        HttpSession s = request.getSession();

        if (s == null || s.getAttribute("tentative") == null || s.getAttribute("Codeconfirmation") == null) {
            response.sendRedirect("ResponsableLogin");
            return;
        }

        String Codeconfirmation = (String) s.getAttribute("Codeconfirmation");
        int tentative = (int) s.getAttribute("tentative");

        if (code.equals(Codeconfirmation)) {
            response.sendRedirect("ChangePassWord");
        } else {
            tentative--;
            if (tentative > 0) {
                s.setAttribute("tentative", tentative);
                request.setAttribute("erreur", "Code incorrect (vous avez encore " + tentative + " tentative(s))");
                this.getServletContext().getRequestDispatcher("/WEB-INF/RecuperPassWord/EmailCodeConfiramtion.jsp").forward(request, response);
            } else {
                s.invalidate(); // Invalidate the session if no attempts left
                response.sendRedirect("ResponsableLogin");
            }
        }
    }
}
