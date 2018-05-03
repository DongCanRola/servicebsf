package cn.dcan.Service;

import cn.dcan.dto.WarehouseDTO;

import java.util.List;

/**
 * Created by dongc_000 on 2018/5/3.
 */
public interface WarehouseService {

    List<WarehouseDTO> getAllWarehouses();

    int addWarehouse(WarehouseDTO warehouseDTO);
}
