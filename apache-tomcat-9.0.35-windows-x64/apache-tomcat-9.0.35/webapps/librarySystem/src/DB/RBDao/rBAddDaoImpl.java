package DB.RBDao;

import DB.CommonDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

interface  rBAddDao{
    boolean doInsert(RB rb);
    boolean doInsert(String uNo,String bNo);//默认时间
    boolean doInsert(String uNo,String bNo,String rbDate);
}
public class rBAddDaoImpl extends CommonDao implements  rBAddDao {
    public boolean doInsert(RB rb){
        if(rb.getRbDate()==" "||rb.getRbDate()==null||rb.getRbDate()=="")
         return  doInsert(rb.getUNo(),rb.getBNo());
        else
            return  doInsert(rb.getUNo(),rb.getBNo(),rb.getRbDate());
    }
    public boolean doInsert(String uno,String bno,String rbDate){
         RB rb=new rBSearchDaoImpl().search_byBNo(bno);
         boolean b = false;
         if(rb!=null){
             return b;
         }else{
             Connection conn =null;
             PreparedStatement preparedStatement =null;
             int success=0;
             try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date1 = format.parse(rbDate);
                System.out.println(date1);
                java.sql.Date date2 = new java.sql.Date(date1.getTime());
                System.out.println(date2);

                String sql="insert into rb  values(?,?,?)";
                //获取连接
                conn = getConnection();
                //创建语句对象
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,uno);
                preparedStatement.setString(2,bno);
                preparedStatement.setDate(3,date2);

                System.out.println(preparedStatement.execute());
                success = preparedStatement.getUpdateCount();//返回的是更新的记数
                System.out.println("success="+success);
                /*
                如果第一个结果是 ResultSet 对象，则返回 true；如果第一个结果是更新计数或者没有结果，则返回 false
                意思就是如果是查询的话返回true，如果是更新或插入的话就返回false了；
                 */
            }
             catch (Exception e){
                e.printStackTrace();
            } finally {
                //释放资源
                close(preparedStatement,conn);
                if(success==0)
                    return false;
                else
                    return true;
            }
         }
    }
    public boolean doInsert(String uNo,String bNo){
        RB rb=new rBSearchDaoImpl().search_byBNo(bNo);
        if(rb!=null){
            return false;
        }else{
            String sql = "Insert into rb(uno,bno) values(?,?)";
            int i= executeUpdate(sql,uNo,bNo);
            if(i==1)
                return true;
            else
                return false;
        }
    }
}
