package cn.dcan.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dongc_000 on 2018/4/27.
 */
public class UserDTO implements Serializable{

    private int user_id;
    private String user_name;
    private String phone;
    private String qqnumber;
    private String wechat;
    private String email;
    private String password;
    private ArrayList<Integer> user_roles;

    public void setUser_id(int id) {
        this.user_id = id;
    }
    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_name(String name) {
        this.user_name = name;
    }
    public String getUser_name() {
        return this.user_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return this.phone;
    }

    public void setQqnumber(String number) {
        this.qqnumber = number;
    }
    public String getQqnumber(){
        return this.qqnumber;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getWechat() {
        return this.wechat;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public void setUser_roles(ArrayList<Integer> roles) {
        this.user_roles = roles;
    }
    public ArrayList<Integer> getUser_roles() {
        return this.user_roles;
    }
}
