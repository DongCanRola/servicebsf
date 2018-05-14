package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/14.
 */
public class ProductStoreDTO {

    private int store_id;
    private int store_saleId;
    private int store_warehouseId;
    private int store_num;
    private int store_remaining;
    private String store_time;
    private int store_user;

    public void setStore_id(int id) {
        this.store_id = id;
    }
    public int getStore_id() {
        return this.store_id;
    }

    public void setStore_saleId(int sale) {
        this.store_saleId = sale;
    }
    public int getStore_saleId() {
        return this.store_saleId;
    }

    public void setStore_warehouseId(int warehouseId) {
        this.store_warehouseId = warehouseId;
    }
    public int getStore_warehouseId() {
        return this.store_warehouseId;
    }

    public void setStore_num(int num) {
        this.store_num = num;
    }
    public int getStore_num() {
        return this.store_num;
    }

    public void setStore_remaining(int remaining) {
        this.store_remaining = remaining;
    }
    public int getStore_remaining() {
        return this.store_remaining;
    }

    public void setStore_time(String time) {
        this.store_time = time;
    }
    public String getStore_time() {
        return this.store_time;
    }

    public void setStore_user(int user) {
        this.store_user = user;
    }
    public int getStore_user() {
        return this.store_user;
    }
}
