package Service.netUserService;

import DB.netUser.RegisterDaoImpl;
import DB.netUser.netUser;
interface RegisterService {
    boolean doInsert(netUser user);
    boolean doInsert(String uNo,String uName,String userPassword,String uJob,String uSex,int uAge);
}
public class RegisterServiceImpl  implements RegisterService {
    RegisterDaoImpl registerDao = null;
    boolean aBoolean = true;
    public boolean doInsert(netUser user){
        try{
            registerDao = new RegisterDaoImpl();
            aBoolean = registerDao.doInsert(user);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return aBoolean;
    }
    public boolean doInsert(String uNo, String uName, String userPassword, String uJob, String uSex, int uAge){
        try{
            registerDao = new RegisterDaoImpl();
            aBoolean = registerDao.doInsert( uNo,uName,  userPassword, uJob,  uSex, uAge);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return aBoolean;
    }
}
