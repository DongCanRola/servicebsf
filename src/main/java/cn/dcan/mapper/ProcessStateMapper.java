package cn.dcan.mapper;

import cn.dcan.entity.ProcessState;

public interface ProcessStateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProcessState record);

    int insertSelective(ProcessState record);

    ProcessState selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessState record);

    int updateByPrimaryKey(ProcessState record);
}