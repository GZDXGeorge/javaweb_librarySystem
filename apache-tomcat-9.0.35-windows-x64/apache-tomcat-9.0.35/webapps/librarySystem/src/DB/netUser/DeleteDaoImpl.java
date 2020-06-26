package DB.netUser;

import static DB.CommonDao.executeUpdate;

interface  DeleteDao {
    public boolean doDelete(netUser user);
    public boolean doDelete(String uNo);
    public boolean doDelete(String uNo,String uName,String userPassword);
}
public class DeleteDaoImpl  implements DeleteDao {
    public boolean doDelete(String uNo){
        String sql = "Delete from netUser where uNo=?";
        int i = executeUpdate(sql,uNo);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doDelete(netUser user){
        return doDelete(user.getUNo(),user.getUserName(),user.getUserPassword());
    }
    public boolean doDelete(String uNo,String uName,String userPassword){
        String sql = "Delete from netUser where uNo=? and userName=? and userPassword=?";
        int i = executeUpdate(sql,uNo,uName,userPassword);
        if(i==1)
            return true;
        else
            return false;
    }
}
