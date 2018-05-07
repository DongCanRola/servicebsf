package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/5.
 */
public class PurchaseDTO {

    private int purchaseOrder_id;
    private int purchaseGoods_id;
    private String purchaseGoods_name;
    private int purchase_num;
    private double purchase_price;
    private int provider_id;
    private String provider_name;
    private int purchase_state;
    private String purchase_time;
    private int purchaseReturn_num;

    public void setPurchaseOrder_id(int id) {
        this.purchaseOrder_id = id;
    }
    public int getPurchaseOrder_id() {
        return this.purchaseOrder_id;
    }

    public void setPurchaseGoods_id(int id) {
        this.purchaseGoods_id = id;
    }
    public int getPurchaseGoods_id() {
        return this.purchaseGoods_id;
    }

    public void setPurchaseGoods_name(String name) {
        this.purchaseGoods_name = name;
    }
    public String getPurchaseGoods_name() {
        return this.purchaseGoods_name;
    }

    public void setPurchase_num(int num) {
        this.purchase_num = num;
    }
    public int getPurchase_num() {
        return this.purchase_num;
    }

    public void setPurchase_price(double price) {
        this.purchase_price = price;
    }
    public double getPurchase_price() {
        return this.purchase_price;
    }

    public void setProvider_id(int id) {
        this.provider_id = id;
    }
    public int getProvider_id() {
        return this.provider_id;
    }

    public void setProvider_name(String name) {
        this.provider_name = name;
    }
    public String getProvider_name() {
        return this.provider_name;
    }

    public void setPurchase_state(int state) {
        this.purchase_state = state;
    }
    public int getPurchase_state() {
        return this.purchase_state;
    }

    public void setPurchase_time(String time) {
        this.purchase_time = time;
    }
    public String getPurchase_time() {
        return this.purchase_time;
    }
}
