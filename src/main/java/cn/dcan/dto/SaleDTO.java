package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/10.
 */
public class SaleDTO {

    private int sale_orderId;
    private int sale_productId;
    private int sale_num;
    private double sale_cost;
    //private double sale_planPrice;
    //private double sale_actualPrice;
    private double sale_price;
    private int sale_consumerId;
    private int sale_state;
    //private int sale_type;
    private String sale_orderTime;
    private int sale_user;

    public void setSale_orderId(int id) {
        this.sale_orderId = id;
    }
    public int getSale_orderId() {
        return this.sale_orderId;
    }

    public void setSale_productId(int id) {
        this.sale_productId = id;
    }
    public int getSale_productId() {
        return this.sale_productId;
    }

    public void setSale_num(int num) {
        this.sale_num = num;
    }
    public int getSale_num() {
        return this.sale_num;
    }

    public void setSale_cost(double cost) {
        this.sale_cost = cost;
    }
    public double getSale_cost() {
        return this.sale_cost;
    }

    public void setSale_price(double price) {
        this.sale_price = price;
    }
    public double getSale_price() {
        return this.sale_price;
    }

    /*
    public void setSale_planPrice(double price) {
        this.sale_planPrice = price;
    }
    public double getSale_planPrice() {
        return this.sale_planPrice;
    }

    public void setSale_actualPrice(double price) {
        this.sale_actualPrice = price;
    }
    public double getSale_actualPrice() {
        return this.sale_actualPrice;
    }
     */

    public void setSale_consumerId(int id) {
        this.sale_consumerId = id;
    }
    public int getSale_consumerId() {
        return this.sale_consumerId;
    }

    public void setSale_state(int state) {
        this.sale_state = state;
    }
    public int getSale_state() {
        return this.sale_state;
    }

    /*
    public void setSale_type(int type) {
        this.sale_type = type;
    }
    public int getSale_type() {
        return this.sale_type;
    }
     */

    public void setSale_orderTime(String orderTime) {
        this.sale_orderTime = orderTime;
    }
    public String getSale_orderTime() {
        return this.sale_orderTime;
    }

    public void setSale_user(int user) {
        this.sale_user = user;
    }
    public int getSale_user() {
        return this.sale_user;
    }
}
