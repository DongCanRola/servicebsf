package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/13.
 */
public class MaterialStockDTO {

    private int stock_warehouseId;
    private int stock_goodsId;
    private int stock_num;

    public void setStock_warehouseId(int warehouseId) {
        this.stock_warehouseId = warehouseId;
    }
    public int getStock_warehouseId() {
        return this.stock_warehouseId;
    }

    public void setStock_goodsId(int goodsId) {
        this.stock_goodsId = goodsId;
    }
    public int getStock_goodsId() {
        return this.stock_goodsId;
    }

    public void setStock_num(int num) {
        this.stock_num = num;
    }
    public int getStock_num() {
        return this.stock_num;
    }
}
