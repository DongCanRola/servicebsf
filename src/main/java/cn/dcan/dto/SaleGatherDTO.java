package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/14.
 */
public class SaleGatherDTO {

    private int gather_id;
    private int gather_saleId;
    private double gather_planTotal;
    private double gather_discount;
    private double gather_actualTotal;
    private double gather_already;
    private double gather_surplus;
    private String gather_saleTime;

    public void setGather_id(int id) {
        this.gather_id = id;
    }
    public int getGather_id() {
        return this.gather_id;
    }

    public void setGather_saleId(int sale) {
        this.gather_saleId = sale;
    }
    public int getGather_saleId() {
        return this.gather_saleId;
    }

    public void setGather_planTotal(double total) {
        this.gather_planTotal = total;
    }
    public double getGather_planTotal() {
        return this.gather_planTotal;
    }

    public void setGather_discount(double discount) {
        this.gather_discount = discount;
    }
    public double getGather_discount() {
        return this.gather_discount;
    }

    public void setGather_actualTotal(double total) {
        this.gather_actualTotal = total;
    }
    public double getGather_actualTotal() {
        return this.gather_actualTotal;
    }

    public void setGather_already(double already) {
        this.gather_already = already;
    }
    public double getGather_already() {
        return this.gather_already;
    }

    public void setGather_surplus(double surplus) {
        this.gather_surplus = surplus;
    }
    public double getGather_surplus() {
        return this.gather_surplus;
    }

    public void setGather_saleTime(String time) {
        this.gather_saleTime = time;
    }
    public String getGather_saleTime() {
        return this.gather_saleTime;
    }
}
