package cn.dcan.mapper;

import cn.dcan.entity.StockAlarm;

import java.util.List;

public interface StockAlarmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockAlarm record);

    int insertSelective(StockAlarm record);

    StockAlarm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockAlarm record);

    int updateByPrimaryKey(StockAlarm record);

    List<StockAlarm> selectByState(Integer state);

    int updateAlarmState(StockAlarm stockAlarm);
}