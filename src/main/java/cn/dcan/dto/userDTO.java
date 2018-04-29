package cn.dcan.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dongc_000 on 2018/4/27.
 */
public class userDTO implements Serializable{

    private int user_id;
    private String user_name;
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

    public void setUser_roles(ArrayList<Integer> roles) {
        this.user_roles = roles;
    }
    public ArrayList<Integer> getUser_roles() {
        return this.user_roles;
    }
}
