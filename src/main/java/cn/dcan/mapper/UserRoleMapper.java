package cn.dcan.mapper;

import cn.dcan.entity.UserRoleKey;

import java.util.*;

public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

    //获取一个用户的所有角色
    ArrayList<Integer> selectByUser(int userid);
}