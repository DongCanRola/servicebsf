package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/14.
 */
public class MaterialUseDTO {

    private int use_id;
    private int use_listId;
    private int use_storeId;
    private int use_num;
    private String use_time;
    private int use_user;

    public void setUse_id(int id) {
        this.use_id = id;
    }
    public int getUse_id() {
        return this.use_id;
    }

    public void setUse_listId(int list) {
        this.use_listId = list;
    }
    public int getUse_listId() {
        return this.use_listId;
    }

    public void setUse_storeId(int purchaseStore) {
        this.use_storeId = purchaseStore;
    }
    public int getUse_storeId() {
        return this.use_storeId;
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

    public void setUse_user(int user) {
        this.use_user = user;
    }
    public int getUse_user() {
        return this.use_user;
    }
}
