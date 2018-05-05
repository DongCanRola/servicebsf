package cn.dcan.mapper;

import cn.dcan.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseOrder record);

    int insertSelective(PurchaseOrder record);

    PurchaseOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseOrder record);

    int updateByPrimaryKey(PurchaseOrder record);

    //查询所有订单信息
    List<PurchaseOrder> getAllOrders();

    //根据状态查询订单
    List<PurchaseOrder> getOrdersByState(Integer state);
}