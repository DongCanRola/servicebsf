package cn.dcan.mapper;

import cn.dcan.entity.SampleUse;

public interface SampleUseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleUse record);

    int insertSelective(SampleUse record);

    SampleUse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleUse record);

    int updateByPrimaryKey(SampleUse record);
}