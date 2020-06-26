package DB;

import java.sql.*;

/**
 * JDBC的工具类：
 * 加载驱动：只需要加载一次
 * 建立连接：
 * 释放资源：
 */

public class JDBCUTILS {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    // 主机地址 连接本机 localhost  或者127.0.0.1
    // 端口号 Oracle 数据库默认端口号 1521
    // 实例名 安装全的是orcl，没有安装全的是XE
    private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";   // jdbc:oracle:thin: @主机地址 :  端口号 : 实例名
    private static String USER = "wbd";
    private static String PASSWORD = "wbdpsd";
    static {//因为驱动只需要加载一次，所以在静态语句中进行
        try {
            Class.forName(DRIVER);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *建立与数据库的连接
     * @return 连接好的连接
     */
    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return  connection;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源
     * @param resultSet 结果集
     * @param statement 语句对象
     * @param connection 连接
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection){//查询时
        try
        {
            if (resultSet != null && !resultSet.isClosed())
                resultSet.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (statement != null && !statement.isClosed())
                    statement.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                try
                {
                    if (connection != null && !connection.isClosed())
                        connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();;
                }
            }
        }
    }

    /**
     * 释放资源
     * @param statement 语句对象
     * @param connection 连接
     */
    public static void close(Statement statement, Connection connection){//增删改
        close(null,statement,connection);
    }
}

