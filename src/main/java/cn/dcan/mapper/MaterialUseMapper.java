package cn.dcan.mapper;

import cn.dcan.entity.MaterialUse;

public interface MaterialUseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialUse record);

    int insertSelective(MaterialUse record);

    MaterialUse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialUse record);

    int updateByPrimaryKey(MaterialUse record);
}