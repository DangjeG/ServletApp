package ServletPackage;

import ServicePackage.UserProfile;
import ServicePackage.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;



@WebServlet("/files")
public class MainServlet extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfile profile = UserService.getUserBySessionId(req.getSession().toString());
        if (profile == null) {
            resp.sendRedirect("/");
            return;
        }

        req.setAttribute("name", getUserDir(req).getAbsolutePath());
        req.setAttribute("files", getUserDir(req).listFiles());

        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfile profile = UserService.getUserBySessionId(req.getSession().toString());
        if (profile == null) {
            resp.sendRedirect("/");
            return;
        }

        File file = new File( req.getParameterValues("btn")[0]);

        if(file.getName().equals("parent")) {

            file = file.getParentFile();
            file = file.getParentFile();
        }

        if(file.equals(new File(File.listRoots()[0] + "\\java\\users")))
            file = getUserDir(req);
        if(!file.isDirectory())
            file = file.getParentFile();


        req.setAttribute("name", file.getAbsolutePath());
        req.setAttribute("files", file.listFiles());
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    private File getUserDir(HttpServletRequest req){
        UserProfile profile = UserService.getUserBySessionId(req.getSession().toString());
        File file = new File(File.listRoots()[0] + "\\java\\users\\" + profile.getLogin());
        if(!file.exists())
            file.mkdirs();
        return file;
    }
}
