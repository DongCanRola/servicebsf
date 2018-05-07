package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/7.
 */
public class PurchasePayDTO {

    private int purchasePay_id;
    private int purchase_id;
    private double plan_total;
    private double discount;
    private double actual_total;
    private double already_pay;
    private double surplus;

    public void setPurchasePay_id(int id) {
        this.purchasePay_id = id;
    }
    public int getPurchasePay_id() {
        return this.purchasePay_id;
    }

    public void setPurchase_id(int id) {
        this.purchase_id = id;
    }
    public int getPurchase_id() {
        return this.purchase_id;
    }

    public void setPlan_total(double total) {
        this.plan_total = total;
    }
    public double getPlan_total() {
        return this.plan_total;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getDiscount() {
        return this.discount;
    }

    public void setActual_total() {
        this.actual_total = this.plan_total - this.discount;
    }

    public double getActual_total() {
        return this.actual_total;
    }

    public void setAlready_pay(double already_pay) {
        this.already_pay = already_pay;
    }
    public double getAlready_pay() {
        return this.already_pay;
    }

    public void setSurplus() {
        this.surplus = this.actual_total - this.already_pay;
    }

    public double getSurplus() {
        return this.surplus;
    }
}
