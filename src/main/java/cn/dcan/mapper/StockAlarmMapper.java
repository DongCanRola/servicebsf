package cn.dcan.mapper;

import cn.dcan.entity.StockAlarm;

public interface StockAlarmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockAlarm record);

    int insertSelective(StockAlarm record);

    StockAlarm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockAlarm record);

    int updateByPrimaryKey(StockAlarm record);
}