package cn.dcan.Service.impl;

import cn.dcan.Service.PurchaseService;
import cn.dcan.dto.PurchaseDTO;
import cn.dcan.entity.Customer;
import cn.dcan.entity.Goods;
import cn.dcan.entity.PurchaseOrder;
import cn.dcan.mapper.CustomerMapper;
import cn.dcan.mapper.GoodsMapper;
import cn.dcan.mapper.PurchaseOrderMapper;
import cn.dcan.constrain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.getOrdersByState(state);
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
    public int addPurchaseOrder(PurchaseDTO purchaseDTO) {
        PurchaseOrder purchaseOrder = dtoToEntity(purchaseDTO);
        int count = purchaseOrderMapper.insertSelective(purchaseOrder);
        return purchaseOrder.getId();
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
        purchaseOrder.setGoodsid(purchaseDTO.getPurchaseGoods_id());
        purchaseOrder.setNum(purchaseDTO.getPurchase_num());
        purchaseOrder.setPrice(purchaseDTO.getPurchase_price());
        purchaseOrder.setProviderid(purchaseDTO.getProvider_id());
        Integer state = purchaseDTO.getPurchase_state();
        if(state != null) {
            purchaseOrder.setState(purchaseDTO.getPurchase_state());
        }
        purchaseOrder.setOrdertime(concreteDataFormat.StringToDate(purchaseDTO.getPurchase_time()));
        return purchaseOrder;
    }
}
