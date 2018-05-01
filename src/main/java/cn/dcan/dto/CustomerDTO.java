package cn.dcan.dto;

import java.io.Serializable;

/**
 * Created by dongc_000 on 2018/5/1.
 */
public class CustomerDTO implements Serializable{

    private int id;
    private int type;
    private String name;
    private int provideType;
    private String manager;
    private String telephone;
    private String email;
    private String address;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setProvideType(int type) {
        this.provideType = type;
    }
    public int getProvideType() {
        return this.provideType;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    public String getManager() {
        return this.manager;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getTelephone() {
        return this.telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }
}
