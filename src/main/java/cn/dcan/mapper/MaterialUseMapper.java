package cn.dcan.mapper;

import cn.dcan.entity.MaterialUse;

import java.util.List;

public interface MaterialUseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialUse record);

    int insertSelective(MaterialUse record);

    MaterialUse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialUse record);

    int updateByPrimaryKey(MaterialUse record);

    List<MaterialUse> selectByProcessOrder(Integer processorderid);
    List<MaterialUse> selectByPurchaseStore(Integer purchasestoreid);
}