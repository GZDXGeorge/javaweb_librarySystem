package DB;

public class myRBClass {
    private String  rbDate;
    private String bNo;
    private String bName;
    private String bAuthor;
    private String picutreUrl;
    public myRBClass(String rbDate,String bNo,String bName,String bAuthor,String picutreUrl){
        this.rbDate = rbDate;
        this.bNo = bNo;
        this.bName = bName;
        this.bAuthor = bAuthor;
        this.picutreUrl = picutreUrl;
    }
    public String getRbDate() {
        return rbDate;
    }

    public void setRbDate(String rbDate) {
        this.rbDate = rbDate;
    }

    public String getbNo() {
        return bNo;
    }

    public void setbNo(String bNo) {
        this.bNo = bNo;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }

    public String getPicutreUrl() {
        return picutreUrl;
    }

    public void setPicutreUrl(String picutreUrl) {
        this.picutreUrl = picutreUrl;
    }
}
