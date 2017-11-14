package dao;

import vo.MedInst;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCMedInstDAO implements MedInstDAO{

    private Connection connection = null;
    private final String URL = "jdbc:mysql://localhost:3306/seev?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return connection;
    }

    public void insert(MedInst mi) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO seev.m_ins (name, location) VALUES(?, ?)");
            ps.setString(1, mi.getName());
            ps.setString(2, mi.getLocation());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MedInst> select() {
        List<MedInst> medInstitutions = new LinkedList<MedInst>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seev.m_ins");
            MedInst medInst = null;
            while (resultSet.next()){
                medInst = new MedInst();
                medInst.setId(Integer.parseInt(resultSet.getString("id")));
                medInst.setName(resultSet.getString("name"));
                medInst.setLocation(resultSet.getString("location"));
                medInstitutions.add(medInst);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medInstitutions;
    }

    public List<String> selectLocations() {
        List<String> locations = new LinkedList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT location FROM seev.m_ins");

            while (resultSet.next()){
                locations.add(resultSet.getString(1));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }


    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
