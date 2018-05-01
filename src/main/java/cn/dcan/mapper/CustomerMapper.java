package cn.dcan.mapper;

import cn.dcan.entity.Customer;

import java.util.*;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    //根据类型得到用户列表
    List<Customer> selectByType(int type);
}