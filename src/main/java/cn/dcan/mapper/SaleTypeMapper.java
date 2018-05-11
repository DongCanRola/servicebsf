package cn.dcan.mapper;

import cn.dcan.entity.SaleType;

public interface SaleTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleType record);

    int insertSelective(SaleType record);

    SaleType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleType record);

    int updateByPrimaryKey(SaleType record);
}