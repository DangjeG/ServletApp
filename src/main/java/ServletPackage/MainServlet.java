package ServletPackage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;


@WebServlet("/")
public class MainServlet extends HttpServlet {

    File file = new File("C://");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", file.getAbsolutePath());
        req.setAttribute("files", file.listFiles());

        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterValue = req.getParameterValues("btn")[0];
        if(!parameterValue.equals(" "))
            file = new File(req.getParameterValues("btn")[0]);
        else
            file = file.getParentFile();

        if(file==null)
            file = new File("C://");
        if(!file.isDirectory())
            file = file.getParentFile();


        req.setAttribute("name", file.getAbsolutePath());
        req.setAttribute("files", file.listFiles());
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
