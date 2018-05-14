package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/14.
 */
public class ProcessOrderDTO {

    private int list_id;
    private int list_process;
    private int list_goods;
    private int list_total;
    private int list_remaining;
    private int list_state;
    private String list_time;

    public void setList_id(int id) {
        this.list_id = id;
    }
    public int getList_id() {
        return this.list_id;
    }

    public void setList_process(int process) {
        this.list_process = process;
    }
    public int getList_process() {
        return this.list_process;
    }

    public void setList_goods(int goods) {
        this.list_goods = goods;
    }
    public int getList_goods() {
        return this.list_goods;
    }

    public void setList_total(int total) {
        this.list_total = total;
    }
    public int getList_total() {
        return this.list_total;
    }

    public void setList_remaining(int remaining) {
        this.list_remaining = remaining;
    }
    public int getList_remaining() {
        return this.list_remaining;
    }

    public void setList_state(int state) {
        this.list_state = state;
    }
    public int getList_state() {
        return this.list_state;
    }

    public void setList_time(String time) {
        this.list_time = time;
    }
    public String getList_time() {
        return this.list_time;
    }
}
