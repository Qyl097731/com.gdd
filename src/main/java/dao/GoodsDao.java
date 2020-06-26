package dao;

import bean.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class GoodsDao {
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

    // 获取数据
    public ArrayList<Goods> queryAllGoods() {
        conn = getConnection();
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        try {
            pstm = conn.prepareStatement("select * from tb_goods");
            rs = pstm.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setGoodsPrice(rs.getDouble("goodsPrice"));
                goods.setGoodsNum(rs.getInt("goodsNum"));
                goods.setGoodsName(rs.getString("goodsName"));
                goodsList.add(goods);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
        return goodsList;
    }

    public Goods queryGoodsById(Integer id) {
        conn = getConnection();
        Goods goods = new Goods();
        try {
            sql = "select * from tb_goods where tb_goods.id=" + id;
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                goods.setId(rs.getInt("id"));
                goods.setGoodsPrice(rs.getDouble("goodsPrice"));
                goods.setGoodsNum(rs.getInt("goodsNum"));
                goods.setGoodsName(rs.getString("goodsName"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
        return goods;
    }


    public void insertGoods(String goodsName, Double goodsPrice, Integer goodsNum) {
        conn = getConnection();
        try {
            sql = "insert into tb_goods(goodsName,goodsPrice,goodsNum) values (?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, goodsName);
            pstm.setDouble(2, goodsPrice);
            pstm.setInt(3, goodsNum);
            pstm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
    }

    public void batchInsertGoods() {
        conn = getConnection();
        try {
            sql = "insert into tb_goods(goodsName,goodsPrice,goodsNum) values (?,?,?)";
            pstm = conn.prepareStatement(sql);
            for (int i = 0; i < 5; i++) {
                pstm.setString(1, UUID.randomUUID().toString().substring(0, 4));
                pstm.setDouble(2, Math.random());
                pstm.setInt(3, (int) (Math.random() * 10));
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
    }


    public void updateGoods(Integer id, String goodsName, Double goodsPrice, Integer goodsNum) {

        conn = getConnection();
        try {
            sql = "update tb_goods set goodsName=?,goodsPrice=?,goodsNum=? where id=?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,goodsName);
            pstm.setDouble(2,goodsPrice);
            pstm.setInt(3,goodsNum);
            pstm.setInt(4,id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
    }

    public void deleteGoodsById(Integer id) {
        conn = getConnection();
        try {
            sql = "delete from tb_goods where tb_goods.id=" + id;
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
