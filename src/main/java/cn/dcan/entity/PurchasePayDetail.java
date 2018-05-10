package cn.dcan.entity;

import java.util.Date;

public class PurchasePayDetail {
    private Integer id;

    private Integer payid;

    private Double money;

    private Date paytime;

    private Integer userid;

    private String savingsid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayid() {
        return payid;
    }

    public void setPayid(Integer payid) {
        this.payid = payid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSavingsid() {
        return savingsid;
    }

    public void setSavingsid(String savingsid) {
        this.savingsid = savingsid == null ? null : savingsid.trim();
    }
}