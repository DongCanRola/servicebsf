package cn.dcan.mapper;

import java.util.*;

import cn.dcan.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    //登录验证
    User verifyUser(Integer id, byte[] password);

    //获取所有用户
    List<User> getUserList();
}