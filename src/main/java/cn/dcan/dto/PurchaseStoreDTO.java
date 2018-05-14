package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/14.
 */
public class PurchaseStoreDTO {

    private int purchase_storeId;
    private int purchase_orderId;
    private int purchase_storeWarehouse;
    private int purchase_storeNum;
    private double purchase_storePrice;
    private int purchase_storeRemaining;
    private String purchase_storeTime;
    private int purchase_storeUser;

    public void setPurchase_storeId(int id) {
        this.purchase_storeId = id;
    }
    public int getPurchase_storeId() {
        return this.purchase_storeId;
    }

    public void setPurchase_orderId(int id) {
        this.purchase_orderId = id;
    }
    public int getPurchase_orderId() {
        return this.purchase_orderId;
    }

    public void setPurchase_storeWarehouse(int warehouse) {
        this.purchase_storeWarehouse = warehouse;
    }
    public int getPurchase_storeWarehouse() {
        return this.purchase_storeWarehouse;
    }

    public void setPurchase_storeNum(int num) {
        this.purchase_storeNum = num;
    }
    public int getPurchase_storeNum() {
        return this.purchase_storeNum;
    }

    public void setPurchase_storePrice(double price) {
        this.purchase_storePrice = price;
    }
    public double getPurchase_storePrice() {
        return this.purchase_storePrice;
    }

    public void setPurchase_storeRemaining(int remaining) {
        this.purchase_storeRemaining = remaining;
    }
    public int getPurchase_storeRemaining() {
        return this.purchase_storeRemaining;
    }

    public void setPurchase_storeTime(String time) {
        this.purchase_storeTime = time;
    }
    public String getPurchase_storeTime() {
        return this.purchase_storeTime;
    }

    public void setPurchase_storeUser(int user) {
        this.purchase_storeUser = user;
    }
    public int getPurchase_storeUser() {
        return this.purchase_storeUser;
    }
}
