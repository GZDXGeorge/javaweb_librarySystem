package Service.RBService;

import DB.RBDao.RB;
import DB.RBDao.rBSearchDaoImpl;
import DB.book.bookSearchDaoImpl;

import java.util.List;

interface  rBsearchService{
    RB search_byBNo(String BNo);
    List<RB> search_byUNo(String uNo);
    RB search_byUBNo(String uNo, String bNo);
    List<RB>getRBJson();
    int getRBSum();
    List<RB> search_byRBDate(String date);
    List<RB>getRBJsonByPage(int pageNow, int pageSize);
    List<RB>getRBJsonByPage(int pageNow, int pageSize,String uno);
}
public class rBSearchServiceImpl  implements  rBsearchService{
    RB rb = null;
    List<RB>rbs = null;
    public  int getRBSum(){
        try{
            return new  rBSearchDaoImpl().getRBSum();
        }catch (Exception ex){
            ex.printStackTrace();
            return 0 ;
        }
    }
    public RB search_byBNo(String bNo){
        try {
            System.out.println("bNo="+bNo);
            rb = new  rBSearchDaoImpl().search_byBNo(bNo);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return rb;
    }
    public List<RB> search_byUNo(String uNo){
        try {
            rbs = new rBSearchDaoImpl().search_byUNo(uNo);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rbs;
    }
    public RB search_byUBNo(String uNo, String bNo){
        try {
            rb = new  rBSearchDaoImpl().search_byUBNo(uNo,bNo);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rb;
    }
    public List<RB>getRBJson(){
        try {
            rbs = new rBSearchDaoImpl().getBookJson();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rbs;
    }
    public List<RB>getRBJsonByPage(int pageNow, int pageSize){
        try {
            rbs = new rBSearchDaoImpl().getRBJsonByPage( pageNow,pageSize);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rbs;
    }
    public  List<RB> search_byRBDate(String date){
        try {
            System.out.println(date);
            rbs = new rBSearchDaoImpl().search_byRBDate(date);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rbs;
    }
    public List<RB>getRBJsonByPage(int pageNow, int pageSize,String uno){
        try {
            rbs = new rBSearchDaoImpl().getRBJsonByPage( pageNow,pageSize,uno);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rbs;
    }
}
