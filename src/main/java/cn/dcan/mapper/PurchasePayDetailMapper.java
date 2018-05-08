package cn.dcan.mapper;

import cn.dcan.entity.PurchasePayDetail;

public interface PurchasePayDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchasePayDetail record);

    int insertSelective(PurchasePayDetail record);

    PurchasePayDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchasePayDetail record);

    int updateByPrimaryKey(PurchasePayDetail record);
}