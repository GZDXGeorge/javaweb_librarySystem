package DB.netUser;

public class netUser {
    private String uNo;
    private String userName;
    private String userPassword;
    private String userJob;
    private String usex;
    private int uage;

    public String getUNo() {
        return uNo;
    }

    public void setUNo(String uNo) {
        this.uNo = uNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public int getUage() {
        return uage;
    }

    public void setUage(int uage) {
        this.uage = uage;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    public String toString(){
        return "编号"+uNo+" 用户名"+userName+" 密码"+userPassword+" 性别"+usex+" 年龄"+uage+" 用户身份"+userJob;
    }

}
