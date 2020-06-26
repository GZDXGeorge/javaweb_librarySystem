package Service.netUserService;

import DB.netUser.getUserJsonDaoImpl;
import DB.netUser.netUser;

import java.util.ArrayList;
import java.util.List;

import static DB.CommonDao.queryBeen;
import static DB.CommonDao.queryBeenList;

public class getUserJsonServiceImpl {
    List<netUser>lst = null;
    public List<netUser> getUserJson(){
           try{
               lst = new getUserJsonDaoImpl().getUserJson();
           }
           catch(Exception ex){
               ex.printStackTrace();
           }
           return lst;
    }
    public List<netUser> getUserJsonByUno(String uno){
        try{
          lst = new getUserJsonDaoImpl().getUserJsonByUno(uno);
        }catch(Exception ex){
            ex.printStackTrace();
        }
       return lst;
    }
    public List<netUser>getUserJsonByUserName(String name){
        try{
            lst = new getUserJsonDaoImpl().getUserJsonByUserName( name);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lst;
    }
    public  List<netUser>getUserJsonByUserJob(String job){
        try{
            lst = new getUserJsonDaoImpl().getUserJsonByUserJob( job);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lst;
    }
    public  List<netUser>getUserJsonByUserSex(String sex){
        try{
            lst = new getUserJsonDaoImpl().getUserJsonByUserSex( sex);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return lst;
    }
    public  List<netUser>getUserJsonByUserAge(String  age ){
        try{
            lst = new getUserJsonDaoImpl().getUserJsonByUserAge(age);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return lst;
    }
    public int  getUserSum(){
        try {
            return new getUserJsonDaoImpl().getUserSum();
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }
    public  List<netUser>getUserJsonByPage( int pageNow,int pageSize) {
        try{
            lst = new getUserJsonDaoImpl().getUserJsonByPage(pageNow,pageSize);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return lst;
    }
}
