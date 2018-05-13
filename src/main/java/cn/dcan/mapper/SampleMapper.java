package cn.dcan.mapper;

import cn.dcan.entity.Sample;

import java.util.List;

public interface SampleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sample record);

    int insertSelective(Sample record);

    Sample selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sample record);

    int updateByPrimaryKey(Sample record);

    List<Sample> selectByProduct(Integer productid);
}