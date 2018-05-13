package cn.dcan.entity;

public class SaleGather {
    private Integer id;

    private Integer saleid;

    private Double plantotal;

    private Double actualtotal;

    private Double discount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSaleid() {
        return saleid;
    }

    public void setSaleid(Integer saleid) {
        this.saleid = saleid;
    }

    public Double getPlantotal() {
        return plantotal;
    }

    public void setPlantotal(Double plantotal) {
        this.plantotal = plantotal;
    }

    public Double getActualtotal() {
        return actualtotal;
    }

    public void setActualtotal(Double actualtotal) {
        this.actualtotal = actualtotal;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}