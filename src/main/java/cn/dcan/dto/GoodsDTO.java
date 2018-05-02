package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/2.
 */
public class GoodsDTO {

    private int goods_id;
    private int goods_type;
    private String goods_name;

    public void setGoods_id(int id) {
        this.goods_id = id;
    }
    public int getGoods_id() {
        return this.goods_id;
    }

    public void setGoods_type(int type) {
        this.goods_type = type;
    }
    public int getGoods_type() {
        return this.goods_type;
    }

    public void setGoods_name(String name) {
        this.goods_name = name;
    }
    public String getGoods_name() {
        return this.goods_name;
    }
}
