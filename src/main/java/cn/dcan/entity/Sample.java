package cn.dcan.entity;

public class Sample {
    private Integer id;

    private Integer productid;

    private String description;

    private Double materialcost;

    private Double processcost;

    private Double humancost;

    private Integer user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Double getMaterialcost() {
        return materialcost;
    }

    public void setMaterialcost(Double materialcost) {
        this.materialcost = materialcost;
    }

    public Double getProcesscost() {
        return processcost;
    }

    public void setProcesscost(Double processcost) {
        this.processcost = processcost;
    }

    public Double getHumancost() {
        return humancost;
    }

    public void setHumancost(Double humancost) {
        this.humancost = humancost;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}