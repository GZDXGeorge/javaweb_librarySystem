package Service.bookService;

import DB.book.book;
import DB.book.bookAddDaoImpl;

interface  bookAddService{
    boolean doInsert(String bNo,String bName,String bAuthor,String bPress,double Price,String url);
    boolean doInsert(book b);
}
public class bookAddServiceImpl implements  bookAddService{
    bookAddDaoImpl bookAddDao = null;
    boolean aBoolean = true;
    public boolean doInsert(book b){
       try{
          bookAddDao = new bookAddDaoImpl();
          bookAddDao.doInsert(b);
       }catch (Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }
    public boolean doInsert(String bNo, String bName, String bAuthor, String bPress, double  bPrice,String url){
        try{
            bookAddDao = new bookAddDaoImpl();
            aBoolean =  bookAddDao.doInsert(bNo,bName,bAuthor,bPress,bPrice,url);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return aBoolean;
    }

}
