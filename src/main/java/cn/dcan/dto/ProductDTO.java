package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/2.
 */
public class ProductDTO {

    private int product_id;
    private String product_name;
    private String product_level;
    private String product_color;
    private String product_style;
    private int product_state;

    public void setProduct_id(int id) {
        this.product_id = id;
    }
    public int getProduct_id() {
        return this.product_id;
    }

    public void setProduct_name(String name) {
        this.product_name = name;
    }
    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_level(String level) {
        this.product_level = level;
    }
    public String getProduct_level() {
        return this.product_level;
    }

    public void setProduct_color(String color) {
        this.product_color = color;
    }
    public String getProduct_color() {
        return this.product_color;
    }

    public void setProduct_style(String style) {
        this.product_style = style;
    }
    public String getProduct_style() {
        return this.product_style;
    }

    public void setProduct_state(int state) {
        this.product_state = state;
    }
    public int getProduct_state() {
        return this.product_state;
    }
}
