package cn.dcan.mapper;

import cn.dcan.entity.SaleGatherDetail;

public interface SaleGatherDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleGatherDetail record);

    int insertSelective(SaleGatherDetail record);

    SaleGatherDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleGatherDetail record);

    int updateByPrimaryKey(SaleGatherDetail record);
}