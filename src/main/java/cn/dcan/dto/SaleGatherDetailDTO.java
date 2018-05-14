package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/14.
 */
public class SaleGatherDetailDTO {

    private int detail_id;
    private int detail_gather;
    private double detail_money;
    private String detail_time;
    private int detail_user;
    private String detail_savings;

    public void setDetail_id(int id) {
        this.detail_id = id;
    }
    public int getDetail_id() {
        return this.detail_id;
    }

    public void setDetail_gather(int gather) {
        this.detail_gather = gather;
    }
    public int getDetail_gather() {
        return this.detail_gather;
    }

    public void setDetail_money(double money) {
        this.detail_money = money;
    }
    public double getDetail_money() {
        return this.detail_money;
    }

    public void setDetail_time(String time) {
        this.detail_time = time;
    }
    public String getDetail_time() {
        return this.detail_time;
    }

    public void setDetail_user(int user) {
        this.detail_user = user;
    }
    public int getDetail_user() {
        return this.detail_user;
    }

    public void setDetail_savings(String savings) {
        this.detail_savings = savings;
    }
    public String getDetail_savings() {
        return this.detail_savings;
    }
}
