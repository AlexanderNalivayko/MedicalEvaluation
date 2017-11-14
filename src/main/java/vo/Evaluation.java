package vo;

import java.util.List;

public class Evaluation {
    private int id;
    private int id_m_ins;
    private String comment;
    private List<Boolean> evals;

    public void setEvals(List<Boolean> evals) {
        this.evals = evals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_m_ins() {
        return id_m_ins;
    }

    public void setId_m_ins(int id_m_ins) {
        this.id_m_ins = id_m_ins;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Boolean> getEvals() {
        return evals;
    }
}
