package cn.dcan.Service;

import cn.dcan.dto.SaleDTO;

import java.util.List;

/**
 * Created by dongc_000 on 2018/5/10.
 */
public interface SaleService {

    List<SaleDTO> getOrdersByState(int state);

    int addOrder(SaleDTO saleDTO);
}
