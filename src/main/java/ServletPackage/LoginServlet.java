package ServletPackage;

import ServicePackage.UserProfile;
import ServicePackage.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = req.getParameterValues("username")[0];
        String password = req.getParameterValues("password")[0];

        UserProfile profile = UserService.getUserByLogin(username);
        if (profile == null) {
            resp.getWriter().write("Please register");
            return;
        } else if (!profile.getPass().equals(password) ) {
            resp.getWriter().write("Incorrect password");
            return;
        }
        UserService.addSession(session.getId(), profile);
        resp.sendRedirect("/files");
    }
}
