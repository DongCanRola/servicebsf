package cn.dcan.Service;

import cn.dcan.dto.*;

import java.util.List;

/**
 * Created by dongc_000 on 2018/5/3.
 */
public interface WarehouseService {

    List<WarehouseDTO> getAllWarehouses();

    int addWarehouse(WarehouseDTO warehouseDTO);
    //获取某个仓库库存盘点
    List<MaterialStockDTO> getStockByWarehouse(int warehouseid);
    //获取某种材料库存情况
    List<MaterialStockDTO> getStockByGoods(int goodsid);
    //货物库存列表
    List<GoodsDTO> getTotalByGoods();
    //某种货物库存
    int getNumByGoods(int goodsid);

    //进货存储分配
    int storePurchase(PurchaseStoreDTO purchaseStoreDTO);
    //获取进货存储分配列表
    List<PurchaseStoreDTO> getPurchaseStore();
    //获取某种材料的存储分配
    List<PurchaseStoreDTO> getPurchaseStoreByGoods(int goodsid);

    //加工材料调度
    int useMaterial(MaterialUseDTO materialUseDTO);
    //根据加工材料单查看材料调度情况
    List<MaterialUseDTO> getMaterialUseByList(int processorderid);
    //根据进货存储分配查看材料调度情况
    List<MaterialUseDTO> getMaterialUseByStore(int purchasestoreid);

    //库存报警增加、查看、更新状态
    int addAlarm(StockAlarmDTO stockAlarmDTO);
    List<StockAlarmDTO> getAlarmByState(int state);
    int updateAlarm(StockAlarmDTO stockAlarmDTO);

}
