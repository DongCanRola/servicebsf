package cn.dcan.mapper;

import cn.dcan.entity.SaleOrder;

import java.util.List;

public interface SaleOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleOrder record);

    int insertSelective(SaleOrder record);

    SaleOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleOrder record);

    int updateByPrimaryKey(SaleOrder record);

    //获取全部销售单
    List<SaleOrder> selectAll();
    //根据状态获取销售单
    List<SaleOrder> selectByState(Integer state);
}