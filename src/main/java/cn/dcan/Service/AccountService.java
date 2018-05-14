package cn.dcan.Service;

import cn.dcan.dto.*;
import cn.dcan.entity.SaleGatherDetail;
import cn.dcan.entity.Savings;

import java.util.List;

/**
 * Created by dongc_000 on 2018/5/4.
 */
public interface AccountService {

    List<SavingsDTO> getAllSavings();
    void addSavings(SavingsDTO savingsDTO);

    void addPurchasePay(PurchaseDTO purchaseDTO);
    List<PurchasePayDTO> getPurchasePayList();
    int addPurchasePayDetail(PurchasePayDetailDTO purchasePayDetailDTO);
    List<PurchasePayDetailDTO> getPurchasePayDetailList();
    List<PurchasePayDetailDTO> getPurchasePayDetailByPay(int payId);
    List<PurchasePayDetailDTO> getPurchasePayDetailBySavings(String savings);

    void addSaleGather(SaleDTO saleDTO);
    List<SaleGatherDTO> getSaleGatherList();
    int addSaleGatherDetail(SaleGatherDetailDTO saleGatherDetailDTO);
    List<SaleGatherDetailDTO> getSaleGatherDetailList();
    List<SaleGatherDetailDTO> getSaleGatherDetailByGather(int gatherId);
    List<SaleGatherDetailDTO> getSaleGatherDetailBySavings(String savings);
}
