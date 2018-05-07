package cn.dcan.Service;

import cn.dcan.dto.PurchaseDTO;

import java.util.List;

/**
 * Created by dongc_000 on 2018/5/5.
 */
public interface PurchaseService {

    List<PurchaseDTO> getAllOrders();

    List<PurchaseDTO> getOrdersByState(int state);

    int addPurchaseOrder(PurchaseDTO purchaseDTO);

    boolean changeOrderContent(PurchaseDTO purchaseDTO);
}
