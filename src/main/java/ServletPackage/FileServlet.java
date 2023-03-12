package ServletPackage;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download/*")
public class FileServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File(req.getParameterValues("btn")[0]);
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition","attachment;filename="+file.getName());
        try
        {
            FileInputStream fileIn = new FileInputStream(file);
            ServletOutputStream out = resp.getOutputStream();

            byte[] outputByte = new byte[4096];

            while(fileIn.read(outputByte, 0, 4096) != -1)
            {
                out.write(outputByte, 0, 4096);
            }
            fileIn.close();
            out.flush();
            out.close();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
