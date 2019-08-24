package top.xiaobucvg.generator.util;

import com.mysql.jdbc.StringUtils;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/***
 * 数据库操作工具
 *
 * by Mr.Zhang
 */
public class DBUtil {
    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Class.forName(PropertyUtil.getValue("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        url = PropertyUtil.getValue("jdbc.url");
        user = PropertyUtil.getValue("jdbc.user");
        password = PropertyUtil.getValue("jdbc.password");
    }

    /***
     * 获取一个数据库连接
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /***
     * 关闭一个连接
     */
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /***
     * 获取当前数据库的所有表名
     */
    public static List<String> getTableNames() {
        List<String> list = new ArrayList<>();
        Connection connection = getConnection();
        if (connection == null) {
            return list;
        }
        ResultSet tables = null;
        try {
            tables = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
            while (tables.next()) {
                String name = tables.getString("TABLE_NAME");
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /***
     * 根据表名字获取所有字段映射 Map<列名String,列类型String>
     * 例: <id,java.lang.Integer>
     */
    public static Map<String, String> getFieldMapper(String tableName) {
        Connection connection = getConnection();
        Map<String, String> map = new HashMap<>();
        if (connection == null || StringUtils.isNullOrEmpty(tableName)) {
            return map;
        }
        String sql = "select * from " + tableName;
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnClassName = metaData.getColumnClassName(i);
                String columnName = metaData.getColumnName(i);
                map.put(columnName, columnClassName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
