package cn.dcan.mapper;

import cn.dcan.entity.PurchasePay;

import java.util.List;

public interface PurchasePayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchasePay record);

    int insertSelective(PurchasePay record);

    PurchasePay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchasePay record);

    int updateByPrimaryKey(PurchasePay record);

    List<PurchasePay> selectAll();
}