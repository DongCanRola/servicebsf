package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/14.
 */
public class ProductSendDTO {

    private int send_id;
    private int send_storeId;
    private int send_num;
    private String send_time;
    private int send_user;

    public void setSend_id(int id) {
        this.send_id = id;
    }
    public int getSend_id() {
        return this.send_id;
    }

    public void setSend_storeId(int storeId) {
        this.send_storeId = storeId;
    }
    public int getSend_storeId() {
        return this.send_storeId;
    }

    public void setSend_num(int num) {
        this.send_num = num;
    }
    public int getSend_num() {
        return this.send_num;
    }

    public void setSend_time(String time) {
        this.send_time = time;
    }
    public String getSend_time() {
        return this.send_time;
    }

    public void setSend_user(int user) {
        this.send_user = user;
    }
    public int getSend_user() {
        return this.send_user;
    }
}
