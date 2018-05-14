package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/14.
 */
public class StockAlarmDTO {

    private int alarm_id;
    private int alarm_goods;
    private int alarm_current;
    private int alarm_required;
    private String alarm_time;
    private int alarm_user;
    private int alarm_state;

    public void setAlarm_id(int id) {
        this.alarm_id = id;
    }
    public int getAlarm_id() {
        return this.alarm_id;
    }

    public void setAlarm_goods(int goods) {
        this.alarm_goods = goods;
    }
    public int getAlarm_goods() {
        return this.alarm_goods;
    }

    public void setAlarm_current(int current) {
        this.alarm_current = current;
    }
    public int getAlarm_current() {
        return this.alarm_current;
    }

    public void setAlarm_required(int required) {
        this.alarm_required = required;
    }
    public int getAlarm_required() {
        return this.alarm_required;
    }

    public void setAlarm_time(String time) {
        this.alarm_time = time;
    }
    public String getAlarm_time() {
        return this.alarm_time;
    }

    public void setAlarm_user(int user) {
        this.alarm_user = user;
    }
    public int getAlarm_user() {
        return this.alarm_user;
    }

    public void setAlarm_state(int state) {
        this.alarm_state = state;
    }
    public int getAlarm_state() {
        return this.alarm_state;
    }
}
