package cn.dcan.mapper;

import cn.dcan.entity.MaterialType;

public interface MaterialTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialType record);

    int insertSelective(MaterialType record);

    MaterialType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialType record);

    int updateByPrimaryKey(MaterialType record);
}