package Servlet.bookServlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@WebServlet(name = "UploadImageServlet")
public class UploadImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、判断请求是不是multipart请求
        if(!ServletFileUpload.isMultipartContent(request)){
            throw new RuntimeException("当前请求不支持文件上传");
        }
        System.out.println("开始上传文件");
        //2、创建FileItem工厂==>文件写入硬盘的作用
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //3、创建temp临时文件夹
            String tempPath ="D:\\tomcat\\apache-tomcat-9.0.35-windows-x64\\apache-tomcat-9.0.35\\webapps\\librarySystem\\web\\net\\temp";
            File tempFile = new File(tempPath);
            factory.setRepository(tempFile);
            //4、设置使用临时文件的边界值,大于该值，上传文件先保存在临时文件中，小于该值，则直接写入内存
            //单位是字节
            factory.setSizeThreshold(1024*1024*1);

            //5、创建文件上传核心组件
            // 调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");//可以解决文件名中文乱码
            upload.setFileSizeMax(1024*1024*2);

            String bNo="defaultBNo",bName="defaultBName";
            //6、解析请求
            List<FileItem>  items =upload.parseRequest(request);
            //7、遍历请求
            for(FileItem item:items){
                //普通表单项，上传名字，编号等普通信息的上i传
                if(item.isFormField()){
                    String fileName = item.getFieldName();// name属性值
                    String fileValue = item.getString("utf-8");// name对应的value值
                    System.out.println(fileName + " -- " + fileValue);
                    if(fileName.equalsIgnoreCase("BNo")){
                        bNo = fileValue;
                    }
                    if(fileName.equalsIgnoreCase("BName")){
                        bName = fileValue;
                    }
                 }
                else{//上传图片等
                    String fileName = item.getName();
                    System.out.println("上传文件名字:"+fileName);
                    String suffix = fileName.substring(fileName.lastIndexOf('.'));//获取文件类型
                    String newFileName = bNo+"_"+bName+suffix;
                    System.out.println(newFileName);
                    //获取输入流，其中有上传文件的内容
                    InputStream is = item.getInputStream();
                    //String path = this.getServletContext().getRealPath("/net/bookImage");//获得当前项目保存服务器地址，也就是web文件夹下
                    String path ="D:\\tomcat\\apache-tomcat-9.0.35-windows-x64\\apache-tomcat-9.0.35\\webapps\\librarySystem\\web\\net\\bookImage";
                    //文件夹内文件数目有上限，但是可以创建子目录
                        //获取当前系统时间
                        Calendar now = Calendar.getInstance();
                        int year = now.get(Calendar.YEAR);
                        int month = now.get(Calendar.MONTH)+1;
                        int day = now.get(Calendar.DAY_OF_MONTH);
                        path = path+"/"+year+"/"+month+"/"+day;
                        //若该目录不存在，直接创建新目录
                        File dirFile = new File(path);
                        if(!dirFile.exists()){
                            dirFile.mkdirs();
                        }
                    //创建目标文件，用来保存上传文件
                    File desFile = new File(path,newFileName);
                    //创建文件输出流
                    OutputStream os = new FileOutputStream(desFile);
                    //将输入流数据写入到输出流中
                    int len=-1;
                    byte[]buf = new byte[1024];
                    while((len=is.read(buf))!=-1){
                        os.write(buf,0,len);
                    }
                    //desFile.delete();//删除临时文件
                    os.close();//输出流
                    is.close();//输入流
                    //删除临时文件
                    item.delete();
                }
            }
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
