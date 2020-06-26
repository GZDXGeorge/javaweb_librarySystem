package Service.netUserService;

import DB.netUser.LoginDaoImpl;
import DB.netUser.netUser;
interface LoginService {
    netUser checkLoginService(String uNo,String uName,String psd);
    netUser checkLoginService(String uNo);
    netUser checkLoginService(String uNo,String uName);
}
public class LoginServiceImpl implements LoginService {
    LoginDaoImpl  loginDao = null;
    netUser user = null;
    public netUser checkLoginService(String uNo,String uName, String psd){
        loginDao=new LoginDaoImpl();
        try {
            user = loginDao.checkLoginDao(uNo, uName, psd);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    public netUser checkLoginService(String uNo){
        loginDao=new LoginDaoImpl();
        try {
            user = loginDao.checkLoginDao(uNo);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    public netUser checkLoginService(String uNo,String uName){
        loginDao=new LoginDaoImpl();
        try {
            user = loginDao.checkLoginDao(uNo, uName);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return user;
    }


}
