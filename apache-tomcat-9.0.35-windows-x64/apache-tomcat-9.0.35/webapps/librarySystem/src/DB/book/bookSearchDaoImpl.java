package DB.book;

import DB.CommonDao;


import java.util.List;

interface  bookSearchDao{
    List<book> getBookJson();
    book getBookJsonByBno(String bno);
    List<book>getBookJsonByBName(String bname);
    List<book> getBookJsonByBAuthor(String bauthor);
    List<book>getBookJsonByBPrice(double bprice);
    List<book> getBookJsonByBPress(String bpress);
    int getBookSum();
    List<book>getBookJsonByPage(int pageNow, int pageSize);
    String getBookUrl(String bno);
}
public class bookSearchDaoImpl  extends CommonDao implements  bookSearchDao {
    public  List<book> getBookJson(){
        String sql = "select * from book";
        return queryBeenList(sql, book.class);
    }
    public book getBookJsonByBno(String bno){
        String sql = "select * from book where bno=?";
        return queryBeen(sql, book.class,bno);
    }
    public  List<book>getBookJsonByBName(String bname){
        String sql = "select * from book where bname like '%"+bname+"%' ";
        System.out.println(sql);
        return queryBeenList(sql, book.class);
    }
    public List<book> getBookJsonByBAuthor(String bauthor){
        String sql = "select * from book where BAUTHOR like '%"+bauthor+"%' ";
        System.out.println(sql);
        return queryBeenList(sql, book.class);
    }
    public  List<book>getBookJsonByBPrice(double bprice){
        String sql = "select * from book where bprice=?";
        return queryBeenList(sql, book.class,bprice);
    }
    public  List<book> getBookJsonByBPress(String bpress){
        String sql = "select * from book where bpress=?";
        return queryBeenList(sql, book.class,bpress);
    }
    public int  getBookSum(){
        String sql = "Select count(*)from book";
        return queryBeenLength(sql);
    }
    public   List<book> getBookJsonByPage(int pageNow, int pageSize){
        String sql = "select bno,bname,bauthor,bprice,bpress,picutreUrl from( select rownum r,e.*from book e where rownum<= ? ) where r>?";
        return queryBeenListByPage(sql, book.class ,pageNow, pageSize);
    }
    public String getBookUrl(String bno){
        String sql = "select picutreUrl from book where bno=?";
        return queryBeenByStringValue(sql,"picutreUrl",bno);
    }
}

