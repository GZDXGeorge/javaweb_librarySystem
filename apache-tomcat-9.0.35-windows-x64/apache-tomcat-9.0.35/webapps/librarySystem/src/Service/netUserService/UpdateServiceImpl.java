package Service.netUserService;

import DB.netUser.UpdateDaoImpl;
import DB.netUser.netUser;
interface UpdateService {
    public boolean doUpdate(netUser user);
    public boolean doUpdate(String uNo,String uName,String userPassword);
    public boolean doUpdate(String uNo,String uName,String userPassword,String userJob,String usex,double uage);
}
public class UpdateServiceImpl implements UpdateService {
    UpdateDaoImpl updateService=null;
    boolean aBoolean = true;
    public boolean doUpdate(netUser user){
        return doUpdate( user.getUNo(), user.getUserName(),user.getUserPassword(),user.getUserJob(),user.getUsex(), user.getUage());
    }
    public boolean doUpdate(String uNo,String uName,String userPassword){
        try{
            updateService =new UpdateDaoImpl();
            aBoolean = updateService.doUpdate(uNo,uName,userPassword);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return aBoolean;
    }

    public boolean doUpdate(String uNo,String uName,String userPassword,String userJob,String usex,double uage){
        try{
            updateService =new UpdateDaoImpl();
            aBoolean = updateService.doUpdate( uNo, uName, userPassword,userJob, usex, uage);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return aBoolean;
    }
}