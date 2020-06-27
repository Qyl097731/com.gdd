package dao;

import bean.Cart;
import bean.Order;
import bean.User;
import org.aspectj.weaver.ast.Or;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;

/**
 * projectName:  com.zhy
 * packageName: dao
 * date: 2020-06-24 19:29
 * copyright(c) 2020 南晓18卓工 邱依良
 */
public class OrderDao {
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

    public ArrayList<Order> queryOrders() {
        conn = getConnection();
        ArrayList<Order>orders = new ArrayList<>();
        try {
            sql = "select * from tb_order order by id desc ";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setGoodsId(rs.getInt("goodsId"));
                order.setTime(rs.getString("time"));
                order.setUsername(rs.getString("username"));
                orders.add(order);
                System.out.println(orders.toString());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
        return orders;
    }
    public ArrayList<Order> queryOrderByUsername(String username) {
        conn = getConnection();
        ArrayList<Order>orders = new ArrayList<>();
        try {
            sql = "select * from tb_order where username=?  order by id desc";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,username);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setGoodsId(rs.getInt("goodsId"));
                order.setTime(rs.getString("time"));
                order.setUsername(rs.getString("username"));
                orders.add(order);
                System.out.println(orders.toString());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
        return orders;
    }

    public void insertOrder(Integer goodsId, String time,String username) {
        conn = getConnection();
        try {
            sql = "insert into tb_order(goodsId,time,username) values (?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, goodsId);
            pstm.setString(2, time);
            pstm.setString(3,username);
            pstm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
    }


    public void deleteOrderById(Integer id) {
        conn = getConnection();
        try {
            sql = "delete from tb_order where tb_order.id=" + id;
            pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (SQLException e) {
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