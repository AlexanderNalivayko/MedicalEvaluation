package dao;

import vo.MedInst;
import java.util.List;

public interface MedInstDAO {
    public void insert(MedInst mi);
    public List<MedInst> select();
    public List<String> selectLocations();
}
