package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/3.
 */
public class WarehouseDTO {

    private int warehouse_id;
    private String warehouse_name;
    private String warehouse_location;
    private double warehouse_spare;

    public void setWarehouse_id(int id) {
        this.warehouse_id = id;
    }
    public int getWarehouse_id() {
        return this.warehouse_id;
    }

    public void setWarehouse_name(String name) {
        this.warehouse_name = name;
    }
    public String getWarehouse_name() {
        return this.warehouse_name;
    }

    public void setWarehouse_location(String location) {
        this.warehouse_location = location;
    }
    public String getWarehouse_location() {
        return this.warehouse_location;
    }

    public void setWarehouse_spare(double spare) {
        this.warehouse_spare = spare;
    }
    public double getWarehouse_spare() {
        return this.warehouse_spare;
    }
}
