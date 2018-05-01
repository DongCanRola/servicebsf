package cn.dcan.mapper;

import cn.dcan.entity.CustomerType;

public interface CustomerTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerType record);

    int insertSelective(CustomerType record);

    CustomerType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerType record);

    int updateByPrimaryKey(CustomerType record);
}