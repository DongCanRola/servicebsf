package cn.dcan.mapper;

import cn.dcan.entity.PurchasePayDetail;

import java.util.List;

public interface PurchasePayDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchasePayDetail record);

    int insertSelective(PurchasePayDetail record);

    PurchasePayDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchasePayDetail record);

    int updateByPrimaryKey(PurchasePayDetail record);

    List<PurchasePayDetail> selectAll();

    List<PurchasePayDetail> selectByPayId(Integer payid);
}