package dao;

import vo.Evaluation;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCMedEvalDAO implements MedEvalDao {
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

    public void insert(Evaluation evaluation) {
        try {
            getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seev.grades");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int count = rsmd.getColumnCount();

            String sql = "INSERT INTO seev.grades (id_m_ins, comment";
            for (int i = 4; i <= count; i++) {
                sql = sql + ", " + rsmd.getColumnName(i);
            }
            sql = sql + ") VALUES( ?, ?";
            for (int i = 4; i <= count; i++) {
                sql = sql + ", ?";
            }
            sql = sql + ")";
            System.out.println(sql); //TODO remove when ready
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, evaluation.getId_m_ins());
            ps.setString(2, evaluation.getComment());
            for (int i = 0; i < evaluation.getEvals().size(); i++) {
                ps.setBoolean(i+3, evaluation.getEvals().get(i));
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Evaluation> select() {
        List<Evaluation> evaluations = new LinkedList<Evaluation>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seev.grades");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int count = rsmd.getColumnCount();
            for (int i = 4; i <= count; i++) {
                System.out.println(i+ " column name is: "+ rsmd.getColumnName(i));

            }
            //TODO need test
            Evaluation ev = null;
            while (resultSet.next()){
                ev = new Evaluation();
                ev.setId(Integer.parseInt(resultSet.getString(1)));
                ev.setId_m_ins(Integer.parseInt(resultSet.getString(2)));
                ev.setComment(resultSet.getString(3));
                List<Boolean> list = new LinkedList<Boolean>();
                for (int i = 4; i < count; i++) {
                    list.add(Boolean.parseBoolean(String.valueOf(resultSet.getBoolean(rsmd.getColumnName(i)))));
                }
                ev.setEvals(list);
                evaluations.add(ev);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluations;
    }


    public void deleteColumn(String colName) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("ALTER TABLE seev.grades DROP "+colName);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createColumn(String colName) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("ALTER TABLE seev.grades ADD "+ colName + " TINYINT NULL");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //return all column names except of first 3 (id, id_m_inst, comment)
    public List<String> getColNames(){
        List<String> columns = new LinkedList<String>();
        try {
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seev.grades");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            //because getColumnName indexation starts from 1
            for (int i = 4; i <= rsmd.getColumnCount(); i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
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

