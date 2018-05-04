package cn.dcan.dto;

/**
 * Created by dongc_000 on 2018/5/4.
 */
public class SavingsDTO {

    private String savings_id;
    private String savings_bank;
    private double savings_balance;

    public void setSavings_id(String id) {
        this.savings_id = id;
    }
    public String getSavings_id() {
        return this.savings_id;
    }

    public void setSavings_bank(String bank) {
        this.savings_bank = bank;
    }
    public String getSavings_bank() {
        return this.savings_bank;
    }

    public void setSavings_balance(double balance) {
        this.savings_balance = balance;
    }
    public double getSavings_balance() {
        return this.savings_balance;
    }
}
