package servlets;

import dao.JDBCMedEvalDAO;
import dao.JDBCMedInstDAO;
import vo.Evaluation;
import vo.MedInst;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = "/start")
public class StartPageServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        JDBCMedInstDAO db = new JDBCMedInstDAO();
        db.getConnection();
        String selected = "Всі";

        selected = request.getParameter("region");
        selected = selected == null ? "Всі": selected;
        List<MedInst> institutions = db.select();
        List<String> institutionsNames = new LinkedList<String>();

        if (selected.equals("Всі")) {
            for (int i = 0; i < institutions.size(); i++) {
                institutionsNames.add(institutions.get(i).getName());
            }
        }else{
            for (int i = 0; i < institutions.size(); i++) {
                if(institutions.get(i).getLocation().equals(selected)){
                    institutionsNames.add(institutions.get(i).getName());
                }
            }
        }
        
        List<String> location = db.selectLocations();
        location.add(0, "Всі");

        request.setAttribute("institutions", institutionsNames);
        request.setAttribute("locations", location);
        request.setAttribute("selected", selected);
        request.getRequestDispatcher("startpage.jsp").forward(request, response);
        db.closeConnection();
    }
}
