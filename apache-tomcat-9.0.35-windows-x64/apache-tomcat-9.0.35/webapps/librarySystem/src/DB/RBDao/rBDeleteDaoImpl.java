package DB.RBDao;

import static DB.CommonDao.executeUpdate;

interface  rBDeleteDao{
    boolean doDelete(String uNo,String bNo);
    boolean doDelete(RB rb);
    boolean doDeleteByUNo(String uNO);
    boolean doDeleteByBno(String bNo);
}

public class rBDeleteDaoImpl {
    public boolean doDelete(String uNo,String bNo){
        String sql = "Delete from rb where uNo=? and bno=?";
        int i = executeUpdate(sql,uNo,bNo);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doDeleteByUNo(String uNo){
        String sql = "Delete from rb where uNo=? ";
        int i = executeUpdate(sql,uNo);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doDeleteByBNo(String bNo){
        String sql = "Delete from rb where bNo=? ";
        int i = executeUpdate(sql,bNo);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doDelete(RB rb){
        return doDelete(rb.getUNo(),rb.getBNo());
    }
}
