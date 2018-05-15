package cn.dcan.mapper;

import cn.dcan.entity.SaleGather;

import java.util.List;

public interface SaleGatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleGather record);

    int insertSelective(SaleGather record);

    SaleGather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleGather record);

    int updateByPrimaryKey(SaleGather record);

    List<SaleGather> selectAll();
}