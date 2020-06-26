package DB.book;

import DB.CommonDao;

interface   bookAddDao{
    boolean doInsert(String bNo,String bName,String bAuthor,String bPress,double Price,String url);
    boolean doInsert(book b);
}

public class bookAddDaoImpl  extends CommonDao implements  bookAddDao{
    public boolean doInsert(book b){
        return doInsert(b.getBNo(),b.getBName(),b.getBAuthor(),b.getBPress(),b.getBPrice(),b.getPicutreUrl());
    }
    public boolean doInsert(String bNo, String bName, String bAuthor, String bPress, double  bPrice,String url){
        String sql = "Insert into book values(?,?,?,?,?,?)";
        int i= executeUpdate(sql,bNo,bName,bAuthor,bPress, bPrice,url);
        if(i==1)
            return true;
        else
            return false;
    }
}
