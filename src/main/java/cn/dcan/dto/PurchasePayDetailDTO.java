package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/9.
 */
public class PurchasePayDetailDTO {

    private int detail_id;
    private int pay_id;
    private double pay_money;
    private String pay_time;
    private int pay_user;
    private String pay_savings;

    public void setDetail_id(int id) {
        this.detail_id = id;
    }
    public int getDetail_id() {
        return this.detail_id;
    }

    public void setPay_id(int id) {
        this.pay_id = id;
    }
    public int getPay_id() {
        return this.pay_id;
    }

    public void setPay_money(double money) {
        this.pay_money = money;
    }
    public double getPay_money() {
        return this.pay_money;
    }

    public void setPay_time(String time) {
        this.pay_time = time;
    }
    public String getPay_time() {
        return this.pay_time;
    }

    public void setPay_user(int id) {
        this.pay_user = id;
    }
    public int getPay_user() {
        return this.pay_user;
    }

    public void setPay_savings(String id) {
        this.pay_savings = id;
    }
    public String getPay_savings() {
        return this.pay_savings;
    }
}
