package DB;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

/**
 * 通用的DAO，可以对任意表做增删改
 */
public     class CommonDao extends JDBCUTILS {
    /**
     * 对任意表做增删改,根据增删改的字符串或数字不同操作
     * @return
     */

    public static int executeUpdate(String sql,Object ...params){
        Connection conn =null;
        PreparedStatement preparedStatement =null;
        int i=0;
        try {
            //获取连接
            conn = getConnection();
            //创建语句对象
            preparedStatement = conn.prepareStatement(sql);
            if(params!=null && params.length>0){//判断需要占位符
                for(int j=0;j<params.length;j++){
                    //设置占位符
                    preparedStatement.setObject(j+1,params[j]);
                }
            }
            i = preparedStatement.executeUpdate();//成功为1
        }catch(SQLException e){
            e.printStackTrace();
        }finally {//释放资源
            close(preparedStatement,conn);
        }
        return i;
    }

    public static <T> List<T> queryBeenList(String sql,Class<T>classz ,Object ...paramas){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<T>lst =new ArrayList<>();
        T tmp =null;
        try{
            //获取连接
            connection = getConnection();
            //创建语句对象
            preparedStatement = connection.prepareStatement(sql);
            if(paramas!=null&&paramas.length>0){//需要设置占位符
                for(int i=0;i<paramas.length;i++){
                    preparedStatement.setObject(i+1,paramas[i]);
                }
            }
            //执行SQL语句
            resultSet = preparedStatement.executeQuery();
            //处理结果
            //获取所有的属性
            Field[]fields=classz.getDeclaredFields();
            //获取元数据
            ResultSetMetaData  metaData = resultSet.getMetaData();
            //获取共多少行
            int columnCount = metaData.getColumnCount();
            //准备字符串数组，用来保存字段名
            String []columnNames =new String[columnCount];
            for(int i=0;i<columnCount;i++){         //从数据库中获取变量名
                columnNames[i] = metaData.getColumnName(i+1);
            }
            while(resultSet.next()){            //行数据
                tmp = classz.newInstance();
                for(Field  f:fields){           //从类的属性名
                    String name = f.getName();
                    Class<?>type =f.getType();
                    //set属性
                    String smn = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
                    // 根据方法名找到方法类型
                    Method sm = classz.getMethod(smn, f.getType());
                    for(String columnName :columnNames){       //从数据库获取的变量名
                        if(columnName.equalsIgnoreCase(name) ) {        //忽略大小写匹配，我的数据库变量名和对象的变量名一样，只是大小写区别
                            Object value = resultSet.getObject(columnName);
                            if(value==null)
                                continue;
                            if(value instanceof BigDecimal && type ==int.class){
                                value = ((BigDecimal)value).intValue();
                            }
                            if(value instanceof BigDecimal && type ==double.class){
                                value = ((BigDecimal)value).doubleValue();
                            }
                            sm.invoke(tmp,value);
                        }
                    }
                }
                lst.add(tmp);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close(resultSet,preparedStatement,connection);
        }
        return lst;
    }

    public static <T> T  queryBeen(String sql,Class<T>classz,Object ...params){
        List<T>lst= queryBeenList(sql,classz,params);
        if(lst.size()>0){
            return lst.get(0);
        }
        return null;
    }

    public static int  queryBeenLength(String sql){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        int num = 0;
        try{
            //获取连接
            connection = getConnection();
            //创建语句对象
            preparedStatement = connection.prepareStatement(sql);
            //执行SQL语句
            resultSet = preparedStatement.executeQuery();
            //处理结果
            if(resultSet.next()){
                num = resultSet.getInt(1);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(resultSet,preparedStatement,connection);
        }
        return num;
    }

    public static <T> List<T> queryBeenListByPage(String sql,Class<T>classz ,int pageNow,int pageSize){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<T>lst =new ArrayList<>();
        T tmp =null;
        try{
            //获取连接
            connection = getConnection();
            //创建语句对象
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,pageNow*pageSize);           //终止点
            preparedStatement.setObject(2, pageNow*pageSize-pageSize);//起始点-1
            //执行SQL语句
            resultSet = preparedStatement.executeQuery();
            //处理结果
            //获取所有的属性
            Field[]fields=classz.getDeclaredFields();
            //获取元数据
            ResultSetMetaData  metaData = resultSet.getMetaData();
            //获取共多少行
            int columnCount = metaData.getColumnCount();
            //准备字符串数组，用来保存字段名
            String []columnNames =new String[columnCount];
            for(int i=0;i<columnCount;i++){         //从数据库中获取变量名
                columnNames[i] = metaData.getColumnName(i+1);
            }
            while(resultSet.next()){            //行数据
                tmp = classz.newInstance();
                for(Field  f:fields){           //从类的属性名
                    String name = f.getName();
                    Class<?>type =f.getType();
                    //set属性
                    String smn = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
                    // 根据方法名找到方法类型
                    Method sm = classz.getMethod(smn, f.getType());
                    for(String columnName :columnNames){       //从数据库获取的变量名
                        if(columnName.equalsIgnoreCase(name) ) {        //忽略大小写匹配，我的数据库变量名和对象的变量名一样，只是大小写区别
                            Object value = resultSet.getObject(columnName);
                            if(value==null)
                                continue;
                            if(value instanceof BigDecimal && type ==int.class){
                                value = ((BigDecimal)value).intValue();
                            }
                            if(value instanceof BigDecimal && type ==double.class){
                                value = ((BigDecimal)value).doubleValue();
                            }
                            sm.invoke(tmp,value);
                        }
                    }
                }
                lst.add(tmp);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close(resultSet,preparedStatement,connection);
        }
        return lst;
    }

    public static  String queryBeenByStringValue(String sql,String nameOfValue,Object ...paramas){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String tmp ="";
        try{
            //获取连接
            connection = getConnection();
            //创建语句对象
            preparedStatement = connection.prepareStatement(sql);
            if(paramas!=null&&paramas.length>0){//需要设置占位符
                for(int i=0;i<paramas.length;i++){
                    preparedStatement.setObject(i+1,paramas[i]);
                }
            }
            //执行SQL语句
            resultSet = preparedStatement.executeQuery();
            //处理结果
            if(resultSet.next()){
                  tmp = resultSet.getString(nameOfValue);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close(resultSet,preparedStatement,connection);
        }
        return tmp;
    }

}

