package Service.bookService;

import DB.book.book;
import DB.book.bookUpdateDaoImpl;

interface  bookUpdateService{
    boolean doUpdate(String bNo,String newBNo);
    boolean doUpdate(String bNo,double newPrice);
    boolean doUpdate_byPic(String bNo,String url);
    boolean doUpdate( String bNo, String bName, String bAuthor, String bPress, double bPrice, String picutreUrl );
    boolean doUpdate(book b);
}
public class bookUpdateServiceImpl  implements  bookUpdateService{
    bookUpdateDaoImpl  bookUpdateDao =null;
    boolean aBoolean = true;
    public boolean doUpdate(String bNo,String newBNo){
        bookUpdateDao = new bookUpdateDaoImpl();
        try{
            aBoolean = bookUpdateDao.doUpdate(bNo,newBNo);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return aBoolean;
    }
    public boolean doUpdate(String bNo,double newPrice){
        bookUpdateDao = new bookUpdateDaoImpl();
        try{
            aBoolean =  bookUpdateDao.doUpdate(bNo,newPrice);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return  aBoolean;
    }
    public boolean doUpdate_byPic(String bNo,String url){
        bookUpdateDao = new bookUpdateDaoImpl();
        try{
            aBoolean =  bookUpdateDao.doUpdate_byPic(bNo,url);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return  aBoolean;
    }
    public boolean doUpdate( String bNo, String bName, String bAuthor, String bPress, double bPrice, String picutreUrl ){
        bookUpdateDao = new bookUpdateDaoImpl();
        try{
            aBoolean =  bookUpdateDao.doUpdate(bNo,bName, bAuthor, bPress, bPrice, picutreUrl);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return  aBoolean;
    }
    public boolean doUpdate(book b){
        bookUpdateDao = new bookUpdateDaoImpl();
        try{
            aBoolean =  bookUpdateDao.doUpdate(b);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return  aBoolean;
    }
}
