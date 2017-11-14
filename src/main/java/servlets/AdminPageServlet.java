package servlets;

import columns.ColumnsProcessor;
import dao.JDBCMedEvalDAO;
import columns.ColumnsTitlesFileProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/adm")
public class AdminPageServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JDBCMedEvalDAO jmed = new JDBCMedEvalDAO();
        jmed.getConnection();
        List<String> colNames = jmed.getColNames();

        ColumnsTitlesFileProcessor ctf = new ColumnsTitlesFileProcessor();
        List<String> columnTitles = ctf.importTitles();
        System.out.println("admin servlet get");

        req.setAttribute("columns", colNames);
        req.setAttribute("columnTitles", columnTitles);
        req.setAttribute("index", colNames.size());
        req.getRequestDispatcher("adm.jsp").forward(req,resp);
        jmed.closeConnection();

        String deleteColName = req.getParameter("toDelete");
        System.out.println("admin servlet post");
        if (deleteColName!=null){
            new ColumnsProcessor().deleteColumn(deleteColName);
        }
        String before = req.getParameter("before");
        String after = req.getParameter("after");
        if(before != null & after != null){
            ColumnsProcessor cp = new ColumnsProcessor();
            cp.updateColumnTitle(before , after);
        }
        String newColumnName = req.getParameter("newColName");
        String newColumnTitle = req.getParameter("newColTitle");
        if (newColumnName != null & newColumnTitle != null ){
            ColumnsProcessor cp = new ColumnsProcessor();
            cp.addColumn(newColumnName, newColumnTitle);
        }
    }
}
