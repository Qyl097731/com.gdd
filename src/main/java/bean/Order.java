package bean;

/**
 * projectName:  com.gdd
 * packageName: bean
 * date: 2020-06-26 11:39
 * copyright(c) 2020 南晓18卓工 邱依良
 */
public class Order {
    private Integer id;
    private Integer goodsId;
    private String username;
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}