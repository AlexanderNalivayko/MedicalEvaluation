package columns;

import dao.JDBCMedEvalDAO;

import java.util.LinkedList;
import java.util.List;

public class ColumnsProcessor {

    public void deleteColumn(String columnName) {

        JDBCMedEvalDAO jmed = new JDBCMedEvalDAO();
        jmed.getConnection();
        List<String> names = jmed.getColNames();
        int index = names.indexOf(columnName);

        jmed.deleteColumn(columnName);

        ColumnsTitlesFileProcessor ctfp = new ColumnsTitlesFileProcessor();
        List<String> list = ctfp.importTitles();
        list.remove(index);
        ctfp.exportTitles(list);
        jmed.closeConnection();
    }

    public void updateColumnTitle(String columnTitleBefore, String columnTitleAfter){
        ColumnsTitlesFileProcessor ctfp = new ColumnsTitlesFileProcessor();
        List<String> list = ctfp.importTitles();
        int index = list.indexOf(columnTitleBefore);
        list.remove(index);
        list.add(index, columnTitleAfter);
        ctfp.exportTitles(list);
    }

    public void addColumn(String columnName, String columnTitle){
        JDBCMedEvalDAO jmed = new JDBCMedEvalDAO();
        jmed.getConnection();
        jmed.createColumn(columnName);
        ColumnsTitlesFileProcessor ctfp = new ColumnsTitlesFileProcessor();
        List<String> list = ctfp.importTitles();
        list.add(columnTitle);
        ctfp.exportTitles(list);
        jmed.closeConnection();
    }

}
