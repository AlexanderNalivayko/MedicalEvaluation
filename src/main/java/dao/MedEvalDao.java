package dao;

import vo.Evaluation;

import java.util.List;

public interface MedEvalDao {
    public void insert(Evaluation evaluation);
    public List<Evaluation> select();
    public void deleteColumn(String colName);
    public void createColumn(String colName);
}
