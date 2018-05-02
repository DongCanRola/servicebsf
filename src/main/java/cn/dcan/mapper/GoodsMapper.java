package cn.dcan.mapper;

import cn.dcan.entity.Goods;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    //根据材料类型查找
    List<Goods> selectByType(int type);
}