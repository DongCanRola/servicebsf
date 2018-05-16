package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/13.
 */
public class ProcessDTO {

    private int process_id;
    private int sale_orderId;
    private int sample_id;
    private int process_state;
    private int process_userId;
    private int process_productNum;

    public void setProcess_id(int id) {
        this.process_id = id;
    }
    public int getProcess_id() {
        return this.process_id;
    }

    public void setSale_orderId(int sale) {
        this.sale_orderId = sale;
    }
    public int getSale_orderId() {
        return this.sale_orderId;
    }

    public void setSample_id(int sample) {
        this.sample_id = sample;
    }
    public int getSample_id() {
        return this.sample_id;
    }

    public void setProcess_state(int state) {
        this.process_state = state;
    }
    public int getProcess_state() {
        return this.process_state;
    }

    public void setProcess_userId(int user) {
        this.process_userId = user;
    }
    public int getProcess_userId() {
        return this.process_userId;
    }

    public void setProcess_productNum(int num) {
        this.process_productNum = num;
    }
    public int getProcess_productNum() {
        return this.process_productNum;
    }
}
