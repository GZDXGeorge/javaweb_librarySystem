package DB.book;

public class book {
    private String bNo;
    private String bName;
    private String bAuthor;
    private String bPress;
    private double bPrice;
    private String picutreUrl;

    public String getBNo() {
        return bNo;
    }

    public void setBNo(String bno) {
        this.bNo = bno;
    }

    public String getBName() {
        return bName;
    }

    public void setBName(String bName) {
        this.bName = bName;
    }

    public String getBAuthor() {
        return bAuthor;
    }

    public void setBAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }

    public String getBPress() {
        return bPress;
    }

    public void setBPress(String bPress) {
        this.bPress = bPress;
    }

    public double getBPrice() {
        return bPrice;
    }

    public void setBPrice(double bPrice) {
        this.bPrice = bPrice;
    }

    public String toString(){
        return "书编号 "+bNo+" 书名 "+bName+" 作者 "+bAuthor+" 出版社 "+bPress+" 价格 "+bPrice+" 保存路径 "+picutreUrl;
    }

    public String getPicutreUrl() {
        return picutreUrl;
    }

    public void setPicutreUrl(String picutreUrl) {
        this.picutreUrl = picutreUrl;
    }
}
