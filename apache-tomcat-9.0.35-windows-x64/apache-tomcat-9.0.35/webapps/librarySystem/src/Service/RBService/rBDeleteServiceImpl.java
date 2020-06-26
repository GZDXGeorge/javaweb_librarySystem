package Service.RBService;

import DB.RBDao.RB;
import DB.RBDao.rBDeleteDaoImpl;

interface rBDeleteService{
    boolean doDelete(String uNo,String bNo);
    boolean doDelete(RB rb);
    boolean doDeleteByUNo(String uNO);
    boolean doDeleteByBno(String bNo);
}
public class rBDeleteServiceImpl implements rBDeleteService {
    rBDeleteDaoImpl rBDeleteDao = null;
    boolean aBoolean = true;
    public boolean doDelete(String uNo,String bNo){
        rBDeleteDao = new rBDeleteDaoImpl();
        try{
            aBoolean  = rBDeleteDao.doDelete(uNo,bNo);
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return  aBoolean;
    }
    public boolean doDelete(RB rb){
        return   rBDeleteDao.doDelete(rb);
    }
    public boolean doDeleteByUNo(String uNo){
        rBDeleteDao = new rBDeleteDaoImpl();
        try{
            aBoolean  = rBDeleteDao.doDeleteByUNo(uNo);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return  aBoolean;
    }
    public boolean doDeleteByBno(String bNo){
        rBDeleteDao = new rBDeleteDaoImpl();
        try{
            aBoolean  = rBDeleteDao.doDeleteByBNo(bNo);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return  aBoolean;
    }
}
