package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vo.Evaluation;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JDBCMedEvalDAOTest {

    Evaluation ev= null;
    int id = 255;
    int id_m_ins = 21;
    String comment = "test";
    Boolean b1 = true;
    Boolean b2 = false;

    @BeforeEach
    void setUp() {
        ev = new Evaluation();
        ev.setId(id);
        ev.setId_m_ins(id_m_ins);
        ev.setComment(comment);
        ev.setEvals(Arrays.asList(b1, b2, b1, b2, b1, b2, b1));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert() {
        JDBCMedEvalDAO jmed = new JDBCMedEvalDAO();
        jmed.getConnection();
        jmed.insert(ev);
        List<Evaluation> e = jmed.select();
        jmed.closeConnection();
        System.out.println(e.get(e.size()-1).getComment());

        assertEquals(id_m_ins, e.get(e.size()-1).getId_m_ins());
        assertEquals(comment, e.get(e.size()-1).getComment());
        assertEquals(b1 ,e.get(e.size()-1).getEvals().get(0));
        assertEquals(b2, e.get(e.size()-1).getEvals().get(1));
    }


    @org.junit.jupiter.api.Disabled
    void createDeleteColumn() {

        String columnName = "testColumn";
        int columnsBefore = 0;
        int columnsAfter = 0;
        ResultSetMetaData rsmd = null;

        JDBCMedEvalDAO jmed = new JDBCMedEvalDAO();
        Connection conn = jmed.getConnection();
        try {
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM seev.grades");
            rsmd = resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            columnsBefore = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jmed.createColumn(columnName);


        try {
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM seev.grades");
            rsmd = resultSet.getMetaData();
            columnsAfter = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(columnsBefore+1, columnsAfter);

        jmed.deleteColumn(columnName);

        columnsBefore = columnsAfter;

        try {
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM seev.grades");
            rsmd = resultSet.getMetaData();
            columnsAfter = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(columnsBefore-1, columnsAfter);
    }

}