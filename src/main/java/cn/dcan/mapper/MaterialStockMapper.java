package cn.dcan.mapper;

import cn.dcan.entity.MaterialStock;
import cn.dcan.entity.MaterialStockKey;

public interface MaterialStockMapper {
    int deleteByPrimaryKey(MaterialStockKey key);

    int insert(MaterialStock record);

    int insertSelective(MaterialStock record);

    MaterialStock selectByPrimaryKey(MaterialStockKey key);

    int updateByPrimaryKeySelective(MaterialStock record);

    int updateByPrimaryKey(MaterialStock record);
}