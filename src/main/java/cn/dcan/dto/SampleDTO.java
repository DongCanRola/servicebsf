package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/13.
 */
public class SampleDTO {

    private int sample_id;
    private int sample_productId;
    private String sample_description;
    private double sample_materialCost;
    private double sample_processCost;
    private double sample_humanCost;
    private int sample_userId;

    public void setSample_id(int id) {
        this.sample_id = id;
    }
    public int getSample_id() {
        return this.sample_id;
    }

    public void setSample_productId(int product) {
        this.sample_productId = product;
    }
    public int getSample_productId() {
        return this.sample_productId;
    }

    public void setSample_description(String description) {
        this.sample_description = description;
    }
    public String getSample_description() {
        return this.sample_description;
    }

    public void setSample_materialCost(double cost) {
        this.sample_materialCost = cost;
    }
    public double getSample_materialCost() {
        return this.sample_materialCost;
    }

    public void setSample_processCost(double cost) {
        this.sample_processCost = cost;
    }
    public double getSample_processCost() {
        return this.sample_processCost;
    }

    public void setSample_humanCost(double cost) {
        this.sample_humanCost = cost;
    }
    public double getSample_humanCost() {
        return this.sample_humanCost;
    }

    public void setSample_userId(int user) {
        this.sample_userId = user;
    }
    public int getSample_userId() {
        return this.sample_userId;
    }
}
