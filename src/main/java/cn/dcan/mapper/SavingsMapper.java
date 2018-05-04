package cn.dcan.mapper;

import cn.dcan.entity.Savings;

import java.util.List;

public interface SavingsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Savings record);

    int insertSelective(Savings record);

    Savings selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Savings record);

    int updateByPrimaryKey(Savings record);

    List<Savings> getAll();
}