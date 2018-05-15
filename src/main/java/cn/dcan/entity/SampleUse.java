package cn.dcan.entity;

import java.util.Date;

public class SampleUse {
    private Integer id;

    private Integer sampleid;

    private Integer purchasestoreid;

    private Integer usenum;

    private Date usetime;

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSampleid() {
        return sampleid;
    }

    public void setSampleid(Integer sampleid) {
        this.sampleid = sampleid;
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}