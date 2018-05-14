package cn.dcan.mapper;

import cn.dcan.entity.MaterialStock;
import cn.dcan.entity.MaterialStockKey;

import java.util.List;
import java.util.Map;

public interface MaterialStockMapper {
    int deleteByPrimaryKey(MaterialStockKey key);

    int insert(MaterialStock record);

    int insertSelective(MaterialStock record);

    MaterialStock selectByPrimaryKey(MaterialStockKey key);

    int updateByPrimaryKeySelective(MaterialStock record);

    int updateByPrimaryKey(MaterialStock record);

    List<MaterialStock> selectByWarehouse(Integer warehouseid);

    List<MaterialStock> selectByGoods(Integer goodsid);

    Map<Integer,Integer> selectGoodsSum();

    Integer selectNumOfGoods(Integer goodsid);
}