package cn.dcan.Service.impl;

import cn.dcan.Service.PurchaseService;
import cn.dcan.dto.PurchaseDTO;
import cn.dcan.entity.Customer;
import cn.dcan.entity.Goods;
import cn.dcan.entity.PurchaseOrder;
import cn.dcan.entity.PurchaseStore;
import cn.dcan.mapper.CustomerMapper;
import cn.dcan.mapper.GoodsMapper;
import cn.dcan.mapper.PurchaseOrderMapper;
import cn.dcan.constrain.*;
import cn.dcan.mapper.PurchaseStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/5.
 */
@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    PurchaseStoreMapper purchaseStoreMapper;

    private ConcreteDataFormat concreteDataFormat = new ConcreteDataFormat();

    @Override
    public List<PurchaseDTO> getAllOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.getAllOrders();
        List<PurchaseDTO> purchaseDTOS = new ArrayList<>();
        Goods goods = null;
        Customer customer = null;
        for(PurchaseOrder purchaseOrder : purchaseOrders) {
            goods = goodsMapper.selectByPrimaryKey(purchaseOrder.getGoodsid());
            customer = customerMapper.selectByPrimaryKey(purchaseOrder.getProviderid());
            purchaseDTOS.add(entityToDto(purchaseOrder, goods, customer));
        }
        return purchaseDTOS;
    }

    @Override
    public List<PurchaseDTO> getOrdersByState(int state) {
        System.out.println("select state service: " + state);
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.getOrdersByState(state);
        System.out.println(purchaseOrders);
        List<PurchaseDTO> purchaseDTOS = new ArrayList<>();
        Goods goods = null;
        Customer customer = null;
        for(PurchaseOrder purchaseOrder : purchaseOrders) {
            goods = goodsMapper.selectByPrimaryKey(purchaseOrder.getGoodsid());
            customer = customerMapper.selectByPrimaryKey(purchaseOrder.getProviderid());
            purchaseDTOS.add(entityToDto(purchaseOrder, goods, customer));
        }
        //到货进货订单未入库数量计算
        if(state == 3) {
            for(PurchaseDTO purchaseDTO : purchaseDTOS) {
                int purchaseId = purchaseDTO.getPurchaseOrder_id();
                List<PurchaseStore> purchaseStores = purchaseStoreMapper.selectByPurchase(purchaseId);
                int alreadyStore = 0;
                for(PurchaseStore purchaseStore : purchaseStores) {
                    alreadyStore = alreadyStore + purchaseStore.getInnum();
                }
                int remaining = purchaseDTO.getPurchase_num() - alreadyStore;
                purchaseDTO.setWait_store(remaining);
            }
        }
        return purchaseDTOS;
    }

    @Override
    public int addPurchaseOrder(PurchaseDTO purchaseDTO) {
        PurchaseOrder purchaseOrder = dtoToEntity(purchaseDTO);
        int count = purchaseOrderMapper.insertSelective(purchaseOrder);
        return purchaseOrder.getId();
    }

    @Override
    public boolean changeOrderContent(PurchaseDTO purchaseDTO) {
        PurchaseOrder purchaseOrder = dtoToEntity(purchaseDTO);
        purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrder);
        return true;
    }

    private PurchaseDTO entityToDto(PurchaseOrder purchaseOrder, Goods goods, Customer customer) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setPurchaseOrder_id(purchaseOrder.getId());
        purchaseDTO.setPurchaseGoods_id(purchaseOrder.getGoodsid());
        purchaseDTO.setPurchaseGoods_name(goods.getName());
        purchaseDTO.setPurchase_num(purchaseOrder.getNum());
        purchaseDTO.setPurchase_price(purchaseOrder.getPrice());
        purchaseDTO.setProvider_id(purchaseOrder.getProviderid());
        purchaseDTO.setProvider_name(customer.getName());
        purchaseDTO.setPurchase_state(purchaseOrder.getState());
        purchaseDTO.setPurchase_time(concreteDataFormat.DateToString(purchaseOrder.getOrdertime()));
        return purchaseDTO;
    }

    private PurchaseOrder dtoToEntity(PurchaseDTO purchaseDTO) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(purchaseDTO.getPurchaseOrder_id());
        if(purchaseDTO.getPurchaseGoods_id() != 0) {
            purchaseOrder.setGoodsid(purchaseDTO.getPurchaseGoods_id());
        }
        //purchaseOrder.setGoodsid(purchaseDTO.getPurchaseGoods_id());
        if(purchaseDTO.getPurchase_num() != 0) {
            purchaseOrder.setNum(purchaseDTO.getPurchase_num());
        }
        //purchaseOrder.setNum(purchaseDTO.getPurchase_num());
        if(purchaseDTO.getPurchase_price() != 0) {
            purchaseOrder.setPrice(purchaseDTO.getPurchase_price());
        }
        //purchaseOrder.setPrice(purchaseDTO.getPurchase_price());
        if(purchaseDTO.getProvider_id() != 0) {
            purchaseOrder.setProviderid(purchaseDTO.getProvider_id());
        }
        //purchaseOrder.setProviderid(purchaseDTO.getProvider_id());
        if(purchaseDTO.getPurchase_state() != 0) {
            purchaseOrder.setState(purchaseDTO.getPurchase_state());
        }
        /*
        Integer state = purchaseDTO.getPurchase_state();
        if(state != null) {

        }
         */
        purchaseOrder.setOrdertime(concreteDataFormat.StringToDate(purchaseDTO.getPurchase_time()));
        return purchaseOrder;
    }
}
