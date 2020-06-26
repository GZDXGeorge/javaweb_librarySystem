package DB.netUser;

import DB.CommonDao;
interface LoginDao {
    netUser checkLoginDao(String uNo, String uName, String psd);
    netUser checkLoginDao(String uNo);
    netUser checkLoginDao(String uNo,String uName);
}
public class LoginDaoImpl  extends CommonDao implements LoginDao {
    public netUser checkLoginDao(String uNo, String uName, String psd){
        String sql = "Select * from netUser where uno=? and userName=? and  userPassword=?";
        return queryBeen(sql,netUser.class,uNo,uName,psd);
    }
    public netUser checkLoginDao(String uNo){
        String sql = "Select * from netUser where uno=?";
        return queryBeen(sql,netUser.class,uNo);
    }
    public netUser checkLoginDao(String uNo,String uName){
        String sql = "Select * from netUser where uno=? and userName=?";
        return queryBeen(sql,netUser.class,uNo,uName);
    }


}
