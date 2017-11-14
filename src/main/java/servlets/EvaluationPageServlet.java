package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ev")
public class EvaluationPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String institution = req.getParameter("institutions");
        System.out.println(institution);

        req.setAttribute("institution", institution);
        req.getRequestDispatcher("eval.jsp").forward(req,resp);
    }
}
