package DB.RBDao;


public class RB {
    private String uNo;
    private String bNo;
    private String  rbDate;

    public String getUNo() {
        return uNo;
    }

    public void setUNo(String uNo) {
        this.uNo = uNo;
    }

    public String getBNo() {
        return bNo;
    }

    public void setBNo(String bNo) {
        this.bNo = bNo;
    }

    public String  getRbDate() {
        return rbDate;
    }

    public void setRbDate(String  rbDate) {
        this.rbDate = rbDate;
    }

    public String toString(){
        return "编号为 "+uNo+" 借书号"+bNo+" at "+rbDate;
    }
}
