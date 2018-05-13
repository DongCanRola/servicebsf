package cn.dcan.mapper;

import cn.dcan.entity.PurchaseStore;

public interface PurchaseStoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseStore record);

    int insertSelective(PurchaseStore record);

    PurchaseStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseStore record);

    int updateByPrimaryKey(PurchaseStore record);
}