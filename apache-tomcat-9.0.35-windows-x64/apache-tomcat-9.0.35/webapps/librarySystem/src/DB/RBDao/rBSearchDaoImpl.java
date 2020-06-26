package DB.RBDao;

import DB.CommonDao;
import DB.book.book;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

interface rBSearchDao{
    RB search_byBNo(String BNo);
    List<RB> search_byUNo(String uNo);
    RB search_byUBNo(String uNo, String bNo);
    int getRBSum();
    int getRBSum(String uno);
    List<RB> getBookJson();
    List<RB>getRBJsonByPage(int pageNow, int pageSize);
    List<RB> search_byRBDate(String date);
    List<RB>getRBJsonByPage(int pageNow, int pageSize,String uno);
}
public class rBSearchDaoImpl  extends CommonDao implements  rBSearchDao {

    public RB search_byBNo(String bno) {
        RB rb = new RB();
        Connection conn = null;
        String sql = "select * from rb where bno = ?";
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, bno);
            ResultSet resultSet = preparedStatement.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (resultSet.next()) {
                String u = resultSet.getString(1);
                String b = resultSet.getString(2);
                Date time = resultSet.getDate(3);
                String rbDate = sdf.format(time);
                rb.setBNo(b);
                rb.setUNo(u);
                rb.setRbDate(rbDate);
            } else {
                rb = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(preparedStatement, conn);
        }
        return rb;
    }

    public List<RB> search_byUNo(String uno) {
        List<RB> lst = new ArrayList<>();
        Connection conn = null;
        String sql = "select * from rb where uno = ?";
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, uno);
            ResultSet resultSet = preparedStatement.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            RB rb = null;
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    rb = new RB();
                    String u = resultSet.getString(1);
                    String b = resultSet.getString(2);
                    Date time = resultSet.getDate(3);
                    String rbDate = sdf.format(time);
                    rb.setBNo(b);
                    rb.setUNo(u);
                    rb.setRbDate(rbDate);
                    lst.add(rb);
                }
            } else {
                lst = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(preparedStatement, conn);
        }
        return lst;
    }

    public RB search_byUBNo(String uNo, String bNo) {
        RB rb = new RB();
        Connection conn = null;
        String sql = "select * from rb where bno = ? and uNo =?";
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, bNo);
            preparedStatement.setString(2, uNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (resultSet.isBeforeFirst()) {
                String u = resultSet.getString(1);
                String b = resultSet.getString(2);
                Date time = resultSet.getDate(3);
                String rbDate = sdf.format(time);
                rb.setBNo(b);
                rb.setUNo(u);
                rb.setRbDate(rbDate);
            } else {
                rb = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(preparedStatement, conn);
        }
        return rb;
    }

    public List<RB> getBookJson() {
        List<RB> lst = new ArrayList<>();
        Connection conn = null;
        String sql = "select * from rb";
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            RB rb = null;
            if (resultSet.isBeforeFirst()) {
                //当 ResultSet 为非空时，其游标指向第一条记录前面，若为空时由于不存在第一条记录，所以这时候游标也无法向指第一条记录前面
                while (resultSet.next()) {
                    rb = new RB();
                    String u = resultSet.getString(1);
                    String b = resultSet.getString(2);
                    Date time = resultSet.getDate(3);
                    String rbDate = sdf.format(time);
                    rb.setBNo(b);
                    rb.setUNo(u);
                    rb.setRbDate(rbDate);
                    lst.add(rb);
                }
            } else {
                lst = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(preparedStatement, conn);
        }
        return lst;
    }

    public int getRBSum() {
        String sql = "Select count(*)from rb";
        return queryBeenLength(sql);
    }

    public int getRBSum(String uno) {
        String sql = "Select count(*)from rb where rb.uno = '"+uno+"'";
        return queryBeenLength(sql);
    }

    public List<RB> getRBJsonByPage(int pageNow, int pageSize) {
        List<RB> lst = new ArrayList<>();
        Connection conn = null;
        String sql = "select UNO,BNO,rbDate from( select rownum r,e.*from rb e where rownum<= ? ) where r>?";
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, pageNow * pageSize);
            preparedStatement.setInt(2, pageNow * pageSize - pageSize);

            ResultSet resultSet = preparedStatement.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            RB rb = null;
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    rb = new RB();
                    String u = resultSet.getString(1);
                    String b = resultSet.getString(2);
                    Date time = resultSet.getDate(3);
                    String rbDate = sdf.format(time);
                    rb.setBNo(b);
                    rb.setUNo(u);
                    rb.setRbDate(rbDate);
                    lst.add(rb);
                }
            } else {
                lst = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(preparedStatement, conn);
        }
        return lst;
    }

    public List<RB> search_byRBDate(String date) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<RB> lst =null;
        ResultSet resultSet = null;
        RB rb = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1 = format.parse(date);
            System.out.println(date1);
            java.sql.Date date2 = new java.sql.Date(date1.getTime());
            System.out.println(date2);

            String sql = "select * from rb where rbdate = ?";

            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setDate(1, date2);
            resultSet = preparedStatement.executeQuery();
            lst = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    rb = new RB();
                    String u = resultSet.getString(1);
                    String b = resultSet.getString(2);
                    Date time = resultSet.getDate(3);
                    String rbDate = sdf.format(time);
                    rb.setBNo(b);
                    rb.setUNo(u);
                    rb.setRbDate(rbDate);
                    lst.add(rb);
                }
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(preparedStatement, conn);
        }
        return lst;
    }

    public List<RB> getRBJsonByPage(int pageNow, int pageSize,String uno) {
        List<RB> lst = new ArrayList<>();
        Connection conn = null;
        String sql = "select UNO,BNO,rbDate from( select rownum r,e.*from rb e where rownum<= ? and  uno =?) where r>? ";
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, pageNow * pageSize);
            preparedStatement.setInt(3, pageNow * pageSize - pageSize);
            preparedStatement.setString(2,uno);

            ResultSet resultSet = preparedStatement.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            RB rb = null;
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    rb = new RB();
                    String u = resultSet.getString(1);
                    String b = resultSet.getString(2);
                    Date time = resultSet.getDate(3);
                    String rbDate = sdf.format(time);
                    rb.setBNo(b);
                    rb.setUNo(u);
                    rb.setRbDate(rbDate);
                    lst.add(rb);
                }
            } else {
                lst = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(preparedStatement, conn);
        }
        return lst;
    }
}
