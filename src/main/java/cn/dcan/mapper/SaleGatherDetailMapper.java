package cn.dcan.mapper;

import cn.dcan.entity.SaleGatherDetail;

import java.util.List;

public interface SaleGatherDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleGatherDetail record);

    int insertSelective(SaleGatherDetail record);

    SaleGatherDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleGatherDetail record);

    int updateByPrimaryKey(SaleGatherDetail record);

    List<SaleGatherDetail> selectByGatherId(Integer gatherid);

    List<SaleGatherDetail> selectBySavingsId(String savingsid);

    List<SaleGatherDetail> selectAll();
}