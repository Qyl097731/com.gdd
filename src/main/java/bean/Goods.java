package bean;

/**
 * projectName:  com.zhy
 * packageName: bean
 * date: 2020-06-24 00:02
 * copyright(c) 2020 南晓18卓工 邱依良
 */
public class Goods {
    private Integer id;
    private String goodsName;
    private Double goodsPrice;
    private Integer goodsNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsNum=" + goodsNum +
                '}';
    }
}