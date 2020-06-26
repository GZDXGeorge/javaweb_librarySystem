package Servlet.bookJsonServlet;

import DB.book.book;

import Service.bookService.bookAddServiceImpl;
import Service.bookService.bookDeleteServiceImpl;
import Service.bookService.bookSearchServiceImpl;

import Service.bookService.bookUpdateServiceImpl;

import net.sf.json.JSONObject;
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
import java.util.*;

@WebServlet(name = "bookJsonServlet")
public class bookJsonServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");
        if(task!=null)
            System.out.println("service task="+task);
         else
            System.out.println("service task="+task);
        if(task!=null){
            switch (task){
                case "insert":insert(request,response);break;
                case "queryAll":queryAll(request,response);break;
                case "queryByPage":queryByPage(request,response);break;
                case "delete":delete(request,response);break;
                case "update":update(request,response);break;
                case "queryByBNo":queryByBNo(request,response);break;
                case "queryByBName":queryByBName(request,response);break;
                case "queryByBPress":queryByBPress(request,response);break;
                case "queryByBAuthor": queryByBAuthor(request,response);break;
                case "queryByBPrice":queryByBPrice(request,response);break;
                default:break;
            }
        }
    }
    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            // upload.setFileSizeMax(1024*1024*2);

            String bNo="defaultBNo",bName="defaultBName",  bAuthor="",bPress="";
            double bPrice = 0.0;
            String picutreUrl = "";

            //6、解析请求
            List<FileItem> items =upload.parseRequest(request);
            book b = new book();
            book tmpBook =null;

            Map<String, Object> map = new HashMap<>();
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            JSONObject json=null;

            System.out.println("提交的表单项目数量："+items.size());

            //7、遍历请求
            for(FileItem item:items){
                //普通表单项，上传名字，编号等普通信息的上i传
                if(item.isFormField()){
                    String fileName = item.getFieldName();// name属性值
                    String fileValue = item.getString("utf-8");// name对应的value值
                    System.out.println(fileName + " -- " + fileValue);
                    if(fileName.equalsIgnoreCase("BNo")){
                        bNo = fileValue;
                        tmpBook = new bookSearchServiceImpl().getBookJsonByBno( bNo);
                        if(tmpBook!=null){
                            map.put( "success",false);
                            map.put( "message", "Exist");
                            json = JSONObject.fromObject(map);
                            response.setCharacterEncoding("utf-8");
                            out = response.getWriter();
                            out.append(json.toString());
                            out.flush();
                            out.close();
                            System.out.println("图书编号已经存在图书馆记录");
                            return;
                        }
                        b.setBNo(bNo);
                    }
                    else if(fileName.equalsIgnoreCase("BName")){
                        bName = fileValue;
                        b.setBName(bName);
                    }
                    else if(fileName.equalsIgnoreCase("BAuthor")){
                        bAuthor = fileValue;
                        b.setBAuthor(bAuthor);
                    }
                    else if(fileName.equalsIgnoreCase("BPress")){
                        bPress = fileValue;
                        b.setBPress(bPress);
                    }
                    else if(fileName.equalsIgnoreCase("BPrice")){
                        bPrice  = Double.valueOf(fileValue);//强制转换成double类型
                        b.setBPrice(bPrice);
                    }
                }
                else{//上传图片等
                    String fileName = item.getName();
                    System.out.println("上传文件名字:"+fileName);
                    String suffix = fileName.substring(fileName.lastIndexOf('.'));//获取文件类型
                    String newFileName = bNo+"_"+bName+suffix;
                    //获取输入流，其中有上传文件的内容
                    InputStream is = item.getInputStream();
                    String path ="D:/tomcat/apache-tomcat-9.0.35-windows-x64/apache-tomcat-9.0.35/webapps/librarySystem/web/net/bookImage";
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
                    os.close();//输出流
                    is.close();//输入流
                    //删除临时文件
                    item.delete();
                    picutreUrl =  path.substring(path.lastIndexOf("net/"));
                    picutreUrl = picutreUrl+"/"+newFileName;//用的时候加上"http://localhost:8888/hello/jsp/" +
                    b.setPicutreUrl(picutreUrl);
                    System.out.println("新增的书籍信息："+b);

                    boolean doInsertOk = new bookAddServiceImpl().doInsert(b);
                    if(doInsertOk==true) {
                        map.put( "success",true);
                        map.put( "message", "ok");
                        System.out.println("增加图书成功");
                    }
                    else{
                        map.put( "success",false);
                        map.put( "message", "Error");
                        System.out.println("增加图书失败");
                    }
                    json = JSONObject.fromObject(map);
                    response.setCharacterEncoding("utf-8");
                    out = response.getWriter();
                    out.append(json.toString());
                    out.flush();
                    out.close();

                }
            }

        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
    private void queryAll(HttpServletRequest Request, HttpServletResponse response){
        try {
            List<book> lst = new bookSearchServiceImpl().getBookJson();
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("书籍全部传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void queryByPage(HttpServletRequest Request, HttpServletResponse response){
        try {
            Request.setCharacterEncoding("UTF-8");
            int pageNow, pageSize;
            pageNow  = Integer.parseInt(Request.getParameter("page"));     //第几页的数据
            pageSize = Integer.parseInt(Request.getParameter("rows"));  //每页多少条数据
            List<book> lst = new bookSearchServiceImpl().getBookJsonByPage( pageNow,pageSize);
            int total =new bookSearchServiceImpl().getBookSum();
            Map<String, Object> map = new HashMap<>();
            map.put("total", total);
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("分页传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response){
      try{
          request.setCharacterEncoding("UTF-8");
          String bNo = request.getParameter("BNo");
          String url ="D:/tomcat/apache-tomcat-9.0.35-windows-x64/apache-tomcat-9.0.35/webapps/librarySystem/web/" + new bookSearchServiceImpl().getBookUrl(bNo);
          System.out.println(url);

          System.out.println(new bookDeleteServiceImpl().doDelete(bNo));
          PrintWriter out = response.getWriter();
          out.append("del-ok");
          out.flush();
          out.close();
          System.out.println("删值");

          File file = new File(url);
          if(file.exists()){
              file.delete();//小心使用
          }

      }catch(Exception ex){
          ex.printStackTrace();
      }
    }
    private void update(HttpServletRequest request, HttpServletResponse response){
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
            // upload.setFileSizeMax(1024*1024*2);
            String bNo="defaultBNo",bName="defaultBName",  bAuthor="",bPress="";
            double bPrice = 0.0;
            String picutreUrl = "";
            //6、解析请求
            List<FileItem> items =upload.parseRequest(request);
            book b = new book();
            Map<String, Object> map = new HashMap<>();
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            JSONObject json=null;
            boolean changeUrl =true;

            //7、遍历请求
            for(FileItem item:items){
                //普通表单项，上传名字，编号等普通信息的上i传
                if(item.isFormField()){
                    String fileName = item.getFieldName();// name属性值
                    String fileValue = item.getString("utf-8");// name对应的value值
                    System.out.println(fileName + " -- " + fileValue);
                    if(fileName.equalsIgnoreCase("BNo")){
                        bNo = fileValue;
                        b.setBNo(bNo);
                    }
                    else if(fileName.equalsIgnoreCase("BName")){
                        bName = fileValue;
                        b.setBName(bName);
                    }
                    else if(fileName.equalsIgnoreCase("BAuthor")){
                        bAuthor = fileValue;
                        b.setBAuthor(bAuthor);
                    }
                    else if(fileName.equalsIgnoreCase("BPress")){
                        bPress = fileValue;
                        b.setBPress(bPress);
                    }
                    else if(fileName.equalsIgnoreCase("BPrice")){
                        bPrice  = Double.valueOf(fileValue);//强制转换成double类型
                        b.setBPrice(bPrice);
                    }
                }
                else{//上传图片等
                    String oldUrl = "D:/tomcat/apache-tomcat-9.0.35-windows-x64/apache-tomcat-9.0.35/webapps/librarySystem/web/"+new bookSearchServiceImpl().getBookUrl(bNo);
                    String oldFileName = oldUrl.substring(oldUrl.lastIndexOf("/")+1);
                    String newFileName="";
                    String fileName = item.getName();

                    if(fileName==""||fileName==null) {
                        fileName = oldFileName;
                        changeUrl = false;
                    }

                    System.out.println("上传文件名字:" + fileName);
                    String suffix = fileName.substring(fileName.lastIndexOf('.'));//获取文件类型
                    newFileName = bNo + "_" + bName + suffix;


                    String path ="D:/tomcat/apache-tomcat-9.0.35-windows-x64/apache-tomcat-9.0.35/webapps/librarySystem/web/net/bookImage";
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
                    picutreUrl =  path.substring(path.lastIndexOf("net/"));// 例如：net/bookImage/2020/6/22/
                    picutreUrl = picutreUrl+"/"+newFileName;//例如：net/bookImage/2020/6/22/0004_4.jpg；用的时候加上"http://localhost:8888/hello/jsp/" +
                    b.setPicutreUrl(picutreUrl);
                    System.out.println("修改书籍信息："+b);

                    boolean doInsertOk = new bookUpdateServiceImpl().doUpdate(b);
                    if(doInsertOk) {
                        map.put( "success",true);
                        map.put( "message", "ok");
                        System.out.println("修改图书成功");
                        //需要修改图片
                        //创建目标文件，用来保存上传文件
                        File desFile  = new File(path, newFileName);
                        File oldFile = new File(oldUrl);
                        //复制图片
                        if(changeUrl) {
                            if(oldFile.exists()){
                                oldFile.delete();//小心使用
                            }
                            //获取输入流，其中有上传文件的内容
                            InputStream is = item.getInputStream();
                            //创建文件输出流
                            OutputStream os = new FileOutputStream(desFile);
                            //将输入流数据写入到输出流中
                            int len = -1;
                            byte[] buf = new byte[1024];
                            while ((len = is.read(buf)) != -1) {
                                os.write(buf, 0, len);
                            }
                            os.close();//输出流
                            is.close();//输入流
                            //删除临时文件
                            item.delete();
                        }
                        else{
                            FileInputStream inFile = new FileInputStream(oldFile);
                            BufferedInputStream inB = new BufferedInputStream(inFile);

                            FileOutputStream outFile = new FileOutputStream(desFile);
                            BufferedOutputStream outB = new BufferedOutputStream(outFile);

                            byte ByteArray[] = new byte[(int) oldFile.length()];

                            while (inB.read(ByteArray) != -1) {
                                outB.write(ByteArray);
                            }
                            outB.flush();
                            inB.close();
                            outB.close();
                            oldFile.delete();//小心使用
                        }
                    }
                    else{
                        map.put( "success",false);
                        map.put( "message", "Error");
                        System.out.println("修改图书失败");
                    }

                    json = JSONObject.fromObject(map);
                    response.setCharacterEncoding("utf-8");
                    out = response.getWriter();
                    out.append(json.toString());
                    out.flush();
                    out.close();
                }
            }
        }
        catch (FileUploadException | IOException e) {
            e.printStackTrace();
        }
    }
    private void queryByBNo(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String bNo = request.getParameter("bNo");
            List<book> lst = new ArrayList<>();
            lst.add(new bookSearchServiceImpl ().getBookJsonByBno(bNo));
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("bNo传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void queryByBName(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String bName =request.getParameter("bName");
            System.out.println(bName);
            List<book> lst = new bookSearchServiceImpl ().getBookJsonByBName(bName);
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            for(int i=0;i<lst.size();i++)
                System.out.println(lst.get(i));
            System.out.println("bName传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void queryByBAuthor(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String bAuthor = request.getParameter("bAuthor");
            List<book> lst = new bookSearchServiceImpl ().getBookJsonByBAuthor(bAuthor);
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            for(int i=0;i<lst.size();i++)
                System.out.println(lst.get(i));
            System.out.println("bAuthor传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void queryByBPress(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String bPress = request.getParameter("bPress");
            List<book> lst = new bookSearchServiceImpl ().getBookJsonByBPress(bPress);
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            for(int i=0;i<lst.size();i++)
                System.out.println(lst.get(i));
            System.out.println("bPress传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void queryByBPrice(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String bPrice = request.getParameter("bPrice");
            double tmp = Double.parseDouble(bPrice);
            List<book> lst = new bookSearchServiceImpl ().getBookJsonByBPrice(tmp);
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            for(int i=0;i<lst.size();i++)
                System.out.println(lst.get(i));
            System.out.println("bPrice传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
