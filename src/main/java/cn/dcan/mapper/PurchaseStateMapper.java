package cn.dcan.mapper;

import cn.dcan.entity.PurchaseState;

public interface PurchaseStateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseState record);

    int insertSelective(PurchaseState record);

    PurchaseState selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseState record);

    int updateByPrimaryKey(PurchaseState record);
}