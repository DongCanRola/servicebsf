package cn.dcan.mapper;

import cn.dcan.entity.SaleState;

public interface SaleStateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleState record);

    int insertSelective(SaleState record);

    SaleState selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleState record);

    int updateByPrimaryKey(SaleState record);
}