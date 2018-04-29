package cn.dcan.Service.impl;

import cn.dcan.Service.UserService;
import cn.dcan.dto.UserDTO;
import cn.dcan.entity.User;
import cn.dcan.mapper.UserMapper;
import cn.dcan.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by dongc_000 on 2018/4/29.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public UserDTO verifyUser(int id, byte[] password, int role) {
        User user = userMapper.verifyUser(id, password);
        ArrayList<Integer> roles = userRoleMapper.selectByUser(id);
        int s = roles.size();
        if(user != null) {
            if(s == 0) {
                System.out.println("There is no role of the user!");
            } else {
                for(int i = 0; i < s; i++) {
                    if(role == roles.get(i)) {
                        return entityToDto(user,roles);
                    }
                }
                System.out.println("The user does not match the role!");
            }
        } else {
            System.out.println("Cannot find the user!");
        }
        return null;
    }

    private UserDTO entityToDto(User user, ArrayList<Integer> roles) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getId());
        userDTO.setUser_name(user.getName());
        userDTO.setUser_roles(roles);
        return userDTO;
    }
}
