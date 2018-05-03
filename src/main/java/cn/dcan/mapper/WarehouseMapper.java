package cn.dcan.mapper;

import cn.dcan.entity.Warehouse;

import java.util.List;

public interface WarehouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Warehouse record);

    int insertSelective(Warehouse record);

    Warehouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Warehouse record);

    int updateByPrimaryKey(Warehouse record);

    //查询全部仓库
    List<Warehouse> selectAll();
}