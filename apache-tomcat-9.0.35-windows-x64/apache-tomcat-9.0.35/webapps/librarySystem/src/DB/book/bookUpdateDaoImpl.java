package DB.book;

import DB.CommonDao;

interface bookUpdateDao{
    public boolean doUpdate(String bNo,String newBNo);
    public boolean doUpdate(String bNo,double newPrice);
    public boolean doUpdate_byPic(String bNo,String url);
    public boolean doUpdate( String bNo, String bName, String bAuthor, String bPress, double bPrice, String picutreUrl );
    public boolean doUpdate(book b);
}
public class bookUpdateDaoImpl extends CommonDao implements bookUpdateDao{
    public  boolean doUpdate(String bNo,String newBno){     //更换书的编号
        String sql = "Update  book set bno=? where bno =?";
        int i = executeUpdate(sql,newBno,bNo);
        if(i==1)
            return true;
        else
            return false;
    }
    public  boolean doUpdate(String bNo,double newPrice){     //更换书的编号
        String sql = "Update  book set bPrice=? where bno =?";
        int i = executeUpdate(sql,newPrice,bNo);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doUpdate_byPic(String bNo,String url){
        String sql = "Update  book set  picutreUrl=? where bno =?";
        int i = executeUpdate(sql,url,bNo);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doUpdate( String bNo, String bName, String bAuthor, String bPress, double bPrice, String picutreUrl ){
        String sql = "Update  book set bName=?, bAuthor=?,  bPress=?, bPrice=?, picutreUrl=? where bno =?";
        int i = executeUpdate(sql, bName, bAuthor,  bPress, bPrice, picutreUrl,bNo);
        if(i==1)
            return true;
        else
            return false;
    }
    public boolean doUpdate(book b){
        return doUpdate(b.getBNo(),b.getBName(),b.getBAuthor(),b.getBPress(),b.getBPrice(),b.getPicutreUrl());
    }
}

