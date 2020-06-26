package Service.bookService;

import DB.book.book;
import DB.book.bookSearchDaoImpl;


import java.util.List;

interface  bookSearchService{
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
public class bookSearchServiceImpl  implements  bookSearchService{

    List<book>bookList=null;
    book b;
    boolean aBoolean =true;
    public  List<book> getBookJson(){
        try{
            bookList = new bookSearchDaoImpl().getBookJson();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }

    public  book getBookJsonByBno(String bno){
        try{
            b = new bookSearchDaoImpl().getBookJsonByBno(bno);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return b;
    }

    public  List<book> getBookJsonByBName(String bname){
        try{
            System.out.println(bname);
            bookList = new bookSearchDaoImpl().getBookJsonByBName(bname);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }

    public  List<book> getBookJsonByBAuthor(String bauthor){
        try{
            bookList = new bookSearchDaoImpl().getBookJsonByBAuthor(bauthor);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }

    public  List<book> getBookJsonByBPrice(double bprice){
        try{
            bookList = new bookSearchDaoImpl().getBookJsonByBPrice(bprice);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }

    public List<book> getBookJsonByBPress(String bpress){
        try{
            bookList = new bookSearchDaoImpl().getBookJsonByBPress(bpress);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }

    public int getBookSum(){
        try{
           return new bookSearchDaoImpl().getBookSum();
        }catch (Exception ex){
            ex.printStackTrace();
            return 0 ;
        }
    }

    public    List<book> getBookJsonByPage(int pageNow, int pageSize){
        try{
            bookList = new  bookSearchDaoImpl ().getBookJsonByPage(pageNow,pageSize);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }

    public  String getBookUrl(String bno){
        String result ="";
        try{
           result = new  bookSearchDaoImpl().getBookUrl(bno);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }


}
