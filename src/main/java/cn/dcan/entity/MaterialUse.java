package cn.dcan.entity;

import java.util.Date;

public class MaterialUse {
    private Integer id;

    private Integer processorderid;

    private Integer purchasestoreid;

    private Integer usenum;

    private Date usetime;

    private Integer user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessorderid() {
        return processorderid;
    }

    public void setProcessorderid(Integer processorderid) {
        this.processorderid = processorderid;
    }

    public Integer getPurchasestoreid() {
        return purchasestoreid;
    }

    public void setPurchasestoreid(Integer purchasestoreid) {
        this.purchasestoreid = purchasestoreid;
    }

    public Integer getUsenum() {
        return usenum;
    }

    public void setUsenum(Integer usenum) {
        this.usenum = usenum;
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}