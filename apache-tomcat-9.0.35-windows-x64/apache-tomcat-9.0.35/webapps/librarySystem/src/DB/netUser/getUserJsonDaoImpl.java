package DB.netUser;

import java.util.List;

import static DB.CommonDao.*;

interface getUserJsonDao{
    List<netUser> getUserJson();
    List<netUser> getUserJsonByUno(String uno);
    List<netUser>getUserJsonByUserName(String uno);
    List<netUser>getUserJsonByUserJob(String job);
    List<netUser>getUserJsonByUserSex(String sex);
    int getUserSum();
    List<netUser>getUserJsonByPage( int pageNow,int pageSize);
}
public class getUserJsonDaoImpl implements getUserJsonDao {
    public List<netUser> getUserJson(){
        String sql = "Select * from netUser";
        return queryBeenList(sql,netUser.class);
    }
    public List<netUser> getUserJsonByUno(String uno){
        String sql = "Select * from netUser where  UNO=?";
        return  queryBeenList(sql,netUser.class,uno);
    }
    public List<netUser>getUserJsonByUserName(String name){
        String sql = "Select * from netUser where userName  like '%"+name+"%' ";
        return queryBeenList(sql,netUser.class);
    }
    public List<netUser>getUserJsonByUserJob(String job){
        String sql = "Select * from netUser where userJob=?";
        return queryBeenList(sql,netUser.class,job);
    }
    public List<netUser>getUserJsonByUserSex(String sex){
        String sql = "Select * from netUser where usex=?";
        return queryBeenList(sql,netUser.class,sex);
    }
    public List<netUser>getUserJsonByUserAge(String age){
        String sql = "Select * from netUser where uage=?";
        return queryBeenList(sql,netUser.class,age);
    }
    public int getUserSum(){
        String sql = "Select count(*)from netUser";
        return queryBeenLength(sql);
    }
    public  List<netUser>getUserJsonByPage( int pageNow,int pageSize){
        String sql = "select uno,userName,userPassword,userJob,uSex,uAge from( select rownum r,e.*from netUser e where rownum<= ? ) where r>?";
        return queryBeenListByPage(sql,netUser.class ,pageNow, pageSize);
    }
}
