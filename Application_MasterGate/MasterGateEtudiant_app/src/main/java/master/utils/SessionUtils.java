/*package master.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {

    public static void storeUserInfoInSession(HttpServletRequest request, int userId, String nom, String prenom, String email) {
        HttpSession session = request.getSession(true); // Create a new session if one doesn't exist
        session.setAttribute("userId", userId);
        session.setAttribute("nom", nom);
        session.setAttribute("prenom", prenom);
        session.setAttribute("email", email);

    }

    public static Integer getUserIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Do not create a new session if one doesn't exist
        return (session != null) ? (Integer) session.getAttribute("userId") : null;
    }

    public static void removeUserInfoFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        return getUserIdFromSession(request) != null;
    }
}
*/
package master.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;

public class SessionUtils {

    private static final int COOKIE_MAX_AGE = 30 * 24 * 60 * 60; // 30 days in seconds

    public static void storeUserInfoInSession(HttpServletRequest request, HttpServletResponse response, int userId, String nom, String prenom, String email) {
        HttpSession session = request.getSession(true); // Create a new session if one doesn't exist
        try {
			String token = OraFactory.getUserDao().storeToken(userId);
	        session.setAttribute("userId", token);
	        session.setAttribute("nom", nom);
	        session.setAttribute("prenom", prenom);
	        session.setAttribute("email", email);

	        Cookie userIdCookie = new Cookie("userId", token);
	        userIdCookie.setMaxAge(COOKIE_MAX_AGE);
	        userIdCookie.setHttpOnly(true); // Security measure to prevent JavaScript access
	        userIdCookie.setPath("/");
	        response.addCookie(userIdCookie);
		} catch (EtudiantDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public static String getUserIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Do not create a new session if one doesn't exist
        if (session != null) {
            return (String) session.getAttribute("userId");
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    return String.valueOf(cookie.getValue());
                }
            }
        }
        return null;
    }

    public static void removeUserInfoFromSession(HttpServletRequest request, HttpServletResponse response) {
        try {
        String token = getUserIdFromSession(request);
        OraFactory.getUserDao().deleteTokenMapEntry(token);
			
    	HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }

        Cookie userIdCookie = new Cookie("userId", null);
        userIdCookie.setMaxAge(0); // Delete the cookie
        userIdCookie.setPath("/");
        response.addCookie(userIdCookie);
        

		} catch (EtudiantDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        return getUserIdFromSession(request) != null;
    }
}
