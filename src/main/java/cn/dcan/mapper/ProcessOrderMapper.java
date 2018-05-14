package cn.dcan.mapper;

import cn.dcan.entity.ProcessOrder;

import java.util.List;

public interface ProcessOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProcessOrder record);

    int insertSelective(ProcessOrder record);

    ProcessOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessOrder record);

    int updateByPrimaryKey(ProcessOrder record);

    List<ProcessOrder> selectByState(Integer state);
    List<ProcessOrder> selectByProcess(Integer processid);
}