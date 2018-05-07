package cn.dcan.Service;

import cn.dcan.dto.PurchaseDTO;
import cn.dcan.dto.SavingsDTO;
import cn.dcan.entity.Savings;

import java.util.List;

/**
 * Created by dongc_000 on 2018/5/4.
 */
public interface AccountService {

    List<SavingsDTO> getAllSavings();
    void addSavings(SavingsDTO savingsDTO);

    void addPurchasePay(PurchaseDTO purchaseDTO);
}
