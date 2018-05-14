package cn.dcan.mapper;

import cn.dcan.entity.PurchaseStore;

import java.util.List;

public interface PurchaseStoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseStore record);

    int insertSelective(PurchaseStore record);

    PurchaseStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseStore record);

    int updateByPrimaryKey(PurchaseStore record);
    //全部分配
    List<PurchaseStore> selectAll();
    //根据进货单获取分配
    List<PurchaseStore> selectByPurchase(Integer purchaseid);
}