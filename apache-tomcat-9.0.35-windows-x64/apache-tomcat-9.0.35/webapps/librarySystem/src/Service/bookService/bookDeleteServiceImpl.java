package Service.bookService;

import DB.book.book;
import DB.book.bookDeleteDaoImpl;

interface  bookDeleteService{
    public boolean doDelete(book b);
    public boolean doDelete(String bNo);
    public boolean  doDeleteAll_ByAuthor(String bAuthor);
    public boolean doDeleteAll_Series(String bName,String bAuthor);
    public boolean doDeleteAll_Series(String bName,String bAuthor,String bPress);
}

public class bookDeleteServiceImpl  implements bookDeleteService{
    bookDeleteDaoImpl bookDeleteDao = null;
    public boolean doDelete(book b){
        bookDeleteDao = new bookDeleteDaoImpl();
        try {
            bookDeleteDao.doDelete(b);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean doDelete(String bNo){
        bookDeleteDao = new bookDeleteDaoImpl();
        try {
            bookDeleteDao.doDelete(bNo);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean doDeleteAll_Series(String bName,String bAuthor){
        bookDeleteDao = new bookDeleteDaoImpl();
        try {
            bookDeleteDao.doDeleteAll_Series(bName,bAuthor);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean  doDeleteAll_ByAuthor(String bAuthor){
        bookDeleteDao = new bookDeleteDaoImpl();
        try {
            bookDeleteDao.doDeleteAll_ByAuthor(bAuthor);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean doDeleteAll_Series(String bName,String bAuthor,String bPress){
        bookDeleteDao = new bookDeleteDaoImpl();
        try {
            bookDeleteDao.doDeleteAll_Series(bName,bAuthor,bPress);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
