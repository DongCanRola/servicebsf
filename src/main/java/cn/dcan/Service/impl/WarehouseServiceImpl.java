package cn.dcan.Service.impl;

import cn.dcan.Service.WarehouseService;
import cn.dcan.dto.*;
import cn.dcan.entity.*;
import cn.dcan.mapper.*;
import cn.dcan.constrain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dongc_000 on 2018/5/3.
 */
@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    WarehouseMapper warehouseMapper;
    @Autowired
    MaterialStockMapper materialStockMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    PurchaseStoreMapper purchaseStoreMapper;
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    MaterialUseMapper materialUseMapper;
    @Autowired
    ProcessOrderMapper processOrderMapper;

    private ConcreteDataFormat concreteDataFormat = new ConcreteDataFormat();

    @Override
    public List<WarehouseDTO> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseMapper.selectAll();
        List<WarehouseDTO> warehouseDTOS = new ArrayList<>();
        for(Warehouse warehouse : warehouses) {
            warehouseDTOS.add(entityToDto(warehouse));
        }
        return warehouseDTOS;
    }

    @Override
    public int addWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = dtoToEntity(warehouseDTO);
        int count = warehouseMapper.insertSelective(warehouse);
        return warehouse.getId();
    }

    @Override
    public List<MaterialStockDTO> getStockByWarehouse(int warehouseid) {
        List<MaterialStock> materialStocks = materialStockMapper.selectByWarehouse(warehouseid);
        List<MaterialStockDTO> materialStockDTOS = new ArrayList<>();
        for(MaterialStock materialStock : materialStocks) {
            materialStockDTOS.add(stockEntityToDto(materialStock));
        }
        return materialStockDTOS;
    }

    @Override
    public List<MaterialStockDTO> getStockByGoods(int goodsid) {
        List<MaterialStock> materialStocks = materialStockMapper.selectByGoods(goodsid);
        List<MaterialStockDTO> materialStockDTOS = new ArrayList<>();
        for(MaterialStock materialStock : materialStocks) {
            materialStockDTOS.add(stockEntityToDto(materialStock));
        }
        return materialStockDTOS;
    }

    @Override
    public List<GoodsDTO> getTotalByGoods() {
        Map<Integer, Integer> goodsTotal = materialStockMapper.selectGoodsSum();
        List<GoodsDTO> goodsDTOS = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : goodsTotal.entrySet()) {
            int goodsid = entry.getKey();
            int goodsSum = entry.getValue();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsid);
            GoodsDTO goodsDTO = new GoodsDTO();
            goodsDTO.setGoods_id(goodsid);
            goodsDTO.setGoods_name(goods.getName());
            goodsDTO.setGoods_remaining(goodsSum);
            goodsDTOS.add(goodsDTO);
        }
        return goodsDTOS;
    }

    @Override
    public int getNumByGoods(int goodsid) {
        return materialStockMapper.selectNumOfGoods(goodsid);
    }

    @Override
    public int storePurchase(PurchaseStoreDTO purchaseStoreDTO) {
        PurchaseStore purchaseStore = psDtoToEntity(purchaseStoreDTO);
        int count = purchaseStoreMapper.insertSelective(purchaseStore);
        //更新库存盘点
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(purchaseStore.getPurchaseid());
        int goodsid = purchaseOrder.getGoodsid();
        int warehouseid = purchaseStore.getWarehouseid();
        MaterialStockKey materialStockKey = new MaterialStockKey();
        materialStockKey.setGoodsid(goodsid);
        materialStockKey.setWarehouseid(warehouseid);
        MaterialStock materialStock = materialStockMapper.selectByPrimaryKey(materialStockKey);
        int num = materialStock.getNum() + purchaseStore.getInnum();
        materialStock.setNum(num);
        materialStockMapper.updateByPrimaryKey(materialStock);
        //更新仓库剩余空间
        Warehouse warehouse = warehouseMapper.selectByPrimaryKey(warehouseid);
        double spare = warehouse.getSpare() - purchaseStore.getInnum();
        warehouse.setSpare(spare);
        warehouseMapper.updateByPrimaryKey(warehouse);

        return purchaseStore.getId();
    }

    @Override
    public List<PurchaseStoreDTO> getPurchaseStore() {
        List<PurchaseStore> purchaseStores = purchaseStoreMapper.selectAll();
        List<PurchaseStoreDTO> purchaseStoreDTOS = new ArrayList<>();
        for(PurchaseStore purchaseStore : purchaseStores) {
            PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(purchaseStore.getPurchaseid());
            purchaseStoreDTOS.add(psEntityToDto(purchaseStore, purchaseOrder));
        }
        return purchaseStoreDTOS;
    }

    @Override
    public List<PurchaseStoreDTO> getPurchaseStoreByGoods(int goodsid) {
        List<PurchaseStoreDTO> purchaseStoreDTOS = new ArrayList<>();
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.getOrdersByGoods(goodsid);
        for(PurchaseOrder purchaseOrder : purchaseOrders) {
            int purchaseId = purchaseOrder.getId();
            List<PurchaseStore> purchaseStores = purchaseStoreMapper.selectByPurchase(purchaseId);
            for(PurchaseStore purchaseStore : purchaseStores) {
                purchaseStoreDTOS.add(psEntityToDto(purchaseStore, purchaseOrder));
            }
        }
        return purchaseStoreDTOS;
    }

    @Override
    public int useMaterial(MaterialUseDTO materialUseDTO) {
        MaterialUse materialUse = muDtoToEntity(materialUseDTO);

        //更新进货存储分配中剩余量
        int purchaseStoreId = materialUse.getPurchasestoreid();
        int num = materialUse.getUsenum();
        PurchaseStore purchaseStore = purchaseStoreMapper.selectByPrimaryKey(purchaseStoreId);
        int remaining = purchaseStore.getRemaining() - num;
        if(remaining < 0) {
            return -1;
        } else {
            //更新加工材料单材料准备情况及库存盘点
            int processOrderId = materialUse.getProcessorderid();
            List<MaterialUse> materialUses = materialUseMapper.selectByProcessOrder(processOrderId);
            ProcessOrder processOrder = processOrderMapper.selectByPrimaryKey(processOrderId);
            int providedNum = 0;
            for(MaterialUse materialUse1 : materialUses) {
                providedNum = providedNum + materialUse1.getUsenum();
            }
            int upToNowTotal = providedNum + num;
            if(upToNowTotal > processOrder.getNum()) {
                return 0;
            } else {
                int count = materialUseMapper.insertSelective(materialUse);
                //更新进货存储分配剩余量
                purchaseStore.setRemaining(remaining);
                purchaseStoreMapper.updateByPrimaryKey(purchaseStore);

                //更新库存盘点
                int goodsId = processOrder.getGoodsid();
                int warehouseId = purchaseStore.getWarehouseid();
                MaterialStockKey materialStockKey = new MaterialStockKey();
                materialStockKey.setGoodsid(goodsId);
                materialStockKey.setWarehouseid(warehouseId);
                MaterialStock materialStock = materialStockMapper.selectByPrimaryKey(materialStockKey);
                int currentNum = materialStock.getNum() - num;
                materialStock.setNum(currentNum);
                materialStockMapper.updateByPrimaryKey(materialStock);

                //更新仓库剩余空间
                Warehouse warehouse = warehouseMapper.selectByPrimaryKey(warehouseId);
                double spare = warehouse.getSpare() + num;
                warehouse.setSpare(spare);
                warehouseMapper.updateByPrimaryKey(warehouse);

                //更新加工材料单状态
                if(upToNowTotal == processOrder.getNum()) {
                    processOrder.setState(2);
                    processOrderMapper.updateByPrimaryKey(processOrder);
                }
            }
        }
        return materialUse.getId();
    }

    @Override
    public List<MaterialUseDTO> getMaterialUseByList(int processorderid) {
        List<MaterialUse> materialUses = materialUseMapper.selectByProcessOrder(processorderid);
        List<MaterialUseDTO> materialUseDTOS = new ArrayList<>();
        for(MaterialUse materialUse : materialUses) {
            materialUseDTOS.add(muEntityToDto(materialUse));
        }
        return materialUseDTOS;
    }

    @Override
    public List<MaterialUseDTO> getMaterialUseByStore(int purchasestoreid) {
        List<MaterialUse> materialUses = materialUseMapper.selectByPurchaseStore(purchasestoreid);
        List<MaterialUseDTO> materialUseDTOS = new ArrayList<>();
        for(MaterialUse materialUse : materialUses) {
            materialUseDTOS.add(muEntityToDto(materialUse));
        }
        return materialUseDTOS;
    }

    private Warehouse dtoToEntity(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDTO.getWarehouse_name());
        warehouse.setLocation(warehouseDTO.getWarehouse_location());
        warehouse.setSpare(warehouseDTO.getWarehouse_spare());
        return warehouse;
    }
    private WarehouseDTO entityToDto(Warehouse warehouse) {
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setWarehouse_id(warehouse.getId());
        warehouseDTO.setWarehouse_name(warehouse.getName());
        warehouseDTO.setWarehouse_location(warehouse.getLocation());
        warehouseDTO.setWarehouse_spare(warehouse.getSpare());
        return warehouseDTO;
    }

    private MaterialStockDTO stockEntityToDto(MaterialStock materialStock) {
        MaterialStockDTO materialStockDTO = new MaterialStockDTO();
        materialStockDTO.setStock_warehouseId(materialStock.getWarehouseid());
        materialStockDTO.setStock_goodsId(materialStock.getGoodsid());
        materialStockDTO.setStock_num(materialStock.getNum());
        return materialStockDTO;
    }

    private PurchaseStore psDtoToEntity(PurchaseStoreDTO purchaseStoreDTO) {
        PurchaseStore purchaseStore = new PurchaseStore();
        if(purchaseStoreDTO.getPurchase_storeId() != 0) {
            purchaseStore.setId(purchaseStoreDTO.getPurchase_storeId());
        }
        if(purchaseStoreDTO.getPurchase_orderId() != 0) {
            purchaseStore.setPurchaseid(purchaseStoreDTO.getPurchase_orderId());
        }
        if(purchaseStoreDTO.getPurchase_storeWarehouse() != 0) {
            purchaseStore.setWarehouseid(purchaseStoreDTO.getPurchase_storeWarehouse());
        }
        if(purchaseStoreDTO.getPurchase_storeNum() != 0) {
            purchaseStore.setInnum(purchaseStoreDTO.getPurchase_storeNum());
        }
        if(purchaseStoreDTO.getPurchase_storeRemaining() != 0) {
            purchaseStore.setRemaining(purchaseStoreDTO.getPurchase_storeRemaining());
        }
        purchaseStore.setIntime(concreteDataFormat.StringToDate(purchaseStoreDTO.getPurchase_storeTime()));
        if(purchaseStoreDTO.getPurchase_storeUser() != 0) {
            purchaseStore.setUser(purchaseStoreDTO.getPurchase_storeUser());
        }
        return purchaseStore;
    }
    private PurchaseStoreDTO psEntityToDto(PurchaseStore purchaseStore, PurchaseOrder purchaseOrder) {
        PurchaseStoreDTO purchaseStoreDTO = new PurchaseStoreDTO();
        purchaseStoreDTO.setPurchase_storeId(purchaseStore.getId());
        purchaseStoreDTO.setPurchase_orderId(purchaseStore.getPurchaseid());
        purchaseStoreDTO.setPurchase_storeWarehouse(purchaseStore.getWarehouseid());
        purchaseStoreDTO.setPurchase_storeNum(purchaseStore.getInnum());
        purchaseStoreDTO.setPurchase_storePrice(purchaseOrder.getPrice());
        purchaseStoreDTO.setPurchase_storeRemaining(purchaseStore.getRemaining());
        purchaseStoreDTO.setPurchase_storeTime(concreteDataFormat.DateToString(purchaseStore.getIntime()));
        purchaseStoreDTO.setPurchase_storeUser(purchaseStore.getUser());
        return purchaseStoreDTO;
    }

    private MaterialUse muDtoToEntity(MaterialUseDTO materialUseDTO) {
        MaterialUse materialUse = new MaterialUse();
        if(materialUseDTO.getUse_id() != 0) {
            materialUse.setId(materialUseDTO.getUse_id());
        }
        if(materialUseDTO.getUse_listId() != 0) {
            materialUse.setProcessorderid(materialUseDTO.getUse_listId());
        }
        if(materialUseDTO.getUse_storeId() != 0) {
            materialUse.setPurchasestoreid(materialUseDTO.getUse_storeId());
        }
        if(materialUseDTO.getUse_num() != 0) {
            materialUse.setUsenum(materialUseDTO.getUse_num());
        }
        materialUse.setUsetime(concreteDataFormat.StringToDate(materialUseDTO.getUse_time()));
        if(materialUseDTO.getUse_user() != 0) {
            materialUse.setUser(materialUseDTO.getUse_user());
        }
        return materialUse;
    }
    private MaterialUseDTO muEntityToDto(MaterialUse materialUse) {
        MaterialUseDTO materialUseDTO = new MaterialUseDTO();
        materialUseDTO.setUse_id(materialUse.getId());
        materialUseDTO.setUse_listId(materialUse.getProcessorderid());
        materialUseDTO.setUse_storeId(materialUse.getPurchasestoreid());
        materialUseDTO.setUse_num(materialUse.getUsenum());
        materialUseDTO.setUse_time(concreteDataFormat.DateToString(materialUse.getUsetime()));
        materialUseDTO.setUse_user(materialUse.getUser());
        return materialUseDTO;
    }
}
