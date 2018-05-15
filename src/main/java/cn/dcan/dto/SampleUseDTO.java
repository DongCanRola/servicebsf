package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/16.
 */
public class SampleUseDTO {

    private int use_id;
    private int use_sampleId;
    private int use_purchaseStoreId;
    private int use_num;
    private String use_time;
    private int use_sampleUser;

    public void setUse_id(int id) {
        this.use_id = id;
    }
    public int getUse_id() {
        return this.use_id;
    }

    public void setUse_sampleId(int sampleId) {
        this.use_sampleId = sampleId;
    }
    public int getUse_sampleId() {
        return this.use_sampleId;
    }

    public void setUse_purchaseStoreId(int purchaseStoreId) {
        this.use_purchaseStoreId = purchaseStoreId;
    }
    public int getUse_purchaseStoreId() {
        return this.use_purchaseStoreId;
    }

    public void setUse_num(int num) {
        this.use_num = num;
    }
    public int getUse_num() {
        return this.use_num;
    }

    public void setUse_time(String time) {
        this.use_time = time;
    }
    public String getUse_time() {
        return this.use_time;
    }

    public void setUse_sampleUser(int user) {
        this.use_sampleUser = user;
    }
    public int getUse_sampleUser() {
        return this.use_sampleUser;
    }
}
