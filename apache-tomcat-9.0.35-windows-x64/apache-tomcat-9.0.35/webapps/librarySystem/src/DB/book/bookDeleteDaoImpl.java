package DB.book;

import DB.CommonDao;

interface  bookDeleteDao{
    public boolean doDelete(book b);
    public boolean doDelete(String bNo);
    public boolean  doDeleteAll_ByAuthor(String bAuthor);
    public boolean doDeleteAll_Series(String bName,String bAuthor);
    public boolean doDeleteAll_Series(String bName,String bAuthor,String bPress);
}
public class bookDeleteDaoImpl extends CommonDao implements  bookDeleteDao {
    public boolean doDelete(String bNo){
        String sql = "Delete from book where bNo =?";
        int i = executeUpdate(sql,bNo);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doDeleteAll_ByAuthor(String bAuthor){    //删除一个作者所有的产品
        String sql = "Delete from book where  bAuthor=?";
        int i = executeUpdate(sql,bAuthor);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doDelete(book b){
        return doDelete(b.getBNo());
    }
    public boolean doDeleteAll_Series(String bName,String bAuthor){    //删除作者一个的产品
        String sql = "Delete from book where bName=? and bAuthor=?";
        int i = executeUpdate(sql,bName,bAuthor);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doDeleteAll_Series(String bName,String bAuthor,String bPress){    //删除作者一个的产品
        String sql = "Delete from book where bName=? and bAuthor=? and bPress=?";
        int i = executeUpdate(sql,bName,bAuthor,bPress);
        if(i==1)
            return true;
        else
            return false;
    }
}
