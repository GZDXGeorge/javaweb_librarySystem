package Service.RBService;

import DB.RBDao.RB;
import DB.RBDao.rBAddDaoImpl;

import java.util.Date;

interface  rBAddService{
    boolean doInsert(RB rb);
    boolean doInsert(String uNo, String bNo, String rbDate);
    boolean doInsert(String uNo, String bNo);
}
public class rBAddServiceImpl implements rBAddService{
    RB rb = null;
    rBAddDaoImpl rBAddDao =null;
    boolean  aBoolean = true;
    public boolean doInsert(RB rb){
        rBAddDao = new rBAddDaoImpl();
        try {
            aBoolean  = rBAddDao.doInsert(rb);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return  aBoolean;
    }
    public boolean doInsert(String uNo,String bNo,String rbDate){
        rBAddDao = new rBAddDaoImpl();
        try {
            aBoolean  =  rBAddDao.doInsert(uNo,bNo,rbDate);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return  aBoolean;
    }
    public boolean doInsert(String uNo,String bNo){
        rBAddDao = new rBAddDaoImpl();
        try {
            aBoolean  =  rBAddDao.doInsert(uNo,bNo);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return  aBoolean;
    }
}
