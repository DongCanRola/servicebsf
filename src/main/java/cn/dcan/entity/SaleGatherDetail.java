package cn.dcan.entity;

import java.util.Date;

public class SaleGatherDetail {
    private Integer id;

    private Integer gatherid;

    private Double money;

    private Date gathertime;

    private Integer userid;

    private String savingsid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGatherid() {
        return gatherid;
    }

    public void setGatherid(Integer gatherid) {
        this.gatherid = gatherid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getGathertime() {
        return gathertime;
    }

    public void setGathertime(Date gathertime) {
        this.gathertime = gathertime;
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