package cn.dcan.mapper;

import cn.dcan.entity.Process;

import java.util.List;

public interface ProcessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Process record);

    int insertSelective(Process record);

    Process selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Process record);

    int updateByPrimaryKey(Process record);

    List<Process> selectAll();

    Process selectBySaleOrder(Integer saleid);

    List<Process> selectByState(Integer state);
}