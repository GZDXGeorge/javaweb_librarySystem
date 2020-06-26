package verifyCodeService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class verifyCodePhotoShow {
    public void fun1() throws FileNotFoundException, IOException {
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.output(verifyCode.getImage(), new FileOutputStream("D:\\tomcat\\apache-tomcat-9.0.35-windows-x64\\apache-tomcat-9.0.35\\webapps\\librarySystem\\web\\net\\verifyCodePicture\\test.jpg"));
        String str = verifyCode.getText();
        System.out.println(str);
    }
    public static void main(String args[]) throws IOException {
       new verifyCodePhotoShow().fun1();
    }
}
