package Service.netUserService;

import DB.netUser.DeleteDaoImpl;
import DB.netUser.netUser;
interface DeleteService {
    boolean doDelete(String uNo);
    boolean doDelete(netUser user);
    boolean doDelete(String uNo,String uName,String userPassword);
}
public class DeleteServiceImpl  implements DeleteService {
    DeleteDaoImpl deleteDao =null;
    boolean aBoolean = true;
    public boolean doDelete(String uNo){
        try{
            deleteDao = new DeleteDaoImpl();
            aBoolean =  deleteDao.doDelete(uNo);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return aBoolean;
    }
    public boolean doDelete(netUser user){
        return  doDelete(user.getUNo(),user.getUserName(),user.getUserPassword());
    }
    public boolean doDelete(String uNo, String uName, String userPassword){
        try{
            deleteDao = new DeleteDaoImpl();
            aBoolean =  deleteDao.doDelete(uNo,uName,userPassword);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return aBoolean;
    }
}
