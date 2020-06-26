package DB.netUser;

import DB.CommonDao;
interface RegisterDao {
    public boolean doInsert(netUser user);
    public boolean doInsert(String uNo,String uName,String userPassword,String uJob,String uSex,int uAge);
}
public class RegisterDaoImpl extends CommonDao implements RegisterDao {
    public boolean doInsert(netUser user){
        return doInsert(user.getUNo(),user.getUserName(),user.getUserPassword(),user.getUserJob(),user.getUsex(),user.getUage());
    }
    public boolean doInsert(String uNo,String uName,String userPassword,String uJob,String uSex,int age){
        String sql = "Insert into netUser values(?,?,?,?,?,?)";
        int i= executeUpdate(sql, uNo,uName,userPassword,uJob,uSex,age);
        if(i==1)
            return true;
        else
            return false;
    }


}
