package DB.netUser;

import DB.CommonDao;
interface UpdateDao {
    public boolean doUpdate(String uNo,String uName,String userPassword);
    public boolean doUpdate(String uNo,String uName,String userPassword,String userJob,String usex,double uage);
}
public class UpdateDaoImpl  extends CommonDao implements UpdateDao {
    public boolean doUpdate(String uNo,String uName,String userPassword){
        String sql = "Update  netUser set userPassword=? where uno =? and userName=?";
        int i = executeUpdate(sql,userPassword,uNo,uName);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doUpdate(String uNo,String uName,String userPassword,String userJob,String usex,double uage){
        String sql = "Update  netUser set userName=?,userPassword=?,userJob=?, usex=?, uage=? where uno =?";
        int i = executeUpdate(sql,uName,userPassword, userJob, usex,uage,uNo);
        if(i==1)
            return true;
        else
            return false;
    }
}
