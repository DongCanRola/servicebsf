package cn.dcan.entity;

public class Warehouse {
    private Integer id;

    private String name;

    private String location;

    private Double spare;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Double getSpare() {
        return spare;
    }

    public void setSpare(Double spare) {
        this.spare = spare;
    }
}