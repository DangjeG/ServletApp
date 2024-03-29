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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameterValues("username")[0];
        String password = req.getParameterValues("password")[0];
        String email = req.getParameterValues("email")[0];
        if(UserService.getUserByLogin(username) != null){
            resp.getWriter().write("Account with this username already exists");
            return;
        }
        UserProfile profile = new UserProfile(username, password, email);
        UserService.addNewUser(profile);
        UserService.addSession(session.getId(), profile);
        resp.sendRedirect("/files");
    }
}
