package dao;

import bean.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;

/**
 * projectName:  com.zhy
 * packageName: dao
 * date: 2020-06-24 19:29
 * copyright(c) 2020 南晓18卓工 邱依良
 */
public class UserDao {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    private String sql;

    // 连接数据库
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动错误！");
        }
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/clotheShop?useUnicode=true&characterEncoding=utf8&useSSL=false", "root",
                    "");

        } catch (SQLException e) {
            System.out.println("数据库连接字符串错误！");
        }
        return conn;
    }


    public User queryUserByUsername(String username, String authority) {
        conn = getConnection();
        User user = new User();
        try {
            sql = "select * from tb_user where tb_user.username=" + username + " and authority=" + authority;
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAuthority(rs.getString("authority"));
                user.setTel(rs.getString("tel"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
        return user;
    }

    public void insertUser(String username, String password, String authority, String tel) {
        conn = getConnection();
        try {
            sql = "insert into tb_user(username,password,authority,tel) values (?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, authority);
            pstm.setString(4, tel);
            pstm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
    }

    public void updateUser(String username, String password, String tel) {
        conn = getConnection();
        try {
            sql = "update tb_user set password=?, tel=? where username=?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,password);
            pstm.setString(2,tel);
            pstm.setString(3,username);
            System.out.println(pstm);
            System.out.println(sql);
            pstm.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
    }

    public void releaseDB(ResultSet rs, PreparedStatement pst, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}